package com.project.trackmydayapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.project.trackmydayapp.R
import com.project.trackmydayapp.model.FoodModel

import kotlin.collections.ArrayList


open class PhotosAdapter :
    RecyclerView.Adapter<PhotosAdapter.DataViewHolder>(), Filterable {

    var photosList: ArrayList<FoodModel> = ArrayList()
    var photosListFiltered: ArrayList<FoodModel> = ArrayList()

    var onItemClick: ((FoodModel) -> Unit)? = null

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(photosListFiltered[adapterPosition])
            }
        }

        var txt_food_name: TextView
        var txt_food_des: TextView

        init {
            txt_food_name = itemView.findViewById<TextView>(R.id.txt_food_name)
            txt_food_des = itemView.findViewById<TextView>(R.id.txt_food_des)

        }

        fun bind(result: FoodModel) {
            txt_food_name.text = result.foodName.toString()
            txt_food_des.text = result.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DataViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.layout_food_list, parent,
            false
        )
    )

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(photosListFiltered[position])
    }

    override fun getItemCount(): Int = photosListFiltered.size

    fun addData(list: List<FoodModel>) {
        photosList = list as ArrayList<FoodModel>
        photosListFiltered = photosList
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                if (charString.isEmpty()) {
                    photosListFiltered = photosList
                } else {
                    val filteredList = ArrayList<FoodModel>()
                    photosList
                        .filter { (it.foodName.contains(constraint.toString())) }
                        .forEach { filteredList.add(it) }
                    photosListFiltered = filteredList

                }
                return FilterResults().apply { values = photosListFiltered }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

                photosListFiltered = if (results?.values == null)
                    ArrayList()
                else
                    results.values as ArrayList<FoodModel>
                notifyDataSetChanged()
            }
        }
    }
}