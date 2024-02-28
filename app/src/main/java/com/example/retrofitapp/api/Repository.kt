// Repository.kt
package com.example.retrofitapp.api

import com.example.retrofitapp.model.Data

class Repository {
    private val apiInterface = APIInterface.create()

    suspend fun getAllCharacters(): retrofit2.Response<Data> {
        return apiInterface.getCharacters()
    }
}
