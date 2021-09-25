package com.project.trackmydayapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.trackmydayapp.R
import com.project.trackmydayapp.activity.admin.FoodDetailActivity
import com.project.trackmydayapp.model.FoodModel

class FoodListAdapter() :
    RecyclerView.Adapter<FoodListAdapter.ViewHolder>(), Filterable {

    var foodListFiltered: ArrayList<FoodModel> = ArrayList()
    var arrayList: ArrayList<FoodModel> = ArrayList()


    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_food_list, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(foodListFiltered.get(position))

    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return foodListFiltered.size
    }

    fun addData(list: ArrayList<FoodModel>) {
        arrayList = list as ArrayList<FoodModel>
        foodListFiltered = arrayList
        notifyDataSetChanged()
    }


    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txt_food_name: TextView
        var txt_food_des: TextView

        init {
            txt_food_name = itemView.findViewById<TextView>(R.id.txt_food_name)
            txt_food_des = itemView.findViewById<TextView>(R.id.txt_food_des)

        }


        fun bindItems(foodModel: FoodModel) {
            txt_food_name.text = "Food Name : " + foodModel.foodName
            txt_food_des.text = "Description : " + foodModel.description

            itemView.setOnClickListener(View.OnClickListener {
                itemView.getContext()
                    .startActivity(
                        Intent(
                            itemView.getContext(),
                            FoodDetailActivity::class.java
                        ).putExtra("foodid", foodModel.foodId)
                    )
            })

        }

    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                if (charString.isEmpty()) {
                    foodListFiltered = arrayList
                } else {
                    val filteredList = ArrayList<FoodModel>()
                    arrayList
                        .filter { (it.foodName.contains(constraint.toString())) }
                        .forEach { filteredList.add(it) }
                    foodListFiltered = filteredList

                }
                return FilterResults().apply { values = foodListFiltered }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

                foodListFiltered = if (results?.values == null)
                    ArrayList()
                else
                    results.values as ArrayList<FoodModel>
                notifyDataSetChanged()
            }
        }
    }
}


