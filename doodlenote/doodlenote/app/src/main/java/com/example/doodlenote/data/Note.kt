package com.example.doodlenote.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String = "",
    val content: String = "",
    val categoryId: Int = 0,
    val reminderTime: Long? = null // nullable, stores the time in millis
)
