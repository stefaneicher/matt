package com.stefaneicher.matt.presentation.screens.events

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.*
import com.stefaneicher.matt.R
import com.stefaneicher.matt.domain.model.FamilyEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventScrollScreen(
    onBack: () -> Unit,
    viewModel: EventScrollViewModel = hiltViewModel()
) {
    val events by viewModel.unplayedEvents.collectAsState()
    val currentEvent by viewModel.currentPlayingEvent.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ereignisse") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Zurück")
                    }
                },
                actions = {
                    if (events.isNotEmpty()) {
                        TextButton(onClick = viewModel::onPlayAll) {
                            Text("Alle abspielen")
                        }
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Animation overlay for current event
            currentEvent?.let { event ->
                EventAnimationOverlay(
                    event = event,
                    onAnimationComplete = viewModel::onAnimationComplete
                )
            }

            if (events.isEmpty() && currentEvent == null) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("🎉", style = MaterialTheme.typography.headlineLarge)
                        Text("Alle Ereignisse abgespielt!")
                    }
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(events) { event ->
                        EventCard(
                            event = event,
                            onPlay = { viewModel.onPlayEvent(event) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EventAnimationOverlay(
    event: FamilyEvent,
    onAnimationComplete: () -> Unit
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.animation_reward)
    )
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = 1
    )

    LaunchedEffect(progress) {
        if (progress == 1f) onAnimationComplete()
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieAnimation(
                composition = composition,
                progress = { progress },
                modifier = Modifier.size(200.dp)
            )
            Text(event.title, style = MaterialTheme.typography.headlineMedium)
            Text(event.description, style = MaterialTheme.typography.bodyLarge)
            val sign = if (event.pointsDelta >= 0) "+" else ""
            Text(
                "$sign${event.pointsDelta} Punkte",
                style = MaterialTheme.typography.titleLarge,
                color = if (event.pointsDelta >= 0)
                    MaterialTheme.colorScheme.tertiary
                else
                    MaterialTheme.colorScheme.error
            )
        }
    }
}

@Composable
fun EventCard(event: FamilyEvent, onPlay: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(event.title, style = MaterialTheme.typography.titleLarge)
                Text(event.description, style = MaterialTheme.typography.bodyLarge)
            }
            val sign = if (event.pointsDelta >= 0) "+" else ""
            Text(
                "$sign${event.pointsDelta}⭐",
                style = MaterialTheme.typography.titleLarge,
                color = if (event.pointsDelta >= 0)
                    MaterialTheme.colorScheme.tertiary
                else
                    MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(onClick = onPlay) {
                Icon(
                    Icons.Default.PlayArrow,
                    contentDescription = "Abspielen"
                )
            }
        }
    }
}
