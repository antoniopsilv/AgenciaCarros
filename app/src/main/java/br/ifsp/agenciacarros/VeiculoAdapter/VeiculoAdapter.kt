package br.ifsp.agenciacarros.VeiculoAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import br.ifsp.agenciacarros.data.Veiculo
import br.ifsp.agenciacarros.databinding.VeiculoCelulaBinding
import com.google.android.material.snackbar.Snackbar

class VeiculoAdapter():
RecyclerView.Adapter<VeiculoAdapter.VeiculoViewHolder>(), Filterable {

    var listener: VeiculoListener?=null

    var veiculosLista = ArrayList<Veiculo>()
    var veiculosListaFilterable = ArrayList<Veiculo>()

    private lateinit var binding: VeiculoCelulaBinding

    fun updateList(newList:ArrayList<Veiculo>){
        veiculosLista= newList
        veiculosListaFilterable = veiculosLista
        notifyDataSetChanged()
    }

    fun setClickListener(listener: VeiculoListener) {
        this.listener = listener
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VeiculoViewHolder {
        binding = VeiculoCelulaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VeiculoViewHolder(binding)
    }
    override fun onBindViewHolder(holder: VeiculoViewHolder, position: Int) {
        holder.marcaVH.text = veiculosLista[position].marca
        holder.modeloVH.text = veiculosLista[position].modelo
        holder.anoVH.text = veiculosLista[position].ano
    }

    override fun getItemCount(): Int {
        return veiculosListaFilterable.size
    }

    inner class VeiculoViewHolder(view:VeiculoCelulaBinding): RecyclerView.ViewHolder(view.root)
    {
        val marcaVH = view.marca
        val modeloVH = view.modelo
        val anoVH = view.ano

        init {
            view.root.setOnClickListener{
                listener?.onItemClick(adapterPosition)
            }
        }
    }

    interface VeiculoListener
    {
        fun onItemClick(pos: Int)
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(p0: CharSequence?): FilterResults {
                if (p0.toString().isEmpty())
                    veiculosListaFilterable = veiculosLista
                else
                {
                    val resultList = ArrayList<Veiculo>()
                    for (row in veiculosLista)
                        if (row.modelo.lowercase().contains(p0.toString().lowercase()))
                            resultList.add(row)
                    veiculosListaFilterable = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = veiculosListaFilterable
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                veiculosListaFilterable = p1?.values as ArrayList<Veiculo>
                notifyDataSetChanged()
            }
        }
    }
}