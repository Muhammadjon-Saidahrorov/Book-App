package uz.gita.bookappmn.data.sourse

import android.content.Context
import android.content.SharedPreferences

class LocalStorage(context: Context) {

    private var preferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null

    init {
        preferences = context.getSharedPreferences("EXAM3", Context.MODE_PRIVATE)
        editor = preferences?.edit()
    }


    companion object {
        private var localStorge: LocalStorage? = null

        fun getInstance(): LocalStorage? {
            return localStorge
        }

        fun init(context: Context) {
            if (localStorge == null) localStorge = LocalStorage(context)
        }

    }

    fun saveFirstApp(str: Boolean) {
        editor?.putBoolean("APP", str)?.apply()
    }

    fun getFirstApp(): Boolean? {
        return preferences?.getBoolean("APP", true)
    }

    fun saveFirstLogic(str: Boolean) {
        editor?.putBoolean("LOGIC", str)?.apply()
    }

    fun getFirstLogic(): Boolean? {
        return preferences?.getBoolean("LOGIC", true)
    }

    fun saveLogicRead(str: Boolean) {
        editor?.putBoolean("LOGICREAD", str)?.apply()
    }

    fun getLogicRead(): Boolean? {
        return preferences?.getBoolean("LOGICREAD", true)
    }

    fun saveReference(str: String) {
        editor?.putString("REFERENCE", str)?.apply()
    }

    fun getReference(): String? {
        return preferences?.getString("REFERENCE", "")
    }

    fun saveBookName(str: String) {
        editor?.putString("BookName", str)?.apply()
    }

    fun getBookName(): String? {
        return preferences?.getString("BookName", "")
    }

    fun saveBookAuthor(str: String) {
        editor?.putString("BookAuthor", str)?.apply()
    }

    fun getBookAuthor(): String? {
        return preferences?.getString("BookAuthor", "")
    }

    fun saveBookPage(name: String, page: Int) {
        editor?.putInt(name, page)?.apply()
    }

    fun getBookPage(name: String): Int? {
        return preferences?.getInt(name, 0)
    }

    fun saveBookImg(str: String) {
        editor?.putString("BookImg", str)?.apply()
    }

    fun getBookImg(): String? {
        return preferences?.getString("BookImg", "")
    }

}