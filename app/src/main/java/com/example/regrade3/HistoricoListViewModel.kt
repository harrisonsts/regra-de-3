package com.example.regrade3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.regrade3.core.HistoricoRepository

class HistoricoListViewModel(private val repository: HistoricoRepository) : ViewModel() {

    private val uiState: MutableLiveData<HistoricoListUiState> by lazy {
        MutableLiveData<HistoricoListUiState>(HistoricoListUiState(historicoItemList = repository.fetchHistorico()))
    }

    fun stateOnceAndStream(): LiveData<HistoricoListUiState> = uiState

    private fun refreshUiState(){
        uiState.value.let { currentUiState ->
            uiState.value = currentUiState.copy(
                historicoItemList = repository.fetchHistorico()
            )
        }
    }

    class Factory(private val repository: HistoricoRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass : Class<T>): T {
            return HistoricoListViewModel(repository) as T
        }
    }
}