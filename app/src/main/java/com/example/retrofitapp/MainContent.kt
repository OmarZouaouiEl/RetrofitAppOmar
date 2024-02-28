// MainContent.kt
package com.example.retrofitapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomNavigation
import androidx.compose.material3.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.retrofitapp.api.CharacterDetailsScreen
import com.example.retrofitapp.viewmodel.APIViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(navController: NavController, viewModel: APIViewModel) {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White
            ) {
                BottomNavigation {
                    val bottomNavItems = listOf(
                        BottomNavItem(
                            label = "Characters",
                            route = "characters",
                            icon = painterResource(id = R.drawable.ic_characters)
                        ),
                        BottomNavItem(
                            label = "Favorites",
                            route = "favorites",
                            icon = painterResource(id = R.drawable.ic_favorites)
                        )
                    )
                    bottomNavItems.forEach { item ->
                        BottomNavigationItem(
                            selected = false,
                            onClick = { navController.navigate(item.route) },
                            icon = {
                                Icon(
                                    painter = item.icon,
                                    contentDescription = item.label
                                )
                            },
                        )
                    }
                }
            }
        }
    ) {
        NavHost(navController = navController, startDestination = "characters") {
            composable(
                route = "characters"
            ) {
                CharacterListScreen(
                    characters = viewModel.characters.value ?: emptyList(),
                    onCharacterClick = { character ->
                        navController.navigate("details/${character.id}")
                    }
                )
            }
            composable(
                route = "details/{characterId}",
                arguments = listOf(navArgument("characterId") { type = NavType.IntType })
            ) { backStackEntry ->
                val characterId = backStackEntry.arguments?.getInt("characterId")
                if (characterId != null) {
                    val character =
                        viewModel.characters.value?.find { it.id == characterId }
                    if (character != null) {
                        CharacterDetailsScreen(character = character)
                    }
                }
            }
        }
    }
}

data class BottomNavItem(val label: String, val route: String, val icon: Painter)
