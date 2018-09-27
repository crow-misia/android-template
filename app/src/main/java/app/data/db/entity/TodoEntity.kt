package app.data.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.threeten.bp.Instant

@Entity(tableName = "todo")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo
    val id: Int,

    @ColumnInfo
    val name: String,

    @ColumnInfo
    val time: Instant
)