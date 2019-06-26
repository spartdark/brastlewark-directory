package com.vsm.brastlewarkdirectory.sync


import com.google.gson.annotations.SerializedName


data class GonomeResponse(

    @field:SerializedName("Brastlewark")
    val brastlewark: List<BrastlewarkItem?>? = null
)