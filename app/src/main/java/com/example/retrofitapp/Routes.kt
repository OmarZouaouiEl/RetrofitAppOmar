package com.example.retrofitapp

sealed class Routes(val route: String) {
    object MainActivity:Routes("main_activity")
    object CharacterDetailsList:Routes("Character_Details_List")
    object Pantalla3:Routes("pantalla3")
}
