package app.data.db.entity.mapper

import android.arch.persistence.room.TypeConverter
import org.threeten.bp.Instant

object Converters {
    @TypeConverter
    @JvmStatic
    fun fromTimestamp(value: Long?): Instant? = value?.let { Instant.ofEpochSecond(it) }

    @TypeConverter @JvmStatic
    fun dateToTimestamp(date: Instant?): Long? = date?.epochSecond
}