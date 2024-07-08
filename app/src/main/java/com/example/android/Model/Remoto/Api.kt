package com.example.android.Model.Remoto

import com.example.android.Model.Remoto.FromInternet.DetalleInternet
import com.example.android.Model.Remoto.FromInternet.ListaInternet
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
   @GET("conciertos")
   suspend fun fetchListaInternet(): Response<List<ListaInternet>>




   @GET("conciertos/{id}")
   suspend fun fetchDetalleInternet(@Path("id") id: Int): Response<DetalleInternet>


}