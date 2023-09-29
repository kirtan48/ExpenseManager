package com.example.expensemanager.views.fragments

import CategoryAdapter
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.expensemanager.R

import com.example.expensemanager.databinding.FragmentAddTransactionBinding
import com.example.expensemanager.databinding.ListDialogBinding
import com.example.expensemanager.model.Category
import com.example.expensemanager.model.Transaction
import com.example.expensemanager.views.activitis.MainActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import io.realm.Realm
import io.realm.kotlin.internal.platform.runBlocking
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.UUID


public class AddTransactionFragment : BottomSheetDialogFragment() {
    private lateinit var categoriesAdapter:CategoryAdapter
    private lateinit var realm:Realm
    val categories=ArrayList<Category>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var flag=0


        var binding:FragmentAddTransactionBinding=FragmentAddTransactionBinding.inflate(inflater)
        binding.tvIncome.setBackgroundResource(com.example.expensemanager.R.drawable.income_selector)
        binding.tvIncome.setTextColor(Color.parseColor("#4CAF50"))
        binding.tvExpense.setTextColor(Color.parseColor("#353535"))
        binding.tvExpense.setBackgroundResource(com.example.expensemanager.R.drawable.default_selector)
        binding.tvIncome.setOnClickListener{
            flag=0
            binding.tvIncome.setBackgroundResource(com.example.expensemanager.R.drawable.income_selector)
            binding.tvIncome.setTextColor(Color.parseColor("#4CAF50"))
            binding.tvExpense.setTextColor(Color.parseColor("#353535"))
            binding.tvExpense.setBackgroundResource(com.example.expensemanager.R.drawable.default_selector)
        }
        binding.tvExpense.setOnClickListener{
            flag=1
            binding.tvExpense.setBackgroundResource(com.example.expensemanager.R.drawable.expense_selector)
            binding.tvIncome.setBackgroundResource(com.example.expensemanager.R.drawable.default_selector)
            binding.tvExpense.setTextColor(Color.parseColor("#F44336"))
            binding.tvIncome.setTextColor(Color.parseColor("#353535"))
        }
        val myCalendar = Calendar.getInstance()
        val year=myCalendar.get(Calendar.YEAR)
        val month=myCalendar.get(Calendar.MONTH)
        val day=myCalendar.get(Calendar.DAY_OF_MONTH)
        if(month+1<10)binding.selectDate.setText("$day/0${month+1}/$year")
        else binding.selectDate.setText("$day/0${month+1}/$year")
        binding.selectDate.setOnClickListener{
            val myCalendar = Calendar.getInstance()
            val year=myCalendar.get(Calendar.YEAR)
            val month=myCalendar.get(Calendar.MONTH)
            val day=myCalendar.get(Calendar.DAY_OF_MONTH)

            val dpd= DatePickerDialog(context as AppCompatActivity,
                {_,selectedYear,selectedMonth,dayOfMonth ->
                  //  if(selectedMonth+1<10)selectedMonth=("0"+selectedMonth.toString()).toInt()

                    val  SelectedDate="$dayOfMonth/${selectedMonth+1}/$selectedYear"
                   // binding.selectDate.setText(SelectedDate)
                    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                    val theDate = sdf.format(sdf.parse(SelectedDate))
                    Log.d("msg+",theDate.toString())
                    binding.selectDate.setText(theDate)
                },
                year,
                month,
                day)

            dpd.show()
        }



        binding.btnSave.setOnClickListener {
            Realm.init(context)
            realm= Realm.getDefaultInstance()

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            runBlocking {
                 launch(Dispatchers.IO) {
                     val realm = Realm.getDefaultInstance()
                     val uniquePrimaryKeyValue = UUID.randomUUID().toString()
                     realm.executeTransaction{
                         val task = realm.createObject(Transaction::class.java,uniquePrimaryKeyValue)
                         if(flag==0)task.type="Income"
                         else task.type="Expense"


                         task.category=binding.category.text.toString()
                         task.amount=binding.amt.text.toString().toInt()
                         task.account=binding.spinner.selectedItem.toString()
                         task.note=binding.note.text.toString()
                         task.date=binding.selectDate.text.toString()
                         Log.d("msg",binding.selectDate.text.toString())

                     }
                 }
             }

            dismiss()
        }
        binding.category.setOnClickListener {
            val dialogBinding = ListDialogBinding.inflate(inflater)
            val categoryDialog: AlertDialog = AlertDialog.Builder(context).create()
            categoryDialog.setView(dialogBinding.root)


            categories.add(Category("Salary", R.drawable.ic_salary,"#4CAF50"))
            categories.add(Category("Business", R.drawable.ic_business, "#F44336"))
            categories.add(Category("Investment", R.drawable.ic_investment, "#F44336"))
            categories.add(Category("Loan", R.drawable.ic_loan,"#9C27B0"))
            categories.add(Category("Rent", R.drawable.ic_rent, "#E91E63"))
            categories.add(Category("Other", R.drawable.ic_other,"#877606"))

            val categoriesAdapter = CategoryAdapter { selectedCategory ->

                categoryDialog.dismiss()
                // You can access the selected category's details using the selectedCategory variable
                val editable: Editable = Editable.Factory.getInstance().newEditable(selectedCategory.categoryName)
                 binding.category.text=editable

                // ...
            }


            dialogBinding.recyclerView.layoutManager = GridLayoutManager(context, 3)
            dialogBinding.recyclerView.adapter = categoriesAdapter
            categoriesAdapter.categoriesList = categories // Set the list of categories

            categoryDialog.show()











        }
        val aval = resources.getStringArray(R.array.Account)
        val spinner: Spinner = binding.spinner
        val adapter = context?.let { ArrayAdapter(it,R.layout.spinner_text, aval) }
        //spinner.adapter = adapter
        adapter?.setDropDownViewResource(R.layout.spinner_dropdown)
        spinner.adapter = adapter
        return binding.root

    }
    fun getCategoryDetails(categoryName: String?): Category? {
        for (cat in categories) {
            if (cat.categoryName == categoryName) {
                return cat
            }
        }
        return null
    }


}