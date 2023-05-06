package com.example.testespessoais.collections

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.testespessoais.R
import com.example.testespessoais.core.database.AppDataBase
import com.example.testespessoais.core.repository.CorRepository
import com.example.testespessoais.core.repository.CorRepositoryImpl
import com.example.testespessoais.databinding.FragmentHomeBinding
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by activityViewModels {
        val repositoryCor : CorRepository = CorRepositoryImpl(appDataBase = AppDataBase.getInstance(requireContext()))
        val getCoresUseCase : GetCoresUseCase = GetCoresUseCaseImpl(repositoryCor)
        HomeViewModel.Factory(getCoresUseCase,repositoryCor)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val corrotina = MainScope()
        corrotina.async {
            viewModel.adicionarPrimeiraCor()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel
            .uiState()
            .observe(viewLifecycleOwner){state ->
            binding.nomeCor.text = state.valorCor.nomeCor
            val color: Int = Color.parseColor(state.valorCor.hexCor)
            binding.raio.setColorFilter(color)
        }

        binding.mudarCor.setOnClickListener(View.OnClickListener {
           val corrotina = MainScope()
            corrotina.async { viewModel.mudarCor() }
        })
        binding.cadastrarNovaCorHome.setOnClickListener(View.OnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_cadastroFragment)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}