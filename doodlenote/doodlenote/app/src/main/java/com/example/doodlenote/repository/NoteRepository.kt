package com.example.doodlenote.data

class NoteRepository(private val db: AppDatabase) {

    private val noteDao = db.noteDao()
    private val checklistDao = db.checklistDao()

    // Note methods
    suspend fun insertNote(note: Note) = noteDao.insertNote(note)
    suspend fun updateNote(note: Note) = noteDao.updateNote(note)
    suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)
    suspend fun getAllNotes(): List<Note> = noteDao.getAllNotes()
    suspend fun getNoteById(id: Int): Note? = noteDao.getNoteById(id)

    // Checklist methods
    suspend fun insertChecklistItem(item: ChecklistItem) = checklistDao.insertItem(item)
    suspend fun updateChecklistItem(item: ChecklistItem) = checklistDao.updateItem(item)
    suspend fun deleteChecklistItem(item: ChecklistItem) = checklistDao.deleteItem(item)
    suspend fun getChecklistForNote(noteId: Int): List<ChecklistItem> = checklistDao.getItemsForNote(noteId)
}
