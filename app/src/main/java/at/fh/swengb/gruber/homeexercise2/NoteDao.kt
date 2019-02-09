package at.fh.swengb.gruber.homeexercise2

import android.arch.persistence.room.*
import android.provider.ContactsContract

@Dao
interface NoteDao {
    @Insert
    fun insert(note: note)

    @Update
    fun update(note: note)

    @Query("SELECT * FROM Notes")
    fun findNotes():List<note>

    @Query("SELECT * FROM Notes WHERE id = :id")
    fun openNote(id:Long): note

    @Query("UPDATE Notes SET title = :titleNew, content = :contentNew WHERE id = :noteId")
    fun updateNote(titleNew:String, contentNew: String, noteId:Long)

    @Delete
    fun delete(note:note)




}