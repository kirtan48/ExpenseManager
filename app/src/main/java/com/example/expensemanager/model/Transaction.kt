package com.example.expensemanager.model

import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmModule
import java.util.Date
import io.realm.RealmObject
import java.util.UUID

@RealmModule
open class Transaction() : RealmObject(), RealmModel {
    var type: String=""
    var category: String=""
    var amount: Int=0
    var account: String=""
    var note: String=""
    var date: String=""
    @PrimaryKey
    var id: String = UUID.randomUUID().toString()
}