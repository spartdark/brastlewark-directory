package com.vsm.brastlewarkdirectory.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vsm.brastlewarkdirectory.R
import com.vsm.brastlewarkdirectory.sync.BrastlewarkItem
import com.vsm.brastlewarkdirectory.ui.GnomesDetailActivity

/*
* @author Vladimir Saldivar
* vsaldivarm@gmail.com
*/

class GnomeAdapter : RecyclerView.Adapter<GnomeAdapter.ViewHolder>() {

    var superheros: List<BrastlewarkItem> = ArrayList()
    lateinit var context: Context


    fun PopularAdapter(superheros: List<BrastlewarkItem>, context: Context) {
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
        //inflo los elementos graficos
        val txtViewPopularTittle = view.findViewById(R.id.txtViewNameGnome) as TextView
        val txtViewPopularSubTittle1 = view.findViewById(R.id.txtViewAgeGnome) as TextView
        val imageViewGnome = view.findViewById(R.id.imageViewGnome) as ImageView
        val EXTRA_INTENTDATA = "brastlewarkitem"
        //muestro los detalles del cardview
        fun bind(superhero: BrastlewarkItem, context: Context) {
            Glide.with(context).load(superhero.thumbnail).into(imageViewGnome)
            txtViewPopularTittle.text = superhero.name
            txtViewPopularSubTittle1.text = "${superhero.age.toString()} años"
            itemView.setOnClickListener {
                val intent = Intent(context, GnomesDetailActivity::class.java)
                intent.putExtra(EXTRA_INTENTDATA, superhero)
                context.startActivity(intent)
            }
        }
    }
}