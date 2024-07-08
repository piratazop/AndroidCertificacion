package com.example.android.Model.Local.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.Model.Local.Entities.DetalleLocal
import com.example.android.Model.Local.Entities.ListaLocal


@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllConcientos(listaLocal: List<ListaLocal>)

    @Query("SELECT * FROM List_Conciertos ORDER BY id ASC")
    fun getAllConciertos(): LiveData<List<ListaLocal>>//live dsta para observar los resultados

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetalleLocal(detalleLocal: DetalleLocal)

    @Query("SELECT * FROM Detalle_Conciertos WHERE id = :id")
    fun getDetalleLocalById(id: Int): LiveData<DetalleLocal>



}