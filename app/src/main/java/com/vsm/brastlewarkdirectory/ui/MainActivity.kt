package com.vsm.brastlewarkdirectory.ui

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.vsm.brastlewarkdirectory.R
import com.vsm.brastlewarkdirectory.adapters.GnomeAdapter
import com.vsm.brastlewarkdirectory.interfaces.DataInterface
import com.vsm.brastlewarkdirectory.sync.BrastlewarkItem
import com.vsm.brastlewarkdirectory.sync.GonomeResponse
import com.vsm.brastlewarkdirectory.utils.retrofitInit
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var mRecyclerView: RecyclerView
    val mAdapter: GnomeAdapter = GnomeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.tittle_toolbar_main)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        //realizo la peticion
        makeRequest()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


    fun setUpRecyclerView(itemsShows: List<BrastlewarkItem>) {
        /*val itemsShows = (0..50).map {
            ItemGnome("Titulo: ${it}", "Subtitulo: $it")
        }*/
        mRecyclerView = findViewById(R.id.rcViewGnomes) as RecyclerView
        mRecyclerView.setHasFixedSize(true)
        if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecyclerView.setLayoutManager(GridLayoutManager(this, 2) as RecyclerView.LayoutManager?)
        } else {
            mRecyclerView.setLayoutManager(GridLayoutManager(this, 4))
        }
        mAdapter.PopularAdapter(itemsShows, this)
        mRecyclerView.adapter = mAdapter
    }

    fun makeRequest() {
        val retrofit = retrofitInit()
        val endpoint = retrofit.create(DataInterface::class.java)
        val call = endpoint.getGnomes()
        call.enqueue(object : Callback<GonomeResponse> {
            override fun onResponse(call: Call<GonomeResponse>, response: Response<GonomeResponse>) {
                if (response?.code() == 200) {
                    Log.i("Respuesta: ", "${response.body().toString()}")
                    setUpRecyclerView(response.body()?.brastlewark as List<BrastlewarkItem>)
                }

            }

            override fun onFailure(call: Call<GonomeResponse>, t: Throwable) {
            }


        })
    }

}
