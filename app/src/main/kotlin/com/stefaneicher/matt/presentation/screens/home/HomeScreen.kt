package com.stefaneicher.matt.presentation.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stefaneicher.matt.presentation.theme.MattTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToTasks: () -> Unit,
    onNavigateToPoints: () -> Unit,
    onNavigateToEvents: () -> Unit,
    onNavigateToCharacters: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("🌟 Matt – Kinderbonus") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Hallo! Was möchtest du tun?",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(24.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    HomeCard(
                        title = "Aufgaben",
                        icon = Icons.Default.CheckCircle,
                        onClick = onNavigateToTasks
                    )
                }
                item {
                    HomeCard(
                        title = "Punkte",
                        icon = Icons.Default.Star,
                        onClick = onNavigateToPoints
                    )
                }
                item {
                    HomeCard(
                        title = "Ereignisse",
                        icon = Icons.Default.Notifications,
                        onClick = onNavigateToEvents
                    )
                }
                item {
                    HomeCard(
                        title = "Charaktere",
                        icon = Icons.Default.Person,
                        onClick = onNavigateToCharacters
                    )
                }
            }
        }
    }
}

@Composable
private fun HomeCard(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(48.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 412, heightDp = 915)
@Composable
private fun HomeScreenPreview() {
    MattTheme {
        HomeScreen(
            onNavigateToTasks = {},
            onNavigateToPoints = {},
            onNavigateToEvents = {},
            onNavigateToCharacters = {}
        )
    }
}
