package app.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import app.data.db.entity.TodoEntity
import app.data.db.entity.mapper.Converters

@Database(entities = [
    TodoEntity::class
], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

}
