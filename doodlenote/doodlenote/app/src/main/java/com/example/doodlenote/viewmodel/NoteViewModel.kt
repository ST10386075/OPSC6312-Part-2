package com.example.doodlenote.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doodlenote.MyApplication
import com.example.doodlenote.data.*
import com.example.doodlenote.utils.ReminderHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// Data class combining a Note with its checklist items
data class NoteWithChecklist(
    val note: Note,
    val checklistItems: List<ChecklistItem>
)

class NoteViewModel : ViewModel() {

    private val db: AppDatabase = AppDatabase.getDatabase(MyApplication.instance)
    private val repository = NoteRepository(db)

    private val _notesWithChecklist = MutableStateFlow<List<NoteWithChecklist>>(emptyList())
    val notesWithChecklist: StateFlow<List<NoteWithChecklist>> get() = _notesWithChecklist

    init {
        loadNotesWithChecklist()
    }

    // Load all notes and their checklist items
    fun loadNotesWithChecklist() {
        viewModelScope.launch {
            val notes = repository.getAllNotes()
            val combined = notes.map { note ->
                NoteWithChecklist(
                    note,
                    repository.getChecklistForNote(note.id)
                )
            }
            _notesWithChecklist.value = combined
        }
    }

    /**
     * Adds a new note with checklist items.
     * Automatically schedules a reminder AFTER the note ID is generated.
     */
    fun addNoteWithChecklist(
        note: Note,
        checklist: List<ChecklistItem>,
        onInserted: ((Note) -> Unit)? = null
    ) {
        viewModelScope.launch {
            // Insert note and get generated ID
            val noteId = repository.insertNote(note).toInt()
            val insertedNote = note.copy(id = noteId)

            // Insert checklist items linked to this note
            checklist.forEach { item ->
                repository.insertChecklistItem(item.copy(noteId = noteId))
            }

            // Reload notes for UI
            loadNotesWithChecklist()

            // Schedule reminder if needed
            if (insertedNote.reminderTime != null && insertedNote.reminderTime > 0) {
                ReminderHelper.scheduleReminder(MyApplication.instance, insertedNote)
            }

            // Notify caller with the inserted note
            onInserted?.invoke(insertedNote)
        }
    }

    // Update an existing note (reschedule reminder if needed)
    fun updateNote(note: Note) {
        viewModelScope.launch {
            repository.updateNote(note)
            loadNotesWithChecklist()

            // Cancel previous reminder
            ReminderHelper.cancelReminder(MyApplication.instance, note)

            // Schedule new reminder if set
            if (note.reminderTime != null && note.reminderTime > 0) {
                ReminderHelper.scheduleReminder(MyApplication.instance, note)
            }
        }
    }

    // Delete a note and its checklist items + cancel reminder
    fun deleteNote(note: Note) {
        viewModelScope.launch {
            val items = repository.getChecklistForNote(note.id)
            items.forEach { repository.deleteChecklistItem(it) }
            repository.deleteNote(note)

            ReminderHelper.cancelReminder(MyApplication.instance, note)
            loadNotesWithChecklist()
        }
    }

    // Checklist operations
    fun addChecklistItem(item: ChecklistItem) = viewModelScope.launch {
        repository.insertChecklistItem(item)
        loadNotesWithChecklist()
    }

    fun updateChecklistItem(item: ChecklistItem) = viewModelScope.launch {
        repository.updateChecklistItem(item)
        loadNotesWithChecklist()
    }

    fun deleteChecklistItem(item: ChecklistItem) = viewModelScope.launch {
        repository.deleteChecklistItem(item)
        loadNotesWithChecklist()
    }
}
