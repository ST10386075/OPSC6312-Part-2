package com.example.doodlenote.uiscreens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.doodlenote.viewmodel.NoteWithChecklist
import com.example.doodlenote.viewmodel.NoteViewModel

@Composable
fun SavedNotesScreen(
    notes: List<NoteWithChecklist>,
    noteViewModel: NoteViewModel,
    onNoteClick: (NoteWithChecklist) -> Unit,
    onCreateNewNote: () -> Unit
) {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    // Filter notes by title or checklist items
    val filteredNotes = notes.filter { noteWithChecklist ->
        noteWithChecklist.note.title.contains(searchQuery.text, ignoreCase = true) ||
                noteWithChecklist.checklistItems.any { it.title.contains(searchQuery.text, ignoreCase = true) }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
        // Search bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Search notes...") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            singleLine = true,
            shape = RoundedCornerShape(12.dp)
        )

        // Create new note button
        Button(
            onClick = onCreateNewNote,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Create New Note")
        }

        // Notes list
        if (filteredNotes.isEmpty()) {
            Text("No notes found.", style = MaterialTheme.typography.bodyLarge)
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(filteredNotes) { noteWithChecklist ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clickable { onNoteClick(noteWithChecklist) },
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            // Note title
                            Text(
                                text = noteWithChecklist.note.title,
                                style = MaterialTheme.typography.titleMedium
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            // Checklist items
                            noteWithChecklist.checklistItems.forEach { item ->
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    Text(
                                        text = if (item.isChecked) "✔ ${item.title}" else "◻ ${item.title}",
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            // Delete button
                            Button(
                                onClick = { noteViewModel.deleteNote(noteWithChecklist.note) },
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text("Delete Note", color = MaterialTheme.colorScheme.onError)
                            }
                        }
                    }
                }
            }
        }
    }
}
