package com.example.doodlenote.uiscreens

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.doodlenote.data.ChecklistItem
import com.example.doodlenote.data.Note
import com.example.doodlenote.viewmodel.NoteWithChecklist
import java.util.*

@Composable
fun NoteScreen(
    noteToEdit: NoteWithChecklist? = null,
    onSave: (Note, List<ChecklistItem>) -> Unit,
    onCancel: () -> Unit
) {
    val context = LocalContext.current

    var titleText by remember { mutableStateOf(noteToEdit?.note?.title ?: "") }
    var contentText by remember { mutableStateOf(noteToEdit?.note?.content ?: "") }
    val checklistState = remember { mutableStateListOf<ChecklistItem>() }
    var newChecklistText by remember { mutableStateOf("") }

    var reminderTime by remember { mutableStateOf(noteToEdit?.note?.reminderTime ?: 0L) }

    // Prefill checklist when editing
    LaunchedEffect(noteToEdit) {
        checklistState.clear()
        noteToEdit?.checklistItems?.let { checklistState.addAll(it) }
    }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = if (noteToEdit != null) "Edit Note" else "New Note",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        // Title Input
        BasicTextField(
            value = titleText,
            onValueChange = { titleText = it },
            singleLine = true,
            textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium),
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(16.dp)
        )

        // Content Input
        BasicTextField(
            value = contentText,
            onValueChange = { contentText = it },
            textStyle = TextStyle(fontSize = 16.sp),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 180.dp)
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(16.dp)
        )

        // Reminder Picker
        Button(
            onClick = {
                val calendar = Calendar.getInstance()
                DatePickerDialog(
                    context,
                    { _, year, month, dayOfMonth ->
                        calendar.set(year, month, dayOfMonth)
                        TimePickerDialog(
                            context,
                            { _, hourOfDay, minute ->
                                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                                calendar.set(Calendar.MINUTE, minute)
                                calendar.set(Calendar.SECOND, 0)
                                reminderTime = calendar.timeInMillis
                                Toast.makeText(
                                    context,
                                    "Reminder set!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            },
                            calendar.get(Calendar.HOUR_OF_DAY),
                            calendar.get(Calendar.MINUTE),
                            true
                        ).show()
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = if (reminderTime > 0) "Reminder Set ✅" else "Set Reminder")
        }

        // Checklist
        Text("Checklist:", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
        checklistState.forEachIndexed { index, item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .toggleable(
                        value = item.isChecked,
                        onValueChange = { checked -> checklistState[index] = item.copy(isChecked = checked) }
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.title,
                    textDecoration = if (item.isChecked) TextDecoration.LineThrough else TextDecoration.None,
                    modifier = Modifier.weight(1f)
                )
                Checkbox(
                    checked = item.isChecked,
                    onCheckedChange = { checked -> checklistState[index] = item.copy(isChecked = checked) }
                )
            }
        }

        // Add Checklist Item
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            BasicTextField(
                value = newChecklistText,
                onValueChange = { newChecklistText = it },
                singleLine = true,
                textStyle = TextStyle(fontSize = 16.sp),
                modifier = Modifier
                    .weight(1f)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(12.dp)
            )
            Button(onClick = {
                if (newChecklistText.isNotBlank()) {
                    checklistState.add(ChecklistItem(noteId = 0, title = newChecklistText))
                    newChecklistText = ""
                }
            }) {
                Text("Add")
            }
        }

        // Save & Cancel Buttons
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = {
                    val note = noteToEdit?.note?.copy(
                        title = titleText,
                        content = contentText,
                        reminderTime = reminderTime
                    ) ?: Note(
                        title = titleText,
                        content = contentText,
                        categoryId = 0,
                        reminderTime = reminderTime
                    )
                    onSave(note, checklistState.toList())
                },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Save", color = MaterialTheme.colorScheme.onPrimary, fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = { onCancel() },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Text("Cancel", color = MaterialTheme.colorScheme.onSecondary, fontWeight = FontWeight.Bold)
            }
        }
    }
}
