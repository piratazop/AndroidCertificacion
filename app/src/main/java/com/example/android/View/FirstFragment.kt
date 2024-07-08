package com.example.android.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.example.android.R
import com.example.android.ViewModel.ConciertosViewModel
import com.example.android.databinding.FragmentFirstBinding
import kotlin.jvm.internal.AdaptedFunctionReference
import com.example.android.View.Adapter.AdapterConcert

class FirstFragment : Fragment() {

    private  var _binding: FragmentFirstBinding?= null
    private val viewModel: ConciertosViewModel by activityViewModels()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = AdapterConcert()
        binding.RvList.adapter = adapter
        binding.RvList.layoutManager = GridLayoutManager(context, 2)

        viewModel.getListaLocal().observe(viewLifecycleOwner) {

            it.let {
                Log.d("conciertosid", it.toString())
                adapter.actualizar(it)
            }
            //metodo para pasar al detalle del recycler
        }
        adapter.selectedConciertos().observe(viewLifecycleOwner) {
            it.let {
                Log.d("Seleccionado", it.toString())
                viewModel.getDetalleLocalByIdFromInternet(it.id)

            }
            val bundle = Bundle().apply {
                putInt("conciertosId", it.id)
                putString("artista", it.artista)
            }
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }
}



