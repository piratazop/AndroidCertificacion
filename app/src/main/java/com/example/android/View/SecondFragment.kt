package com.example.android.View

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.android.R
import com.example.android.ViewModel.ConciertosViewModel
import com.example.android.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val viewModel: ConciertosViewModel by activityViewModels()
    private var conciertosId: Int = 0
    private var artista: String = ""

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { bundle ->
            conciertosId = bundle.getInt("conciertosId")
            artista = bundle.getString("artista").toString()

        }

        conciertosId.let {id->
            viewModel.getDetalleLocalByIdFromInternet(id)

        }

        viewModel.getDetalleLocal().observe(viewLifecycleOwner, Observer {
            Log.d("DetalleLocal", "DetalleLocal")

            Glide.with(binding.imageRv).load(it.imagen).into(binding.imageRv)

            binding.textNameRV.text = "Nombre:${it.artista}"
            binding.textTipoRV.text = "Ciudad:${it.ciudad}"

            val url = it.entradas



            binding.btnConciertos.setOnClickListener {
                Log.d("BUTTON", "Click")
                val mintent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(url)

                }
               startActivity(mintent)



            }

        })

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}