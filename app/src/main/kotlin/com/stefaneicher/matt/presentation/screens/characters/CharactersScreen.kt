package com.stefaneicher.matt.presentation.screens.characters

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.stefaneicher.matt.domain.model.Character
import com.stefaneicher.matt.presentation.theme.MattTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersScreen(onBack: () -> Unit) {
    val characters = sampleCharacters()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Charaktere") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Zurück")
                    }
                }
            )
        }
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(characters) { character ->
                CharacterCard(character = character)
            }
        }
    }
}

@Composable
fun CharacterCard(character: Character) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(character.avatarResKey, style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(character.name, style = MaterialTheme.typography.titleLarge)
            Text(character.description, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(4.dp))
            AssistChip(
                onClick = {},
                label = { Text("Ab ${character.unlockedAtPoints} ⭐") }
            )
        }
    }
}

private fun sampleCharacters() = listOf(
    Character("1", "Sternenritter", "Mutig und stark!", "⚔️", 0),
    Character("2", "Feuerfuchs", "Schnell wie der Wind!", "🦊", 20),
    Character("3", "Wasserzauberin", "Weise und geduldig!", "🧙‍♀️", 50),
    Character("4", "Drachenwächter", "Hüter aller Schätze!", "🐉", 100)
)

@Preview(showBackground = true, widthDp = 412, heightDp = 915)
@Composable
private fun CharactersScreenPreview() {
    MattTheme {
        CharactersScreen(onBack = {})
    }
}
