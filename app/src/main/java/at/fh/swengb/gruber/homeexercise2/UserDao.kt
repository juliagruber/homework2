package at.fh.swengb.gruber.homeexercise2

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import android.provider.ContactsContract

@Dao
interface UserDao {
    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Query("SELECT * FROM Users" )
    fun findUser():List<User>

    @Query("SELECT id FROM Users WHERE name = :username")
    fun findUserId(username: String): Long?

    @Query("SELECT * FROM Users WHERE id= :userId")
    fun findNotesForUser(userId: Long) :UserAndNotes


}