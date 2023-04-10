package com.rafael.bodyfattracker.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rafael.bodyfattracker.data.model.BodyFatModel
import com.rafael.bodyfattracker.databinding.RowRecyclerViewBinding
import kotlin.collections.ArrayList

class RecyclerViewAdapter(var list: ArrayList<BodyFatModel>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(private val binding: RowRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BodyFatModel){
            binding.apply {
                bodyFatValue.text = item.value
                dateText.text = item.date
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView =RowRecyclerViewBinding.inflate(LayoutInflater.from(parent.context),
        parent,
        false)
        return ProductViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)

    }
}