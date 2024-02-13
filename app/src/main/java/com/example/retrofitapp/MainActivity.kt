package com.example.retrofitapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.retrofitapp.api.CharacterDetailsScreen
import com.example.retrofitapp.api.CharacterListScreen
import com.example.retrofitapp.viewmodel.APIViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val myViewModel by viewModels<APIViewModel>()
        setContent {
            RetrofitAppTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainContent(myViewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(myViewModel: APIViewModel) {
    val navController = rememberNavController()

    val bottomNavItems = listOf(
        BottomNavItem("Characters", "characters", painterResource(id = R.drawable.ic_characters)),
        BottomNavItem("Favorites", "favorites", painterResource(id = R.drawable.ic_favorites))
    )

    Scaffold(
        bottomBar = {
            BottomAppBar(
                backgroundColor = MaterialTheme.colors.primarySurface
            ) {
                BottomNavigation {
                    bottomNavItems.forEach { item ->
                        BottomNavigationItem(
                            selected = false,
                            onClick = { navController.navigate(item.route) },
                            icon = {
                                Icon(
                                    painter = item.icon,
                                    contentDescription = item.label,
                                    tint = MaterialTheme.colors.onPrimary
                                )
                            },
                            label = { Text(text = item.label) }
                        )
                    }
                }
            }
        }
    ) {
        NavHost(navController, startDestination = "characters") {
            composable("characters") {
                CharacterListScreen(
                    characters = myViewModel.characters
                ) { character ->
                    navController.navigate("details/${character.id}")
                }
            }
            composable(
                "details/{characterId}",
                arguments = listOf(navArgument("characterId") { type = NavType.IntType })
            ) { backStackEntry ->
                val characterId = backStackEntry.arguments?.getInt("characterId")
                if (characterId != null) {
                    val character = myViewModel.characters.find { it.id == characterId }
                    if (character != null) {
                        CharacterDetailsScreen(character = character)
                    }
                }
            }
            // Add more composable destinations as needed
        }
    }
}

data class BottomNavItem(val label: String, val route: String, val icon: Painter)
