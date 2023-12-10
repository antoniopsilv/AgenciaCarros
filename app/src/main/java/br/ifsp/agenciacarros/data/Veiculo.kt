package br.ifsp.agenciacarros.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Veiculo (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var marca: String,
    var modelo: String,
    var ano: String,
    var chat: String,
    var preco: String?
)
