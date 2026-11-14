package com.trebronwayne.aubree

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * The twenty-three things Aubree can say. Order matches the `aubreeState` enum in attrs.xml.
 *
 * | State      | Use it for                                |
 * |------------|-------------------------------------------|
 * | HAPPY      | success, done, saved                      |
 * | SAD        | error, failed, declined                   |
 * | ASK        | request, permission, confirm              |
 * | WAIT       | loading, processing                       |
 * | CELEBRATE  | big win, level up, first purchase         |
 * | SLEEPY     | idle, night mode, nothing happening       |
 * | OFFLINE    | no connection, retry                      |
 * | EMPTY      | empty list, no results, no items          |
 * | SEARCHING  | search in progress                        |
 * | TYPING     | composing, saving text, chat input        |
 * | LOW_BATTERY| low battery or power-saving warning       |
 * | WELCOME    | welcome back, onboarding, login success   |
 * | PLEADING   | please, begging, soft ask (emoji 🥺)       |
 * | TOUCHED    | moved, grateful, happy tears (emoji 🥹)    |
 * | RUNNING    | in a hurry, fast, urgent                  |
 * | PENSIVE    | disappointed, quiet sad (emoji 😔)         |
 * | LAUGHING   | funny, delighted (emoji 😆)                |
 * | FEARFUL    | warning, risky, nervous (emoji 😨)         |
 * | SCREAMING  | shock, critical error (emoji 😱)           |
 * | LOVE       | favorite, liked, loved (emoji 😍)          |
 * | ANGRY      | blocked, denied, rage (emoji 😠)           |
 * | CRYING     | big failure, heartbreak (emoji 😭)         |
 * | COOL       | premium, pro, nice work (emoji 😎)         |
 */
enum class AubreeState(
    /** Short lowercase name, the same one used in XML (`app:aubreeState="wait"`). */
    val id: String,
    @DrawableRes internal val animationRes: Int,
    @DrawableRes internal val stillRes: Int,
    @StringRes internal val descriptionRes: Int,
) {
    HAPPY("happy", R.drawable.aubree_happy, R.drawable.aubree_happy_still, R.string.aubree_desc_happy),
    SAD("sad", R.drawable.aubree_sad, R.drawable.aubree_sad_still, R.string.aubree_desc_sad),
    ASK("ask", R.drawable.aubree_ask, R.drawable.aubree_ask_still, R.string.aubree_desc_ask),
    WAIT("wait", R.drawable.aubree_wait, R.drawable.aubree_wait_still, R.string.aubree_desc_wait),
    CELEBRATE("celebrate", R.drawable.aubree_celebrate, R.drawable.aubree_celebrate_still, R.string.aubree_desc_celebrate),
    SLEEPY("sleepy", R.drawable.aubree_sleepy, R.drawable.aubree_sleepy_still, R.string.aubree_desc_sleepy),
    OFFLINE("offline", R.drawable.aubree_offline, R.drawable.aubree_offline_still, R.string.aubree_desc_offline),
    EMPTY("empty", R.drawable.aubree_empty, R.drawable.aubree_empty_still, R.string.aubree_desc_empty),
    SEARCHING("searching", R.drawable.aubree_searching, R.drawable.aubree_searching_still, R.string.aubree_desc_searching),
    TYPING("typing", R.drawable.aubree_typing, R.drawable.aubree_typing_still, R.string.aubree_desc_typing),
    LOW_BATTERY("lowbattery", R.drawable.aubree_lowbattery, R.drawable.aubree_lowbattery_still, R.string.aubree_desc_lowbattery),
    WELCOME("welcome", R.drawable.aubree_welcome, R.drawable.aubree_welcome_still, R.string.aubree_desc_welcome),
    PLEADING("pleading", R.drawable.aubree_pleading, R.drawable.aubree_pleading_still, R.string.aubree_desc_pleading),
    TOUCHED("touched", R.drawable.aubree_touched, R.drawable.aubree_touched_still, R.string.aubree_desc_touched),
    RUNNING("running", R.drawable.aubree_running, R.drawable.aubree_running_still, R.string.aubree_desc_running),
    PENSIVE("pensive", R.drawable.aubree_pensive, R.drawable.aubree_pensive_still, R.string.aubree_desc_pensive),
    LAUGHING("laughing", R.drawable.aubree_laughing, R.drawable.aubree_laughing_still, R.string.aubree_desc_laughing),
    FEARFUL("fearful", R.drawable.aubree_fearful, R.drawable.aubree_fearful_still, R.string.aubree_desc_fearful),
    SCREAMING("screaming", R.drawable.aubree_screaming, R.drawable.aubree_screaming_still, R.string.aubree_desc_screaming),
    LOVE("love", R.drawable.aubree_love, R.drawable.aubree_love_still, R.string.aubree_desc_love),
    ANGRY("angry", R.drawable.aubree_angry, R.drawable.aubree_angry_still, R.string.aubree_desc_angry),
    CRYING("crying", R.drawable.aubree_crying, R.drawable.aubree_crying_still, R.string.aubree_desc_crying),
    COOL("cool", R.drawable.aubree_cool, R.drawable.aubree_cool_still, R.string.aubree_desc_cool);

    companion object {
        /** Finds a state by its [id], or returns null. Handy for server-driven screens. */
        fun fromId(id: String): AubreeState? = values().firstOrNull { it.id.equals(id, ignoreCase = true) }
    }
}
