package com.vsm.brastlewarkdirectory.ui

import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
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

class GnomesActivity : AppCompatActivity() {
    lateinit var mRecyclerView: RecyclerView
    val mAdapter: GnomeAdapter = GnomeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.tittle_toolbar_main)
        fab.setOnClickListener { view ->
            Snackbar.make(view, R.string.fb_action_filter_gnomes, Snackbar.LENGTH_LONG)
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

    //metodo para crear e inflar el recliclerView
    fun setUpRecyclerView(itemsShows: List<BrastlewarkItem>) {
        progressBarMain.visibility = View.GONE
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

    //metodo para hacer la peticion http y obtener el json
    fun makeRequest() {
        progressBarMain.visibility = View.VISIBLE
        val retrofit = retrofitInit()
        val endpoint = retrofit.create(DataInterface::class.java)
        val call = endpoint.getGnomes()
        call.enqueue(object : Callback<GonomeResponse> {
            override fun onResponse(call: Call<GonomeResponse>, response: Response<GonomeResponse>) {
                if (response?.code() == 200) {
                    setUpRecyclerView(response.body()?.brastlewark as List<BrastlewarkItem>)
                }
            }

            override fun onFailure(call: Call<GonomeResponse>, t: Throwable) {
                progressBarMain.visibility = View.GONE
                Snackbar.make(
                    coordinatorMain,
                    R.string.error_there_was_an_error_please_retry_later,
                    Snackbar.LENGTH_LONG
                ).show()
            }
        })
    }

}
