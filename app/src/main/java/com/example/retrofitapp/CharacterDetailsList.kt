package com.example.retrofitapp.api

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.retrofitapp.model.Character


@Composable
fun CharacterDetailsScreen(character: Character) {
    var assignFavourite:Boolean=false
    Button(onClick = {assignFavourite=true}) {
        if (assignFavourite){
            println("Assigned to favoutite")
        }
    }
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Character Details",
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "Name: ${character.name}",
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Description: ${character.uniqueTraits}",
        )
        Text(
            text = "Jutsu: ${character.jutsu}",
        )
    }
}
