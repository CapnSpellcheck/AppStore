package com.example.qcells.storage

import androidx.room.TypeConverter
import com.example.qcells.model.ApplicationCategory
import com.example.qcells.model.InstallStatus
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class Converters {
    @TypeConverter
    fun fromApplicationCategory(value: ApplicationCategory): String {
        return value.name
    }

    @TypeConverter
    fun toApplicationCategory(value: String): ApplicationCategory {
        return ApplicationCategory.valueOf(value)
    }

    @TypeConverter
    fun fromInstallStatus(value: InstallStatus): String {
        return value.name
    }

    @TypeConverter
    fun toInstallStatus(value: String): InstallStatus {
        return InstallStatus.valueOf(value)
    }

    @TypeConverter
    fun uuidToString(uuid: Uuid): String {
        return uuid.toHexString()
    }
    @TypeConverter
    fun stringtoUuid(string: String): Uuid {
        return Uuid.Companion.parseHex(string)
    }

}
