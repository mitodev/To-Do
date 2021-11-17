package com.example.to_do_v2.data

import androidx.room.TypeConverter
import com.example.to_do_v2.data.model.Priority

class Converter {
    @TypeConverter
    fun fromPriority(priority: Priority): String {
        return priority.name
    }

    @TypeConverter
    fun toPriority(priority: String): Priority {
        return Priority.valueOf(priority)
    }
}