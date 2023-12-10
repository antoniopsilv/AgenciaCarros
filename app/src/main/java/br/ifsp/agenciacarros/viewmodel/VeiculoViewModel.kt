package br.ifsp.agenciacarros.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import br.ifsp.agenciacarros.data.Veiculo
import br.ifsp.agenciacarros.data.VeiculoDatabase
import br.ifsp.agenciacarros.repository.VeiculoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VeiculoViewModel(application: Application): AndroidViewModel(application) {
    private val repository: VeiculoRepository
    var allContacts: LiveData<List<Veiculo>>
    lateinit var veiculo: LiveData<Veiculo>


    init {
        val dao = VeiculoDatabase.getDatabase(application).veiculoDAO()
        repository = VeiculoRepository(dao)
        allContacts = repository.getAllContacts()
    }

    fun insert(veiculo: Veiculo) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(veiculo)
    }

    fun update(veiculo: Veiculo) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(veiculo)
    }

    fun delete(veiculo: Veiculo) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(veiculo)
    }
    fun getContactById(id:Int) {
        viewModelScope.launch {
            veiculo=repository.getContactById(id)
        }
    }

}