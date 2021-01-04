package com.project.college_project


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Metrials (
    var id : String = "",
    val imgThree : String = "",
    val stdy : String = "",
    val type : String = "",
    val strTitle : String = ""
) : Parcelable