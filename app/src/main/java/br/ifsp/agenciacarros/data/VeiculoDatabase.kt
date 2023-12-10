package br.ifsp.agenciacarros.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



@Database(entities = [Veiculo::class], version = 1)
abstract class VeiculoDatabase: RoomDatabase() {

    abstract fun veiculoDAO(): VeiculoDAO

    companion object{
        @Volatile
        private var INSTANCE: VeiculoDatabase? = null

        fun getDatabase(context: Context): VeiculoDatabase {
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VeiculoDatabase::class.java,
                    "agenciaCarRoom.db"
                ).build()
                INSTANCE=instance
                instance
            }

        }

    }
}
