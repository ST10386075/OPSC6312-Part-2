package com.example.doodlenote.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "checklist_items")
data class ChecklistItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val noteId: Int = 0,           // link to Note; 0 initially, will be updated after saving note
    val title: String = "",        // checklist item text
    val isChecked: Boolean = false // true if checked
) {
    // Optional: helper function to toggle check state
    fun toggle(): ChecklistItem = copy(isChecked = !isChecked)
}
