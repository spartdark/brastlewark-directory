package com.vsm.brastlewarkdirectory.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
* @author Vladimir Saldivar
* vsaldivarm@gmail.com
*/

fun retrofitInit(): Retrofit {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/rrafols/mobile_test/master/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit
}