package com.rafael.bodyfattracker.util

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

fun goTo(it: View, id: Int) {
    Navigation.findNavController(it).navigate(id)
}

fun Fragment.toast(msg: String?){
    Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
}

fun View.hide(){
    visibility = View.INVISIBLE
}

fun View.show(){
    visibility = View.VISIBLE
}

fun View.gone(){
    visibility = View.GONE
}