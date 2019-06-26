package com.vsm.brastlewarkdirectory.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.vsm.brastlewarkdirectory.sync.BrastlewarkItem
import kotlinx.android.synthetic.main.activity_gnomes_detail.*
import kotlinx.android.synthetic.main.content_gnomes_detail.*


class GnomesDetailActivity : AppCompatActivity() {

    var gnome: BrastlewarkItem? = null
    val EXTRA_INTENTDATA = "brastlewarkitem"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.vsm.brastlewarkdirectory.R.layout.activity_gnomes_detail)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //obtengo el objeto de la actividad anterior
        gnome = getIntent().extras?.getSerializable(EXTRA_INTENTDATA) as? BrastlewarkItem
        supportActionBar?.title = gnome?.name

        //cargo la imagenm del nomo
        Glide.with(this).load(gnome?.thumbnail).into(imageViewGnomeDetail)
        //muestro el detalle del nomo
        textAge.text = gnome?.age.toString()
        textWeight.text = gnome?.weight.toString().take(2).toDouble().toString()
        textHeight.text = gnome?.height.toString().take(2).toDouble().toString()
        textHairColor.text = gnome?.hairColor.toString()
        if (gnome?.professions?.isEmpty()!!) {
            textview_professions.visibility = View.INVISIBLE
            textProfessions.visibility = View.INVISIBLE
        } else {
            var text = ""
            for (details in gnome?.professions!!) {
                text = text + details + "\n"
            }
            textProfessions.text = text
        }
        if (gnome?.friends?.isEmpty()!!) {
            textview_professions.visibility = View.INVISIBLE
            textProfessions.visibility = View.INVISIBLE
        } else {
            var textMFriends = ""
            for (details in gnome?.friends!!) {
                textMFriends = textMFriends + details + "\n"
            }
            textFriends.text = textMFriends
        }
    }

}
