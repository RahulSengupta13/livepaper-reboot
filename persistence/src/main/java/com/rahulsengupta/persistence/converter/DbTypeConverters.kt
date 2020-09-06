package com.rahulsengupta.persistence.converter

import androidx.room.TypeConverter
import com.rahulsengupta.persistence.LivePaperDatabase.Companion.json
import com.rahulsengupta.persistence.entity.common.CoverPhoto
import com.rahulsengupta.persistence.entity.common.Urls
import com.rahulsengupta.persistence.entity.common.User
import com.rahulsengupta.persistence.entity.common.UserImage
import kotlinx.serialization.serializer

class DbTypeConverters {

    @TypeConverter
    fun fromUrls(data: Urls) = json.encodeToString(Urls.serializer(), data)

    @TypeConverter
    fun toUrls(value: String): Urls = json.decodeFromString(serializer(), value)

    @TypeConverter
    fun fromCoverPhoto(data: CoverPhoto) = json.encodeToString(CoverPhoto.serializer(), data)

    @TypeConverter
    fun toCoverPhoto(value: String): CoverPhoto = json.decodeFromString(serializer(), value)

    @TypeConverter
    fun fromUser(data: User) = json.encodeToString(User.serializer(), data)

    @TypeConverter
    fun toUser(value: String): User = json.decodeFromString(serializer(), value)

    @TypeConverter
    fun fromUserImage(data: UserImage) = json.encodeToString(UserImage.serializer(), data)

    @TypeConverter
    fun toUserImage(value: String): UserImage = json.decodeFromString(serializer(), value)
}