package com.example.testespessoais.collections

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.testespessoais.R
import com.example.testespessoais.core.database.AppDataBase
import com.example.testespessoais.core.repository.CorRepository
import com.example.testespessoais.core.repository.CorRepositoryImpl
import com.example.testespessoais.databinding.FragmentCadastroBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import java.util.UUID

class CadastroFragment : Fragment() {

    private var _binding: FragmentCadastroBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CadastroViewModel by activityViewModels {
        val repositoryCor : CorRepository = CorRepositoryImpl(appDataBase = AppDataBase.getInstance(requireContext()))
        CadastroViewModel.Factory(repositoryCor)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCadastroBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.voltarBtnCadastro.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.cadastrarBtnCadastro.setOnClickListener {
            val itemNovo = CorItem(
                idCor = UUID.randomUUID().toString(),
                nomeCor = binding.nomeCorTextInput.editableText.toString(),
                hexCor = binding.codigoHexTextInput.editableText.toString()
            )
            val corrotina = MainScope()
            corrotina.async {
                val resultado = async { viewModel.cadastrarNovoItem(itemNovo) }
                resultado.await()
                if(resultado != null)
                    findNavController().navigateUp()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}