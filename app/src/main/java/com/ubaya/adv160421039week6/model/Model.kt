package com.ubaya.adv160421039week6.model

import com.google.gson.annotations.SerializedName

data class Phone(
    val id: Int,
    val name: String,
    val brand: String,
    val features: List<String>,
    val specifications: PhoneSpesifications?
)

data class PhoneSpesifications(
        val RAM: Int?,
        val Storage: Int?,
        val Battery: Int?
        )