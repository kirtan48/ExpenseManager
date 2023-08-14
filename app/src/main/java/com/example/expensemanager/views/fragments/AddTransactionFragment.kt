package com.example.expensemanager.views.fragments

import CategoryAdapter
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
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
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class AddTransactionFragment : BottomSheetDialogFragment() {
    private lateinit var categoriesAdapter:CategoryAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var binding:FragmentAddTransactionBinding=FragmentAddTransactionBinding.inflate(inflater)
        binding.tvIncome.setOnClickListener{
            binding.tvIncome.setBackgroundResource(com.example.expensemanager.R.drawable.income_selector)
            binding.tvIncome.setTextColor(Color.parseColor("#4CAF50"))
            binding.tvExpense.setTextColor(Color.parseColor("#353535"))
            binding.tvExpense.setBackgroundResource(com.example.expensemanager.R.drawable.default_selector)
        }
        binding.tvExpense.setOnClickListener{
            binding.tvExpense.setBackgroundResource(com.example.expensemanager.R.drawable.expense_selector)
            binding.tvIncome.setBackgroundResource(com.example.expensemanager.R.drawable.default_selector)
            binding.tvExpense.setTextColor(Color.parseColor("#F44336"))
            binding.tvIncome.setTextColor(Color.parseColor("#353535"))
        }
        val myCalendar = Calendar.getInstance()
        val year=myCalendar.get(Calendar.YEAR)
        val month=myCalendar.get(Calendar.MONTH)
        val day=myCalendar.get(Calendar.DAY_OF_MONTH)
        binding.selectDate.setText("$day/${month+1}/$year")
        binding.selectDate.setOnClickListener{
            val myCalendar = Calendar.getInstance()
            val year=myCalendar.get(Calendar.YEAR)
            val month=myCalendar.get(Calendar.MONTH)
            val day=myCalendar.get(Calendar.DAY_OF_MONTH)

            val dpd= DatePickerDialog(context as AppCompatActivity,
                {_,selectedYear,selectedMonth,dayOfMonth ->

                    val SelectedDate="$dayOfMonth/${selectedMonth+1}/$selectedYear"
                    binding.selectDate.setText(SelectedDate)
                    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                    val theDate = sdf.parse(SelectedDate)
                },
                year,
                month,
                day)

            dpd.show()
        }
        binding.category.setOnClickListener {
            val dialogBinding = ListDialogBinding.inflate(inflater)
            val categoryDialog: AlertDialog = AlertDialog.Builder(context).create()
            categoryDialog.setView(dialogBinding.root)
            val categories=ArrayList<Category>()

            categories.add(Category("Salary", R.drawable.ic_salary,R.color.category2))
            categories.add(Category("Business", R.drawable.ic_business, R.color.category2))
            categories.add(Category("Investment", R.drawable.ic_investment, R.color.category3))
            categories.add(Category("Loan", R.drawable.ic_loan,R.color.category4))
            categories.add(Category("Rent", R.drawable.ic_rent, R.color.category5))
            categories.add(Category("Other", R.drawable.ic_other,R.color.category6))
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


}