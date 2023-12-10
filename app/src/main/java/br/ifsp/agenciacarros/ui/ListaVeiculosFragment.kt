package br.ifsp.agenciacarros.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.ifsp.agenciacarros.R
import br.ifsp.agenciacarros.VeiculoAdapter.VeiculoAdapter
import br.ifsp.agenciacarros.data.Veiculo
import br.ifsp.agenciacarros.databinding.FragmentListaVeiculosBinding
import br.ifsp.agenciacarros.viewmodel.VeiculoViewModel

class ListaVeiculosFragment: Fragment(){

    private var _binding: FragmentListaVeiculosBinding? = null
    private val binding get() = _binding!!
    private lateinit var veiculoAdapter: VeiculoAdapter
    private lateinit var viewModel: VeiculoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListaVeiculosBinding.inflate(inflater, container, false)
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_listaVeiculosFragment_to_cadastroFragment)
        }
        configureRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureRecyclerView()
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.main_menu, menu)

                val searchView = menu.findItem(R.id.action_search).actionView as SearchView
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                    override fun onQueryTextSubmit(p0: String?): Boolean {
                        TODO("Not yet implemented")
                    }

                    override fun onQueryTextChange(p0: String?): Boolean {
                        veiculoAdapter.filter.filter(p0)
                        return true
                    }
                })
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                TODO("Not yet implemented")
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }


    private fun configureRecyclerView(){
        viewModel= ViewModelProvider(this).get(VeiculoViewModel::class.java)
        viewModel.allContacts.observe(viewLifecycleOwner) {list ->
            list?.let{
                veiculoAdapter.updateList(list as ArrayList<Veiculo>)
            }
        }
        val recyclerView  = binding.recyclerview
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        veiculoAdapter = VeiculoAdapter()
        recyclerView.adapter = veiculoAdapter

        val listener = object : VeiculoAdapter.VeiculoListener {
            override fun onItemClick(pos: Int) {
                val v = veiculoAdapter.veiculosListaFilterable[pos]

                val bundle = Bundle()
                bundle.putInt("idVeiculo", v.id)

                findNavController().navigate(
                    R.id.action_listaVeiculosFragment_to_detalheFragment,
                    bundle
                )
            }
        }
        veiculoAdapter.setClickListener(listener)
    }
}
