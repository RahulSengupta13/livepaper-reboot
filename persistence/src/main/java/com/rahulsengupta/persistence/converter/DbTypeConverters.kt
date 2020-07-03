package com.rahulsengupta.persistence.converter

import androidx.room.TypeConverter
import com.rahulsengupta.persistence.LivePaperDatabase.Companion.json
import com.rahulsengupta.persistence.entity.common.CoverPhoto
import com.rahulsengupta.persistence.entity.common.Urls
import com.rahulsengupta.persistence.entity.common.User
import com.rahulsengupta.persistence.entity.common.UserImage
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.serializer
import kotlinx.serialization.stringify

class DbTypeConverters {

    @ImplicitReflectionSerializer
    @TypeConverter
    fun fromUrls(data: Urls) = json.stringify(data)

    @ImplicitReflectionSerializer
    @TypeConverter
    fun toUrls(value: String): Urls = json.parse(serializer(), value)

    @ImplicitReflectionSerializer
    @TypeConverter
    fun fromCoverPhoto(data: CoverPhoto) = json.stringify(data)

    @ImplicitReflectionSerializer
    @TypeConverter
    fun toCoverPhoto(value: String): CoverPhoto = json.parse(serializer(), value)

    @ImplicitReflectionSerializer
    @TypeConverter
    fun fromUser(data: User) = json.stringify(data)

    @ImplicitReflectionSerializer
    @TypeConverter
    fun toUser(value: String): User = json.parse(serializer(), value)

    @ImplicitReflectionSerializer
    @TypeConverter
    fun fromUserImage(data: UserImage) = json.stringify(data)

    @ImplicitReflectionSerializer
    @TypeConverter
    fun toUserImage(value: String): UserImage = json.parse(serializer(), value)
}