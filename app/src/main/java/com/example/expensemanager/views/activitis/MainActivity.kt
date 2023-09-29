package com.example.expensemanager.views.activitis

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expensemanager.R
import com.example.expensemanager.adapter.TransactionAdapter
import com.example.expensemanager.databinding.ActivityMainBinding
import com.example.expensemanager.model.Transaction
import com.example.expensemanager.views.fragments.AddTransactionFragment
import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.internal.platform.runBlocking
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.UUID


class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    val myCalendar = Calendar.getInstance()
    private lateinit var realm:Realm
    private lateinit var transactionAdapter:TransactionAdapter
    private lateinit var viewModel:ViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        setupDatabase()

        supportActionBar?.title = "Transaction"
        binding.floatingActionButton.setOnClickListener{
           var bottomsheet= AddTransactionFragment()

           bottomsheet.show(supportFragmentManager,null)
            Log.d("test","reach")
            getDetail()



        }


        updateDate()
        binding.imgBack.setOnClickListener{

            myCalendar.add(Calendar.DATE,-1)
            updateDate()
            getDetail()
        }
        binding.imgNext.setOnClickListener{

            myCalendar.add(Calendar.DATE,1)
            updateDate()
            getDetail()
        }
        transactionAdapter = TransactionAdapter()
        val itemClickListener = this
    //    transactionAdapter.setOnItemClickListener(itemClickListener)

        val transaction=ArrayList<Transaction>()
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        getDetail()

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu,menu)

        return super.onCreateOptionsMenu(menu)
    }

    private fun updateDate() {

        val year=myCalendar.get(Calendar.YEAR)
        val month=myCalendar.get(Calendar.MONTH)
        val day=myCalendar.get(Calendar.DAY_OF_MONTH)
        binding.tvDate.setText("$day ${month+1}")
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        binding.tvDate.text = sdf.format(myCalendar.time)


    }
    fun setupDatabase(){
        Realm.init(this)
        realm= Realm.getDefaultInstance()
    }
    fun getDetail(){
        var income=0
        var expense=0
        val transaction=ArrayList<Transaction>()
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        val targetDate = binding.tvDate.text.toString()
        Log.d("msg",targetDate)
        val transactions = realm.where(Transaction::class.java)
            .equalTo("date", targetDate)
            .findAll()
        for (x in transactions) {
            // Access and process each transaction object
            val type = x.type
            val category = x.category
            val amount = x.amount
            val account = x.account
            val note = x.note
            val date = x.date

            if(x.type=="Income")income=income+amount
            else{
                expense=expense+amount

            }
            transaction.add(x)


        }
        binding.transactionRv.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL ,false)
        transactionAdapter.transactionList=transaction
        binding.transactionRv.adapter = transactionAdapter
        binding.tvIncomeVal.text=income.toString()
        binding.tvExpenseVal.text=expense.toString()
        binding.tvTotalVal.text=(income-expense).toString()
    }
    fun onItemClick(position: Int) {
        Log.d("test","xyz")

        // Handle the click event for the specific item at 'position'
        // You can perform any action you want here
    }




}