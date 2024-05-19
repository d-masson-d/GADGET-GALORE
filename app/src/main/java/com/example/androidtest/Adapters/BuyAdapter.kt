package com.example.androidtest.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtest.Models.PopularModel
import com.example.androidtest.databinding.CartAddItemBinding
import com.example.androidtest.databinding.HomeItemBinding
import com.example.androidtest.ui.Basket.basketFragment

class BuyAdapter(
    var context: Context,
    var list: ArrayList<PopularModel>
) : RecyclerView.Adapter<BuyAdapter.CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyAdapter.CartViewHolder {
        val binding = CartAddItemBinding.inflate(LayoutInflater.from(context), parent,false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BuyAdapter.CartViewHolder, position: Int) {
        val listModel = list[position]

        holder.rvName.text = listModel.getRvName()
        holder.rvPrice.text = listModel.getRvPrice()
        listModel.getRvImage()?.let { holder.rvImage.setImageResource(it)}


        holder.plus.setOnClickListener(){

            if (listModel.getCountRv() < 10){
                val count = listModel.getCountRv() + 1
                listModel.setCountRv(count)
                holder.rvCount.text = listModel.getCountRv().toString()
            }

        }
        holder.minus.setOnClickListener(){

            if(listModel.getCountRv()>1){
                val count = listModel.getCountRv() - 1
                listModel.setCountRv(count)
                holder.rvCount.text = listModel.getCountRv().toString()
            }
            else{
                holder.bundItem()
            }
        }
        holder.deleteBtn.setOnClickListener(){
            holder.bundItem()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class CartViewHolder(binding: CartAddItemBinding): RecyclerView.ViewHolder(binding.root) {

        val rvImage = binding.homeRvImage
        val rvName = binding.homeRvName
        val rvPrice = binding.homeRvPrice
        val rvCount = binding.countRv

        val plus = binding.plusBtn
        val minus = binding.minusBtn

        val deleteBtn = binding.deletedBtn

        fun bundItem() {

        if(adapterPosition != RecyclerView.NO_POSITION){
                deleteItem(adapterPosition)
            }
        }

    }
    fun deleteItem(position: Int){

        if(position in 0..list.size){
            list.removeAt(position)
            notifyDataSetChanged()
            notifyItemRangeChanged(position,list.size)
        }
    }


}