package com.example.mylibrary

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import org.junit.Rule
import org.junit.Test

class ComposeTest {
    // make the screen only 1x1 big and use the expanding rendering mode to expand only as needed for a better diff
    private val deviceConfig = DeviceConfig.NEXUS_5.copy(softButtons = false)//, screenHeight = 1, screenWidth = 1)
    private val defaultStartEndPadding = 24.dp

    @get:Rule
    val paparazzi = Paparazzi(
        maxPercentDifference = 0.1,
        deviceConfig = deviceConfig,
        renderingMode = SessionParams.RenderingMode.FULL_EXPAND,
    )

    fun snapshot(content: @Composable () -> Unit) {
        paparazzi.snapshot {
            /**
             * We need a limiting box for components which use the [fillMaxWidth] modifier.
             * To do so, add a Box and use [DeviceConfig.xdpi] of [deviceConfig] as a fixed width
             * but subtract [defaultStartEndPadding] two times (start and end) for a more realistic example.
             */
            Box(modifier = Modifier.width(deviceConfig.xdpi.dp - (defaultStartEndPadding * 2))) {
                content()
            }
        }
    }

    @Test
    fun test_DefaultPreview() {
        snapshot { DefaultPreview() }
    }
}
