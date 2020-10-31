package com.joseluisgs.retrofitcrud2020

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joseluisgs.retorfitcrud2020.R
import com.joseluisgs.retrofitcrud2020.modelos.usuarios.Usuario
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.lista_usuarios_item.view.*

class UsuarioListAdapter(
    private val listaUsuario: MutableList<Usuario>,
    private val listener: (Usuario) -> Unit
) :
    RecyclerView.Adapter<UsuarioListAdapter.UsuarioViewHolder>() {

//class NoticiasListAdapter(// Objeto con el modelo de datos (lista)
//	private val listaNoticias: MutableList<Noticia>, // Fragment Manager para trabajar con el
//	private val fm: FragmentManager
//) :
//	RecyclerView.Adapter<NoticiasListAdapter.NoticiaViewHolder>() {

    /**
     * Asociamos la vista
     *
     * @param parent
     * @param viewType
     * @return
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        return UsuarioViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.lista_usuarios_item, parent, false)
        )
    }

    /**
     * Procesamos los datos y las metemos en un Holder
     *
     * @param holder
     * @param position
     */
    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        holder.itemUserTxtID.text = listaUsuario[position].id
        holder.itemUserTxtNombre.text = listaUsuario[position].name
        holder.itemUserTxtNick.text = listaUsuario[position].nick
        holder.itemUserTxtEmail.text = listaUsuario[position].email
        // holder.ivItemDato.setImageResource(listaDatos[position].imgId)
        Picasso.get()
            .load(listaUsuario[position].avatar) //Instanciamos un objeto de la clase (creada más abajo) para redondear la imagen
            .resize(250, 250).into(holder.itemUserIvAvatar)

        // Programamos el clic de cada fila (itemView)
        holder.itemView
            .setOnClickListener {
                listener(listaUsuario[position])
            }
    }

    /**
     * Elimina un item de la lista
     *
     * @param position
     */
    fun removeItem(position: Int) {
        listaUsuario.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, listaUsuario.size)
    }

    /**
     * Recupera un Item de la lista
     *
     * @param item
     * @param position
     */
    fun restoreItem(item: Usuario, position: Int) {
        listaUsuario.add(position, item)
        notifyItemInserted(position)
        notifyItemRangeChanged(position, listaUsuario.size)
    }

    /**
     * Para añadir un elemento
     * @param item Dato
     */
    fun addItem(item: Usuario) {
        listaUsuario.add(item)
        notifyItemInserted(listaUsuario.size)
    }

    /**
     * Devuelve el número de items de la lista
     *
     * @return
     */
    override fun getItemCount(): Int {
        return listaUsuario.size
    }

    fun list(): MutableList<Usuario> {
        return this.listaUsuario
    }


    /**
     * Holder que encapsula los objetos a mostrar en la lista
     */
    class UsuarioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Elementos graficos con los que nos asociamos
        var itemUserTxtID = itemView.itemUserTxtID
        var itemUserTxtNombre = itemView.itemUserTxtNombre
        var itemUserTxtNick = itemView.itemUserTxtNick
        var itemUserTxtEmail = itemView.itemUserTxtEmail
        var itemUserIvAvatar = itemView.itemUserIvAvatar
    }
}