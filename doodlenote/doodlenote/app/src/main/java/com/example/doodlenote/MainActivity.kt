package com.example.doodlenote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.doodlenote.data.ChecklistItem
import com.example.doodlenote.data.Note
import com.example.doodlenote.ui.theme.DoodleNoteTheme
import com.example.doodlenote.uiscreens.NoteScreen
import com.example.doodlenote.uiscreens.SavedNotesScreen
import com.example.doodlenote.viewmodel.NoteViewModel
import com.example.doodlenote.viewmodel.NoteWithChecklist
import com.example.doodlenote.utils.ReminderHelper

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DoodleNoteTheme {
                val noteViewModel: NoteViewModel = viewModel()

                var currentScreen by remember { mutableStateOf("Home") }
                var selectedNote by remember { mutableStateOf<NoteWithChecklist?>(null) }
                val notesWithChecklist by noteViewModel.notesWithChecklist.collectAsState(initial = emptyList())

                when (currentScreen) {
                    "Home" -> HomeScreen(
                        onCreateNote = { currentScreen = "NoteScreen" },
                        onViewNotes = { currentScreen = "SavedNotesScreen" }
                    )

                    "NoteScreen" -> NoteScreen(
                        noteToEdit = selectedNote,
                        onSave = { note: Note, checklist: List<ChecklistItem> ->
                            // Add note and schedule reminder safely after ID is assigned
                            noteViewModel.addNoteWithChecklist(note, checklist) { insertedNote ->
                                if (insertedNote.reminderTime != null && insertedNote.reminderTime > 0) {
                                    ReminderHelper.scheduleReminder(this@MainActivity, insertedNote)
                                }
                                selectedNote = null
                                currentScreen = "SavedNotesScreen"
                            }
                        },
                        onCancel = {
                            selectedNote = null
                            currentScreen = "Home"
                        }
                    )

                    "SavedNotesScreen" -> SavedNotesScreen(
                        notes = notesWithChecklist,
                        noteViewModel = noteViewModel,
                        onNoteClick = { note ->
                            selectedNote = note
                            currentScreen = "NoteScreen"
                        },
                        onCreateNewNote = {
                            selectedNote = null
                            currentScreen = "NoteScreen"
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun HomeScreen(
    onCreateNote: () -> Unit,
    onViewNotes: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF6A1B9A)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // App title
            Text(
                text = "DoodleNote",
                style = MaterialTheme.typography.displayMedium,
                color = Color.White
            )

            // Buttons
            ActionButton(
                text = "Create New Note",
                backgroundColor = Color.White,
                contentColor = Color(0xFF6A1B9A),
                onClick = onCreateNote
            )

            ActionButton(
                text = "View Saved Notes",
                backgroundColor = Color(0xFFCE93D8),
                contentColor = Color.White,
                onClick = onViewNotes
            )
        }
    }
}

@Composable
fun ActionButton(
    text: String,
    backgroundColor: Color,
    contentColor: Color,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
    ) {
        Text(
            text = text,
            color = contentColor,
            style = MaterialTheme.typography.titleMedium
        )
    }
}
