package com.example.qcells.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@Entity
data class Application(
   @PrimaryKey val uuid: Uuid,
   val name: String,
   val category: ApplicationCategory,
   val ratingInTenths: Int?,
   val developerName: String,
   val installStatus: InstallStatus,
   val iconColorAsInt: Int,
   val description: String,
)


val Application.rating: String?
   get() = ratingInTenths?.let { formatRating(it) }

fun formatRating(ratingInTenths: Int): String =
   "%.1f".format(ratingInTenths / 10.0)

enum class ApplicationCategory(val description: String) {
    ART_AND_DESIGN("Art & Design"),
    AUTO_AND_VEHICLES("Auto & Vehicles"),
    BEAUTY("Beauty"),
    BOOKS_AND_REFERENCE("Books & Reference"),
    BUSINESS("Business"),
    COMICS("Comics"),
    COMMUNICATION("Communication"),
    DATING("Dating"),
    EDUCATION("Education"),
    ENTERTAINMENT("Entertainment"),
    EVENTS("Events"),
    FINANCE("Finance"),
    FOOD_AND_DRINK("Food & Drink"),
    HEALTH_AND_FITNESS("Health & Fitness"),
    HOUSE_AND_HOME("House & Home"),
    LIBRARIES_AND_DEMO("Libraries & Demo"),
    LIFESTYLE("Lifestyle"),
    MAPS_AND_NAVIGATION("Maps & Navigation"),
    MEDICAL("Medical"),
    MUSIC_AND_AUDIO("Music & Audio"),
    NEWS_AND_MAGAZINES("News & Magazines"),
    PARENTING("Parenting"),
    PERSONALIZATION("Personalization"),
    PHOTOGRAPHY("Photography"),
    PRODUCTIVITY("Productivity"),
    SHOPPING("Shopping"),
    SOCIAL("Social"),
    SPORTS("Sports"),
    TOOLS("Tools"),
    TRAVEL_AND_LOCAL("Travel & Local"),
    VIDEO_PLAYERS_AND_EDITORS("Video Players & Editors"),
    WEATHER("Weather")
}

enum class InstallStatus {
   NOT_COMPATIBLE,
   NOT_INSTALLED,
   INSTALLING,
   INSTALLED,
   UNINSTALLING,
   ;
}
