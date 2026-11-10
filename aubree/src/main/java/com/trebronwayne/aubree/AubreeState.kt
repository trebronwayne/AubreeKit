package com.trebronwayne.aubree

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * The twelve things Aubree can say. Order matches the `aubreeState` enum in attrs.xml.
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
    WELCOME("welcome", R.drawable.aubree_welcome, R.drawable.aubree_welcome_still, R.string.aubree_desc_welcome);

    companion object {
        /** Finds a state by its [id], or returns null. Handy for server-driven screens. */
        fun fromId(id: String): AubreeState? = values().firstOrNull { it.id.equals(id, ignoreCase = true) }
    }
}
