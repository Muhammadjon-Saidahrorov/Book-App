package uz.gita.bookappmn.utils

import android.content.Context
import android.util.Log
import android.widget.Toast

fun myLog(message: String, tag : String = "TTT") {
    Log.d(tag, message)
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
