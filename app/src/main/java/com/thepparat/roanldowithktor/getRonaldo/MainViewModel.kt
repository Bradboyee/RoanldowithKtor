package com.thepparat.roanldowithktor.getRonaldo

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thepparat.roanldowithktor.getRonaldo.data.api.RonaldoApi
import com.thepparat.roanldowithktor.getRonaldo.data.dto.Ronaldo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val ronaldoApi: RonaldoApi) : ViewModel() {

    data class RonaldoState(
        val ronaldo: Ronaldo? = null,
        val isLoading: Boolean = false,
    )
    init {
        getRandomRonaldo()
    }

    private val _state = mutableStateOf(RonaldoState())
    val state: State<RonaldoState> = _state

    fun getRandomRonaldo() {
        viewModelScope.launch {
                try {
                    val randomRonaldo = ronaldoApi.getRandomRonaldo()
                    Log.d(TAG,randomRonaldo.toString())
                    _state.value = state.value.copy(
                        isLoading = true
                    )
                    _state.value = state.value.copy(
                        ronaldo = randomRonaldo,
                        isLoading = false
                    )

                } catch(e: Exception) {
                    Log.e(TAG, "getRandomRabbit: ", e)
                    _state.value = state.value.copy(isLoading = false)
                }
        }
    }

    companion object {
        const val TAG = "MAIN VIEWMODEL"
    }
}