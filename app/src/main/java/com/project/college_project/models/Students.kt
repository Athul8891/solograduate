package com.project.college_project


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Students (
    var id : String = "",
    val strUsr : String = "",
    val strSem : String = "",
    val uId : String = "",
    val strKtu : String = ""


) : Parcelable