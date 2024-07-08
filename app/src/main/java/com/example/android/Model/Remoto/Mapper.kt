package com.example.android.Model.Remoto

import com.example.android.Model.Local.Entities.DetalleLocal
import com.example.android.Model.Local.Entities.ListaLocal
import com.example.android.Model.Remoto.FromInternet.DetalleInternet
import com.example.android.Model.Remoto.FromInternet.ListaInternet

fun fromInternetListaInternet(listaLocal: List<ListaInternet>): List<ListaLocal> {

    return listaLocal.map {
        ListaLocal(
            id = it.id,
            artista = it.artista,
            fecha = it.fecha,
            lugar = it.lugar,
            ciudad = it.ciudad,
            imagen = it.imagen,
            entradas = it.entradas

        )

    }
}

fun fromInternetDetalleInternet(detalleInternet: DetalleInternet):DetalleLocal{

    return DetalleLocal(

        id = detalleInternet.id,
        artista = detalleInternet.artista,
        fecha = detalleInternet.fecha,
        lugar = detalleInternet.lugar,
        ciudad = detalleInternet.ciudad,
        imagen = detalleInternet.imagen,
        entradas = detalleInternet.entradas

    )


}