package com.example.fitnessapp

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel

import com.example.fitnessapp.data.FitTips
import com.example.fitnessapp.data.FitsTipsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FitnessViewModel () : ViewModel(){

    private var repository : MutableList<FitTips> = FitsTipsRepository.tips
    private lateinit var list :  List<FitnessTipUI>

    init {
        getInfo()
    }


    private val _uiState = MutableStateFlow(list)
    val uiState : StateFlow<List<FitnessTipUI>>
        get() = _uiState.asStateFlow()


    fun updateStateItemUI(state : FitnessTipUI, boolean: Boolean){
        val updatedList = _uiState.value.toMutableList()
        val target = _uiState.value.indexOf(state)
        updatedList[target] = state.copy(expand = boolean)
        _uiState.value = updatedList

    }

    private fun getInfo(){
        repository = FitsTipsRepository.tips
        list = repository.map { tips ->
            FitnessTipUI(
                title = tips.title,
                imageRes = tips.imageRes,
                day = tips.day,
                description = tips.description,
            )
        }
    }
}