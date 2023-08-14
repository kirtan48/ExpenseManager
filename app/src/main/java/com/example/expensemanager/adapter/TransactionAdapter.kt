package com.example.expensemanager.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.expensemanager.databinding.RowTransactionBinding
import com.example.expensemanager.databinding.SampleCategoryItemBinding
import com.example.expensemanager.model.Category
import com.example.expensemanager.model.Transaction

class TransactionAdapter(private val onItemClick: (Transaction) -> Unit) : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {
    var transactionList = ArrayList<Transaction>()

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

       if(transaction.type=="Income"){
           holder.binding.tvTransVal.setTextColor(Color.parseColor("#4CAF50"))
       }
        else{
           holder.binding.tvTransVal.setTextColor(Color.parseColor("#353535"))

       }
    }
}