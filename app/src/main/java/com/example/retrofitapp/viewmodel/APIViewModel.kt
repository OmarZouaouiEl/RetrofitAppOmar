// APIViewModel.kt
package com.example.retrofitapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitapp.api.Repository
import com.example.retrofitapp.model.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class APIViewModel : ViewModel() {

    private val repository = Repository()
    val characters: MutableLiveData<List<Character>> = MutableLiveData()

    init {
        getCharacters()
    }

    private fun getCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getAllCharacters()
            if (response.isSuccessful) {
                characters.postValue(response.body()?.characters ?: emptyList())
            }
        }
    }
}
