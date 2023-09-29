package com.example.expensemanager.model

import io.realm.annotations.RealmModule

@RealmModule(classes = [Transaction::class])
open class MyRealmModule
