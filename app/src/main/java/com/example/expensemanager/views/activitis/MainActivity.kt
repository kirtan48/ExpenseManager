package com.example.expensemanager.views.activitis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.example.expensemanager.views.fragments.AddTransactionFragment
import com.example.expensemanager.R
import com.example.expensemanager.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    val myCalendar = Calendar.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Transaction"
        binding.floatingActionButton.setOnClickListener{
           var bottomsheet= AddTransactionFragment()

           bottomsheet.show(supportFragmentManager,null)
        }

        updateDate()
        binding.imgBack.setOnClickListener{

            myCalendar.add(Calendar.DATE,-1)
            updateDate()
        }
        binding.imgNext.setOnClickListener{

            myCalendar.add(Calendar.DATE,1)
            updateDate()
        }


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
}