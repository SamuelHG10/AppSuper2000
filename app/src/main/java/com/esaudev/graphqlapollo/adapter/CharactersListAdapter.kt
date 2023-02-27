package com.esaudev.graphqlapollo.adapter

import android.annotation.SuppressLint
import android.media.Image
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.esaudev.graphqlapollo.CharactersListQuery
import com.esaudev.graphqlapollo.databinding.ItemCharacterBinding

class CharactersListAdapter(
): ListAdapter<CharactersListQuery.ListaProducto, BaseListViewHolder<*>>(DiffUtilCallback)  {

    companion object{

        lateinit var subTotal : String

    }

    private object DiffUtilCallback : DiffUtil.ItemCallback<CharactersListQuery.ListaProducto>() {
        override fun areItemsTheSame(oldItem: CharactersListQuery.ListaProducto, newItem: CharactersListQuery.ListaProducto): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: CharactersListQuery.ListaProducto, newItem: CharactersListQuery.ListaProducto): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseListViewHolder<*> {
        val itemBinding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BindViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseListViewHolder<*>, position: Int) {
        when (holder) {
            is BindViewHolder -> holder.bind(getItem(position), position)
        }
    }

    inner class BindViewHolder(private val binding: ItemCharacterBinding) : BaseListViewHolder<CharactersListQuery.ListaProducto>(binding.root) {

        @SuppressLint("SetTextI18n")
        override fun bind(item: CharactersListQuery.ListaProducto, position: Int): Unit = with(binding) {

            //characterId.text = item.id.toString()
            characterName.text = item.descripcion
            characterSpecie.text = item.precio.toString()
            Glide.with(characterImagen.context).load(item.image).into(characterImagen)

            btnMas.setOnClickListener {
                txtCantidad.setText((txtCantidad.text.toString().toInt() + 1).toString())
            }

            btnMenos.setOnClickListener {
                if (txtCantidad.text.toString().toInt() > 1){
                    txtCantidad.setText((txtCantidad.text.toString().toInt() - 1).toString())
                }
            }

            btnAgregarCarrito.setOnClickListener {
                val suma : Float =
                    (characterSpecie.text.toString().toFloat() * txtCantidad.text.toString().toFloat())
                subTotal = suma.toString()
            }

        }
    }
}