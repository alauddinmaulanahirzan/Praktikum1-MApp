package com.example.praktikum1_mapp

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class UserInfo (
    var nama : String? = "",
    var email : String? = "",
    var password : String? = ""
)
