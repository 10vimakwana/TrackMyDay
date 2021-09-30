package com.project.trackmydayapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.trackmydayapp.R
import com.project.trackmydayapp.activity.admin.UserDetailActivity
import com.project.trackmydayapp.database.DatabaseHandler
import com.project.trackmydayapp.model.StepModel
import com.project.trackmydayapp.model.UserModel
import com.project.trackmydayapp.model.UserProfileModel

class StepAdapter(val context: Context?, val arraylist: ArrayList<StepModel>) :
    RecyclerView.Adapter<StepAdapter.ViewHolder>() {

    val db: DatabaseHandler = DatabaseHandler(context);


    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_steps, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(arraylist.get(position), position)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return arraylist.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txt_steps: TextView

        init {
            txt_steps = itemView.findViewById<TextView>(R.id.txt_steps)

        }

        fun bindItems(stepModel: StepModel, position: Int) {
            txt_steps.text = "" + (position + 1) + " : " + stepModel.steps + " Steps"


        }

    }

}
