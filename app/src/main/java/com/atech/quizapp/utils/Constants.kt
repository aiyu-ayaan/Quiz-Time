package com.atech.quizapp.utils

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

const val BASE_URL = "https://opentdb.com/"

enum class Difficulty {
    easy,
    medium,
    hard
}

@Keep
@Parcelize
data class UserDetail(
    val name: String,
    val category: Int,
    val difficulty: String,
    var points: Int = 0
) : Parcelable

@Keep
data class Category(val id: Int, val name: String)

val category_list = listOf(
    Category(0, "Any Category"),

    Category(
        9, "General Knowledge"
    ),
    Category(
        10,
        "Entertainment: Books"
    ),
    Category(
        11,
        "Entertainment: Film"
    ),
    Category(
        12,
        "Entertainment: Music"
    ),
    Category(
        13,
        "Entertainment: Musicals & Theatres"
    ),
    Category(
        14, "Entertainment: Television"
    ),
    Category(
        15,
        "Entertainment: Video Games"
    ),
    Category(
        16,
        "Entertainment: Board Games"
    ),
    Category(
        17,
        "Science & Nature"
    ),
    Category(
        18,
        "Science: Computers"
    ),
    Category(
        19,
        "Science: Mathematics"
    ),
    Category(
        20,
        "Mythology"
    ),
    Category(
        21,
        "Sports"
    ),
    Category(
        22,
        "Geography"
    ),
    Category(
        23,
        "History"
    ),
    Category(
        24,
        "Politics"
    ),
    Category(
        25,
        "Art"
    ),
    Category(
        26,
        "Celebrities"
    ),
    Category(
        27,
        "Animals"
    ),
    Category(
        28,
        "Vehicles"
    ),
    Category(
        29,
        "Entertainment: Comics"
    ),
    Category(
        30,
        "Science: Gadgets"
    ),
    Category(
        31,
        "Entertainment: Japanese Anime & Manga"
    ),
    Category(
        32,
        "Entertainment: Cartoon & Animations"
    )


)