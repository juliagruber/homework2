package at.fh.swengb.gruber.homeexercise2

import android.arch.persistence.room.*

@Entity (tableName = "Notes",
        foreignKeys = [ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["userid"],
            onDelete = ForeignKey.CASCADE )])
class note(val title: String, val content: String, val userid: Long ) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

