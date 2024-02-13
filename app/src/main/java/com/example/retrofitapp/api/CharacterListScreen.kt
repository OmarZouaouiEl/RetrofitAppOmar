package com.example.retrofitapp.api

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import com.example.retrofitapp.model.Character
import com.example.retrofitapp.model.Data

@Composable
fun CharacterListScreen(
    characters: MutableLiveData<Data>,
    onCharacterClick: (Character) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Character List",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LazyColumn {
            items(  characters) { character ->
                CharacterListItem(character = character, onCharacterClick)
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
                style = MaterialTheme.typography.subtitle1
            )
            Text(
                text = character.description,
                style = MaterialTheme.typography.body2
            )
        }
    }
}
