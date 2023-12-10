package br.ifsp.agenciacarros.repository

import androidx.lifecycle.LiveData
import br.ifsp.agenciacarros.data.Veiculo
import br.ifsp.agenciacarros.data.VeiculoDAO

class VeiculoRepository(private val veiculoDAO: VeiculoDAO) {

    suspend fun insert(veiculo: Veiculo) {
        veiculoDAO.insert(veiculo)
    }

    suspend fun update(veiculo: Veiculo) {
        veiculoDAO.update(veiculo)
    }

    suspend fun delete(veiculo: Veiculo) {
        veiculoDAO.delete(veiculo)
    }

    fun getAllContacts():LiveData<List<Veiculo>>{
        return veiculoDAO.getAllContacts()
    }

    fun getContactById(id: Int): LiveData<Veiculo> {
        return veiculoDAO.getContactById(id)
    }
}