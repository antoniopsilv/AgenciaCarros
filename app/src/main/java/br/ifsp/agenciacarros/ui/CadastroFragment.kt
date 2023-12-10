package br.ifsp.agenciacarros.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.ifsp.agenciacarros.R
import br.ifsp.agenciacarros.data.Veiculo
import br.ifsp.agenciacarros.databinding.FragmentCadastroBinding
import br.ifsp.agenciacarros.viewmodel.VeiculoViewModel
import com.google.android.material.snackbar.Snackbar


class CadastroFragment : Fragment(){
    private var _binding: FragmentCadastroBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: VeiculoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(VeiculoViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCadastroBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.cadastro_menu, menu)
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                return when (menuItem.itemId) {
                    R.id.action_salvarContato -> {

                        val marca = binding.commonLayout.editTextMarca.text.toString()
                        val modelo = binding.commonLayout.editTextModelo.text.toString()
                        val ano = binding.commonLayout.editTextAno.text.toString()
                        val preco = binding.commonLayout.editTextPreco.text.toString()
                        val chat = binding.commonLayout.editTextChat.text.toString()

                        val veiculo = Veiculo(0,marca ,modelo ,ano ,chat ,preco )

                        viewModel.insert(veiculo)

                        Snackbar.make(binding.root, "Veiculo inserido", Snackbar.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}