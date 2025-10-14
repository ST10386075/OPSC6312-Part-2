package com.example.doodlenote.data

import androidx.room.*

@Dao
interface ChecklistDao {

    @Insert
    suspend fun insertItem(item: ChecklistItem): Long

    @Update
    suspend fun updateItem(item: ChecklistItem)

    @Delete
    suspend fun deleteItem(item: ChecklistItem)

    @Query("SELECT * FROM checklist_items WHERE noteId = :noteId")
    suspend fun getItemsForNote(noteId: Int): List<ChecklistItem>
}
