package com.example.expensemanager.adapter

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Context
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.expensemanager.R
import com.example.expensemanager.databinding.RowTransactionBinding
import com.example.expensemanager.model.Category
import com.example.expensemanager.model.Transaction
import com.example.expensemanager.views.activitis.MainActivity



class TransactionAdapter : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {
    var transactionList = ArrayList<Transaction>()

    private var onClickListener: DialogInterface.OnClickListener? = null

    inner class TransactionViewHolder(val binding: RowTransactionBinding) : RecyclerView.ViewHolder(binding.root)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        return TransactionViewHolder(
            RowTransactionBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }




    override fun getItemCount(): Int {
        return transactionList.size
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {

        val transaction: Transaction = transactionList[position]
        holder.binding.tvTransVal.text = transaction.amount.toString()
        holder.binding.tvTransCategory.text=transaction.account
        holder.binding.tvCategory.text=transaction.category
        holder.binding.transDate.text=transaction.date.toString()


        //holder.binding.imgTransaction.setImageResource()

        if(transaction.category=="Salary"){
            holder.binding.imgTransaction.setImageResource( R.drawable.ic_salary)
            val hexColor = "#4CAF50" // Replace with your hex color code
            val colorValue = Color.parseColor(hexColor)
            val colorStateList = ColorStateList.valueOf(colorValue)
            holder.binding.imgTransaction.backgroundTintList = colorStateList
        }
        if(transaction.category=="Business"){
            holder.binding.imgTransaction.setImageResource( R.drawable.ic_business)
            val hexColor = "#F44336" // Replace with your hex color code
            val colorValue = Color.parseColor(hexColor)
            val colorStateList = ColorStateList.valueOf(colorValue)
            holder.binding.imgTransaction.backgroundTintList = colorStateList
        }
        if(transaction.category=="Investment"){
            holder.binding.imgTransaction.setImageResource( R.drawable.ic_investment)
            val hexColor = "#F44336" // Replace with your hex color code
            val colorValue = Color.parseColor(hexColor)
            val colorStateList = ColorStateList.valueOf(colorValue)
            holder.binding.imgTransaction.backgroundTintList = colorStateList

        }
        if(transaction.category=="Loan"){
            holder.binding.imgTransaction.setImageResource( R.drawable.ic_loan)
            val hexColor = "#9C27B0" // Replace with your hex color code
            val colorValue = Color.parseColor(hexColor)
            val colorStateList = ColorStateList.valueOf(colorValue)
            holder.binding.imgTransaction.backgroundTintList = colorStateList
        }
        if(transaction.category=="Rent"){
            holder.binding.imgTransaction.setImageResource( R.drawable.ic_rent)
            val hexColor = "#E91E63" // Replace with your hex color code
            val colorValue = Color.parseColor(hexColor)
            val colorStateList = ColorStateList.valueOf(colorValue)
            holder.binding.imgTransaction.backgroundTintList = colorStateList
        }
        if(transaction.category=="Other"){
            holder.binding.imgTransaction.setImageResource( R.drawable.ic_other)
            val hexColor = "#877606" // Replace with your hex color code
            val colorValue = Color.parseColor(hexColor)
            val colorStateList = ColorStateList.valueOf(colorValue)
            holder.binding.imgTransaction.backgroundTintList = colorStateList
        }
       if(transaction.type=="Income"){
           holder.binding.tvTransVal.setTextColor(Color.parseColor("#4CAF50"))
       }
        else {
           holder.binding.tvTransVal.setTextColor(Color.parseColor("#FF0000"))
           holder.binding.tvTransVal.text="-"+transaction.amount.toString()

       }
    }
    fun setOnClickListener(on: OnClickListener) {
        this.onClickListener =onClickListener
    }

    // onClickListener Interface
    interface OnClickListener {
        fun onClick(position: Int)
    }
}