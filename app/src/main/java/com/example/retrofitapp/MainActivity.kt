// MainActivity.kt
package com.example.retrofitapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.retrofitapp.api.CharacterDetailsScreen
import com.example.retrofitapp.api.CharacterListScreen
import com.example.retrofitapp.ui.theme.RetrofitAppTheme
import com.example.retrofitapp.viewmodel.APIViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RetrofitAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    AppContent()
                }
            }
        }
    }
}

@Composable
fun AppContent() {
    val navController = rememberNavController()
    val viewModel: APIViewModel = viewModel()

    RetrofitAppTheme {
        Surface(color = MaterialTheme.colors.background) {
            MainContent(navController = navController, viewModel = viewModel)
        }
    }
}
