package com.trebronwayne.aubree.compose

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.trebronwayne.aubree.AubreeState
import com.trebronwayne.aubree.AubreeView

/**
 * Aubree for Jetpack Compose.
 *
 * ```kotlin
 * Aubree(state = AubreeState.WAIT, size = 160.dp)
 * ```
 *
 * Change `state` and the new loop starts from its first frame.
 *
 * @param contentDescription Overrides the built-in description. Leave null to use the default.
 */
@Composable
fun Aubree(
    state: AubreeState,
    modifier: Modifier = Modifier,
    size: Dp = 200.dp,
    contentDescription: String? = null,
) {
    AndroidView(
        modifier = modifier.size(size),
        factory = { context -> AubreeView(context) },
        update = { view ->
            view.state = state
            if (contentDescription != null) {
                view.contentDescription = contentDescription
            }
        },
    )
}
