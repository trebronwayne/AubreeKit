package com.trebronwayne.aubree

import android.content.Context
import android.graphics.ImageDecoder
import android.graphics.drawable.AnimatedImageDrawable
import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import android.os.Build
import android.provider.Settings
import android.util.AttributeSet
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.core.content.withStyledAttributes

/**
 * A view that shows one Aubree animation.
 *
 * ```xml
 * <com.trebronwayne.aubree.AubreeView
 *     android:layout_width="160dp"
 *     android:layout_height="160dp"
 *     app:aubreeState="wait" />
 * ```
 *
 * ```kotlin
 * binding.aubree.state = AubreeState.HAPPY
 * ```
 *
 * On Android 9 (API 28) and above the animation plays and loops. On older versions, and when the
 * user has turned system animations off, a still picture of the same state is shown instead.
 * Playback starts and stops with the view's window, so it does not drain battery in the background.
 */
class AubreeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ImageView(context, attrs, defStyleAttr) {

    private var shown: AubreeState? = null
    private var autoDescription: CharSequence? = null

    /** The state on screen. Setting it swaps the picture and restarts the loop from the first frame. */
    var state: AubreeState = AubreeState.HAPPY
        set(value) {
            field = value
            if (shown != value) {
                shown = value
                show(value)
            }
        }

    init {
        scaleType = ScaleType.FIT_CENTER
        adjustViewBounds = true
        var initial = AubreeState.HAPPY
        context.withStyledAttributes(attrs, R.styleable.AubreeView, defStyleAttr, 0) {
            val index = getInt(R.styleable.AubreeView_aubreeState, 0)
            initial = AubreeState.values().getOrElse(index) { AubreeState.HAPPY }
        }
        state = initial
    }

    /** Starts the loop. Called for you when the view is attached to a window. */
    fun play() {
        (drawable as? Animatable)?.start()
    }

    /** Stops the loop. Called for you when the view is detached from a window. */
    fun pause() {
        (drawable as? Animatable)?.stop()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        play()
    }

    override fun onDetachedFromWindow() {
        pause()
        super.onDetachedFromWindow()
    }

    private fun show(target: AubreeState) {
        val animated: Drawable? =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P && animationsAllowed()) {
                Api28.decode(this, target.animationRes)
            } else {
                null
            }

        if (animated != null) {
            setImageDrawable(animated)
            if (isAttachedToWindow) play()
        } else {
            setImageResource(target.stillRes)
        }

        // Give screen readers a description, unless the app already set its own.
        val current = contentDescription
        if (current == null || current == autoDescription) {
            autoDescription = context.getString(target.descriptionRes)
            contentDescription = autoDescription
        }
    }

    private fun animationsAllowed(): Boolean = try {
        Settings.Global.getFloat(context.contentResolver, Settings.Global.ANIMATOR_DURATION_SCALE, 1f) != 0f
    } catch (e: Exception) {
        true
    }

    @RequiresApi(Build.VERSION_CODES.P)
    private object Api28 {
        fun decode(view: ImageView, @DrawableRes res: Int): Drawable? = try {
            val source = ImageDecoder.createSource(view.resources, res)
            val drawable = ImageDecoder.decodeDrawable(source)
            if (drawable is AnimatedImageDrawable) {
                drawable.repeatCount = AnimatedImageDrawable.REPEAT_INFINITE
            }
            drawable
        } catch (e: Exception) {
            null
        }
    }
}
