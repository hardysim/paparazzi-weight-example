package com.example.mylibrary

import androidx.compose.runtime.Composable
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import org.junit.Rule
import org.junit.Test

class ComposeTest {
    private val deviceConfig = DeviceConfig.NEXUS_5

    @get:Rule
    open val paparazzi = Paparazzi(
        theme = "AppTheme",
        maxPercentDifference = 0.1,
        deviceConfig = deviceConfig,
        renderingMode = SessionParams.RenderingMode.FULL_EXPAND,
    )

    fun snapshot(content: @Composable () -> Unit) {
        paparazzi.snapshot {
            content()
        }
    }

    @Test
    fun test_DefaultPreview() {
        snapshot { DefaultPreview() }
    }
}
