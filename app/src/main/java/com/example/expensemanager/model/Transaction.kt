package com.example.expensemanager.model

import java.util.Date

data class Transaction(
    var type:String,
    var category:String,
    var amount: Int,
    var account:String,
    var note:String,
    var date:Date


)
