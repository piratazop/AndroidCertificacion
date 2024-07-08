package com.example.android.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.android.Model.Local.Database.ConciertosDatabase
import com.example.android.Model.Local.Entities.DetalleLocal
import com.example.android.Model.Local.Entities.ListaLocal
import com.example.android.Model.Remoto.Repositorio
import kotlinx.coroutines.launch

class ConciertosViewModel (application: Application) : AndroidViewModel(application) {
    private val repository: Repositorio
    private var idSelected: Int = 0
    private val detalleLocalC = MutableLiveData<DetalleLocal>()

    init {
        val bd = ConciertosDatabase.getdatabase(application)
        val Dao = bd.getDao()
        repository = Repositorio(Dao)
        viewModelScope.launch {
            repository.fetchConcierto()
        }
    }
        fun getListaLocal(): LiveData<List<ListaLocal>> = repository.listaLocalLiveData

        fun getDetalleLocalByIdFromInternet(id: Int) = viewModelScope.launch {

            val detalle = repository.fetchDetalleLocal(id)
            detalle?.let {
                detalleLocalC.postValue(it)
            }


        }

        fun getDetalleLocal(): LiveData<DetalleLocal> = detalleLocalC



}
