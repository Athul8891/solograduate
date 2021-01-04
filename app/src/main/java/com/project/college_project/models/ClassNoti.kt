package com.project.college_project


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ClassNoti (
    var id : String = "",
    val strName : String = "",
    val strDate : String = "",
    val strLink : String = "",
    val strClssId : String = "",
    val strMatter : String = ""

) : Parcelable