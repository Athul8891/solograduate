package com.project.college_project


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Class (
    var id : String = "",
    val strName : String = "",
    val strTitle : String = "",
    val strYr : String = "",
    val strClssId : String = ""
) : Parcelable