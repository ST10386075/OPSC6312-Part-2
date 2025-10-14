package com.example.doodlenote.data

import androidx.room.*

@Dao
interface NoteDao {

    @Insert
    suspend fun insertNote(note: Note): Long

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM notes") // matches tableName
    suspend fun getAllNotes(): List<Note>

    @Query("SELECT * FROM notes WHERE id = :id") // matches tableName
    suspend fun getNoteById(id: Int): Note?
}
