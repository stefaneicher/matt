package com.stefaneicher.matt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.stefaneicher.matt.presentation.navigation.MattNavGraph
import com.stefaneicher.matt.presentation.theme.MattTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MattTheme {
                val navController = rememberNavController()
                MattNavGraph(navController = navController)
            }
        }
    }
}
