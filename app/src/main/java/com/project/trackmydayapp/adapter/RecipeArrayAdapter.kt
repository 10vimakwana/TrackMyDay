package com.project.trackmydayapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.trackmydayapp.R
import com.project.trackmydayapp.model.FeedbackModel
import com.project.trackmydayapp.model.FoodModel
import com.project.trackmydayapp.model.UserProfileModel

class RecipeArrayAdapter(ctx: Context, moods: List<FoodModel>) :
    ArrayAdapter<FoodModel>(ctx, 0, moods) {

    override fun getView(position: Int, recycledView: View?, parent: ViewGroup): View {
        return this.createView(position, recycledView, parent)
    }

    override fun getDropDownView(position: Int, recycledView: View?, parent: ViewGroup): View {
        return this.createView(position, recycledView, parent)
    }

    private fun createView(position: Int, recycledView: View?, parent: ViewGroup): View {

        val mood = getItem(position)

        val view = recycledView ?: LayoutInflater.from(context).inflate(R.layout.layout_spinner, parent, false)
        val viewHolder = ViewHolder(view)

        viewHolder.bindItems(mood?.foodName.toString())
        return view
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var sp_food_name: TextView

        init {
            sp_food_name = itemView.findViewById<TextView>(R.id.sp_food_name)

        }

        fun bindItems(food : String) {
            sp_food_name.text =  food


            /* itemView.setOnClickListener(View.OnClickListener {
                 itemView.getContext()
                     .startActivity(
                         Intent(
                             itemView.getContext(),
                             UserDetailActivity::class.java
                         ).putExtra("userid", userModel.userId).putExtra("emailid", userModel.email)
                     )
             })*/

        }

    }

}