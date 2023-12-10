package br.ifsp.agenciacarros.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface  VeiculoDAO {

    @Insert
    suspend fun insert(veiculo: Veiculo)

    @Update
    suspend fun update(veiculo: Veiculo)

    @Delete
    suspend fun delete(veiculo: Veiculo)

    @Query("SELECT * FROM veiculo ORDER BY marca")
    fun getAllContacts(): LiveData<List<Veiculo>>

    @Query("SELECT * FROM veiculo WHERE id=:id")
    fun getContactById(id: Int): LiveData<Veiculo>


}
