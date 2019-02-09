package at.fh.swengb.gruber.homeexercise2

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation

class UserAndNotes {
    @Embedded
    lateinit var user: User
    @Relation(entity = note::class, entityColumn = "userid", parentColumn = "id")
    lateinit var notes: List<note> }