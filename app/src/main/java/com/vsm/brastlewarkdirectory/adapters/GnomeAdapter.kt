package com.vsm.brastlewarkdirectory.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.vsm.brastlewarkdirectory.R
import com.vsm.brastlewarkdirectory.services.ItemGnome

/*
* @author Vladimir Saldivar
* vsaldivarm@gmail.com
*/

class GnomeAdapter : RecyclerView.Adapter<GnomeAdapter.ViewHolder>() {

    var superheros: List<ItemGnome> = ArrayList()
    lateinit var context: Context


    fun PopularAdapter(superheros: List<ItemGnome>, context: Context) {
        this.superheros = superheros
        this.context = context
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = superheros.get(position)
        holder.bind(item, context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_gnome, parent, false))
    }

    override fun getItemCount(): Int {
        return superheros.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtViewPopularTittle = view.findViewById(R.id.txtViewNameGnome) as TextView
        val txtViewPopularSubTittle1 = view.findViewById(R.id.txtViewAgeGnome) as TextView

        fun bind(superhero: ItemGnome, context: Context) {
            txtViewPopularTittle.text = superhero.name
            txtViewPopularSubTittle1.text = superhero.age
            //avatar.loadUrl(superhero.photo)
            itemView.setOnClickListener(View.OnClickListener {
                Toast.makeText(
                    context,
                    superhero.name,
                    Toast.LENGTH_SHORT
                ).show()
            })
        }
        /*fun ImageView.loadUrl(url: String) {
            Picasso.with(context).load(url).into(this)
        }*/
    }
}