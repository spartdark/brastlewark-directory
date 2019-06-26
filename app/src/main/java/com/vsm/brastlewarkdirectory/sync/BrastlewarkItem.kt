package com.vsm.brastlewarkdirectory.sync


import com.google.gson.annotations.SerializedName


data class BrastlewarkItem(

    @field:SerializedName("thumbnail")
    val thumbnail: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("professions")
    val professions: List<String?>? = null,

    @field:SerializedName("weight")
    val weight: Double? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("age")
    val age: Int? = null,

    @field:SerializedName("friends")
    val friends: List<String?>? = null,

    @field:SerializedName("height")
    val height: Double? = null,

    @field:SerializedName("hair_color")
    val hairColor: String? = null
)