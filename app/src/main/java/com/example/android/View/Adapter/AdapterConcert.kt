package com.example.android.View.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.android.Model.Local.Entities.ListaLocal
import com.example.android.databinding.ListBinding


    class AdapterConcert: RecyclerView.Adapter<AdapterConcert.Viewholder>() {

        private var listaLocal = listOf<ListaLocal>()
        private val selectedConciertos = MutableLiveData<ListaLocal>()

        fun selectedConciertos(): LiveData<ListaLocal> = selectedConciertos


        inner class Viewholder (private val binding: ListBinding) :
            RecyclerView.ViewHolder(binding.root),
            View.OnClickListener {
            fun bind (conciertos: ListaLocal) {
                Glide.with(binding.imageRv).load(conciertos.imagen).centerCrop()
                    .into(binding.imageRv)
                binding.textNameRV.text = conciertos.artista
                binding.textTipoRV.text = conciertos.ciudad
                itemView.setOnClickListener(this)

            }

            override fun onClick(v: View?) {
                selectedConciertos.value = listaLocal[bindingAdapterPosition]
                Log.d("ONCLICK", bindingAdapterPosition.toString())
            }

        }

        fun actualizar(list: List<ListaLocal>) {
            listaLocal = list
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
            return Viewholder(ListBinding.inflate(LayoutInflater.from(parent.context)))
        }

        override fun onBindViewHolder(holder: Viewholder, position: Int) {
            holder.bind(listaLocal[position])
        }


        override fun getItemCount(): Int = listaLocal.size


    }





