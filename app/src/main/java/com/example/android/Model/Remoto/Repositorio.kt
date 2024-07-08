package com.example.android.Model.Remoto

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.android.Model.Local.Dao.Dao
import com.example.android.Model.Local.Entities.DetalleLocal



    class Repositorio(private val Dao: Dao) {
        private val networkService = Retrofit.getRetrofit()

        val listaLocalLiveData = Dao.getAllConciertos()


        suspend fun fetchConcierto() {
            val service = kotlin.runCatching { networkService.fetchListaInternet()}
            service.onSuccess {

                when (it.code()) {
                    in 200..299 -> it.body()?.let {

                        Log.d("Flowers", it.toString())

                       Dao.insertAllConcientos(fromInternetListaInternet(it))
                    }

                    else -> Log.d("Repo", "${it.code()}-${it.errorBody()}")
                }


            }
            service.onFailure {
                Log.e("Error", "${it.message}")
            }
        }
        suspend fun fetchDetalleLocal(id:Int):DetalleLocal?{
            val service = kotlin.runCatching { networkService.fetchDetalleInternet(id)}

            return service.getOrNull()?.body()?.let {details ->

                val detalleLocal = fromInternetDetalleInternet(details)
               Dao.insertDetalleLocal(detalleLocal)
                detalleLocal
            }
        }
        fun getDetalleLocalById(id:Int): LiveData<DetalleLocal> {
            return Dao.getDetalleLocalById(id)

        }









}