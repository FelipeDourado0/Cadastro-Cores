package com.example.testespessoais.collections

import androidx.lifecycle.*
import com.example.testespessoais.core.repository.CorRepository
import kotlinx.coroutines.launch
import java.util.UUID

class HomeViewModel(
    private val getCoresUseCase: GetCoresUseCase,
    private val repository: CorRepository
    ) : ViewModel(){
    private val stateColor: MutableLiveData<CorUiState> by lazy {
        MutableLiveData<CorUiState>()
    }

    fun onResume(){
        viewModelScope.launch {
            refreshUiState()
        }
    }

    fun uiState(): LiveData<CorUiState> = stateColor

    suspend fun mudarCor(){
        viewModelScope.launch {
            stateColor.value = CorUiState(valorCor = repository.getRandomColor())
        }
        //refreshUiState()
    }

    suspend fun adicionarPrimeiraCor(){
        viewModelScope.launch {
            if(repository.getListaCores().isNotEmpty()){
               repository.postNewColor(CorItem(
                   idCor = UUID.randomUUID().toString(),
                   nomeCor = "Preto",
                   hexCor = "#000000")
               )
            }
        }
    }

    private suspend fun refreshUiState(){
        stateColor.value?.let { currentUi ->
            stateColor.value = currentUi.copy(
                valorCor =  repository.getRandomColor()
            )
        }
    }

    class Factory(private val getCoresUseCase: GetCoresUseCase, private val repository: CorRepository): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(getCoresUseCase, repository) as T
        }
    }
}