package com.rsschool.android2021

interface i_openFragment {
    fun openFirstFragmentViaInteface(previousNumber:Int)
    fun openSecondFragmentViaInteface(min:Int, max:Int)
    fun showToast(text:String)
    fun setnumerForBack(number:Int)
    fun getnumerForBack():Int
}