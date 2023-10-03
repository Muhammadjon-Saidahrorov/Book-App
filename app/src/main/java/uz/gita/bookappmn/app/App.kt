package uz.gita.bookappmn.app

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import uz.gita.bookappmn.data.sourse.LocalStorage
import uz.gita.bookappmn.data.sourse.local.database.BookDatabase

@HiltAndroidApp
class App: Application() {
    companion object{
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        LocalStorage.init(this)
        BookDatabase.init(this)
    }
}