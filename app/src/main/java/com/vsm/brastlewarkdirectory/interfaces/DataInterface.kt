package com.vsm.brastlewarkdirectory.interfaces

import com.vsm.brastlewarkdirectory.sync.GonomeResponse
import retrofit2.http.GET

/*
* @author Vladimir Saldivar
* vsaldivarm@gmail.com
*/

interface DataInterface {

    @GET("data.json")
    fun getGnomes(): retrofit2.Call<GonomeResponse>

}