package com.example.androidtest.Adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.telecom.Call.Details
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtest.DetailsActivity
import com.example.androidtest.Models.PopularModel
import com.example.androidtest.Models.SharedModel
import com.example.androidtest.databinding.ActivityLogin2Binding
import com.example.androidtest.databinding.FragmentProfileBinding
import com.example.androidtest.databinding.HomeItemBinding
import com.example.androidtest.ui.MenuBottomSheer

class PopularAgapter(
    var context: Context,
    var list: ArrayList<PopularModel>
) : RecyclerView.Adapter<PopularAgapter.PopularViewHolder>(){

    private lateinit var sharedModel: SharedModel

    fun setSharedModel(viewModel: SharedModel){
        sharedModel = viewModel
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularAgapter.PopularViewHolder {
        val binding = HomeItemBinding.inflate(LayoutInflater.from(context), parent,false)
        return PopularViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularAgapter.PopularViewHolder, position: Int) {

        val listModel = list[position]

        holder.rvName.text = listModel.getRvName()
        holder.rvPrice.text = listModel.getRvPrice()
        listModel.getRvImage()?.let { holder.rvImage.setImageResource(it) }

        holder.item.setOnClickListener{
            val intent = Intent(context, DetailsActivity:: class.java)
            intent.putExtra("rvImage", listModel.getRvImage())
            intent.putExtra("rvName", listModel.getRvName())
            context.startActivities(arrayOf(intent))
        }

        holder.addBtn.setOnClickListener(){

            if (sharedModel.inList(listModel)){
                sharedModel.deleteFromCart(listModel)
                holder.addBtn.setText("Add To Basket")

            }
            else{
               sharedModel.addToCart(listModel)
               holder.addBtn.setText("Delete Choice")
            }
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
    class PopularViewHolder(binding: HomeItemBinding): RecyclerView.ViewHolder(binding.root) {

        val rvImage = binding.homeRvImage
        val rvName = binding.homeRvName
        val rvPrice = binding.homeRvPrice

        val addBtn = binding.homeRvBtn
        val item = binding.root

    }

    fun updateList(newList : ArrayList<PopularModel>){
        list = newList
        notifyDataSetChanged()
    }
}


