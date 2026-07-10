package com.stefaneicher.matt.presentation.screens

import app.cash.paparazzi.DeviceConfig.Companion.PIXEL_5
import app.cash.paparazzi.Paparazzi
import com.stefaneicher.matt.presentation.screens.characters.CharactersScreen
import com.stefaneicher.matt.presentation.screens.home.HomeScreen
import com.stefaneicher.matt.presentation.theme.MattTheme
import org.junit.Rule
import org.junit.Test

class PaparazziPreviewTest {

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = PIXEL_5,
        theme = "android:Theme.Material.Light.NoActionBar"
    )

    @Test
    fun homeScreen() {
        paparazzi.snapshot {
            MattTheme(darkTheme = false) {
                HomeScreen(
                    onNavigateToTasks = {},
                    onNavigateToPoints = {},
                    onNavigateToEvents = {},
                    onNavigateToCharacters = {}
                )
            }
        }
    }

    @Test
    fun charactersScreen() {
        paparazzi.snapshot {
            MattTheme(darkTheme = false) {
                CharactersScreen(onBack = {})
            }
        }
    }
}
