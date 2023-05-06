package com.example.testespessoais.collections

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.testespessoais.core.repository.CorRepository
import kotlinx.coroutines.launch
import androidx.navigation.fragment.findNavController
import com.example.testespessoais.core.database.entity.Cor
import java.util.*

class CadastroViewModel(
    private val repository: CorRepository
    ) : ViewModel(){

    suspend fun cadastrarNovoItem(itemNovo: CorItem) : CorItem? {
        viewModelScope.launch {
            repository.postNewColor(corItem = itemNovo)
        }
        return repository.getListaCores().lastOrNull()
    }


    class Factory(private val repository: CorRepository): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CadastroViewModel(repository) as T
        }
    }


}