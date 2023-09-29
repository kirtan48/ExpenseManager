package com.example.expensemanager

import android.app.Application
import com.example.expensemanager.model.MyRealmModule
import com.example.expensemanager.model.Transaction
import io.realm.Realm
import io.realm.RealmConfiguration

class realmApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)

        // Initialize Realm with your custom configuration
        val config = RealmConfiguration.Builder()
            .name("myrealm.realm")
            .schemaVersion(1)
            .modules(Realm.getDefaultModule(), MyRealmModule())
            .build()
        Realm.setDefaultConfiguration(config)
    }
}