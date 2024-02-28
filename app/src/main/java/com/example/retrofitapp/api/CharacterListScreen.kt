// CharacterListScreen.kt
package com.example.retrofitapp.api

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.retrofitapp.model.Character

@Composable
fun CharacterListScreen(
    characters: List<Character>,
    onCharacterClick: (Character) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Character List",
            modifier = Modifier.padding(16.dp)
        )
        LazyColumn {
            items(characters) { character ->
                CharacterListItem(character = character, onCharacterClick = onCharacterClick)
            }
        }
    }
}

@Composable
fun CharacterListItem(
    character: Character,
    onCharacterClick: (Character) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCharacterClick(character) }
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = character.name,
                style = MaterialTheme.typography.h6
            )
            Text(
                text = character.uniqueTraits.joinToString(", "),
                style = MaterialTheme.typography.body2
            )
        }
    }
}
