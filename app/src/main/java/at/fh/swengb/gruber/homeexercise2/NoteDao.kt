package at.fh.swengb.gruber.homeexercise2

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import android.provider.ContactsContract

@Dao
interface NoteDao {
    @Insert
    fun insert(note: note)

    @Update
    fun update(note: note)

    @Query("SELECT * FROM note")
    fun findNotes():List<note>


}