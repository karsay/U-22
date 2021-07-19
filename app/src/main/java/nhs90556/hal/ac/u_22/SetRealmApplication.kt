package nhs90556.hal.ac.u_22

import android.app.Application
import io.realm.Realm


class SetRealmApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}