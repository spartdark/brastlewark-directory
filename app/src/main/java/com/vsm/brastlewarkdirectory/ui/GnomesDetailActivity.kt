package com.vsm.brastlewarkdirectory.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vsm.brastlewarkdirectory.R
import com.vsm.brastlewarkdirectory.sync.BrastlewarkItem
import kotlinx.android.synthetic.main.activity_gnomes_detail.*

class GnomesDetailActivity : AppCompatActivity() {

    var gnome: BrastlewarkItem? = null
    val EXTRA_INTENTDATA = "brastlewarkitem"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gnomes_detail)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        gnome = getIntent().extras?.getSerializable(EXTRA_INTENTDATA) as? BrastlewarkItem
        supportActionBar?.title = gnome?.name


        //Toast.makeText(this,"${gnome?.name}",Toast.LENGTH_SHORT).show()
    }

}
