package br.ifsp.agenciacarros.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import br.ifsp.agenciacarros.R
import br.ifsp.agenciacarros.data.Veiculo
import br.ifsp.agenciacarros.databinding.FragmentDetalheBinding
import br.ifsp.agenciacarros.viewmodel.VeiculoViewModel
import com.google.android.material.snackbar.Snackbar
import androidx.navigation.fragment.findNavController

class DetalheFragment : Fragment() {

    private var _binding: FragmentDetalheBinding? = null
    private val binding get() = _binding!!

    lateinit var veiculo: Veiculo

    lateinit var marcaEditText: EditText
    lateinit var modeloEditText: EditText
    lateinit var anoEditText: EditText
    lateinit var precoEditText: EditText
    lateinit var chatEditText: EditText


    lateinit var viewModel: VeiculoViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(VeiculoViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetalheBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        marcaEditText = binding.commonLayout.editTextMarca
        modeloEditText = binding.commonLayout.editTextModelo
        anoEditText = binding.commonLayout.editTextAno
        precoEditText = binding.commonLayout.editTextPreco
        chatEditText = binding.commonLayout.editTextChat

        val idVeiculo = requireArguments().getInt("idVeiculo")

        viewModel.getContactById(idVeiculo)
        viewModel.veiculo.observe(viewLifecycleOwner) { result ->
            result?.let {
                veiculo = result

                marcaEditText.setText(veiculo.marca)
                modeloEditText.setText(veiculo.modelo)
                anoEditText.setText(veiculo.ano)
                precoEditText.setText(veiculo.preco)
                chatEditText.setText(veiculo.chat)

            }
        }
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.detalhe_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: android.view.MenuItem): Boolean {

                return when (menuItem.itemId) {
                    R.id.action_alterarContato -> {
                        veiculo.marca = marcaEditText.text.toString()
                        veiculo.modelo = modeloEditText.text.toString()
                        veiculo.ano = anoEditText.text.toString()
                        veiculo.preco = precoEditText.text.toString()
                        veiculo.chat = chatEditText.text.toString()

                        viewModel.update(veiculo)
                        Snackbar.make(binding.root, "Veiculo alterado", Snackbar.LENGTH_SHORT)
                            .show()
                        findNavController().popBackStack()
                        true
                    }
                    R.id.action_excluirContato -> {

                        viewModel.delete(veiculo)
                        Snackbar.make(binding.root, "Veiculo apagado", Snackbar.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}
