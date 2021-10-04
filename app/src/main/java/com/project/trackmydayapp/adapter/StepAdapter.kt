package com.project.trackmydayapp.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.project.trackmydayapp.R
import com.project.trackmydayapp.activity.admin.UserDetailActivity
import com.project.trackmydayapp.database.DatabaseHandler
import com.project.trackmydayapp.helper.SessionManager
import com.project.trackmydayapp.model.StepModel
import com.project.trackmydayapp.model.UserModel
import com.project.trackmydayapp.model.UserProfileModel

class StepAdapter(val context: Context?, val arraylist: ArrayList<StepModel>,val onStepClick :onClick) :
    RecyclerView.Adapter<StepAdapter.ViewHolder>() {

    val db: DatabaseHandler = DatabaseHandler(context);


    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_steps, parent, false)
        return ViewHolder(v)
    }

    val sessionManager: SessionManager = context?.let { it1 ->
        SessionManager.getInstance(
            it1
        )
    }!!


    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(arraylist.get(position), position)
        holder.iv_delete_steps.setOnClickListener {
            onStepClick.deleteSteps(arraylist.get(position).stepId)


        }
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return arraylist.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txt_steps: TextView
        var iv_delete_steps: ImageView

        init {
            txt_steps = itemView.findViewById<TextView>(R.id.txt_steps)
            iv_delete_steps = itemView.findViewById<ImageView>(R.id.iv_delete_steps)

        }

        fun bindItems(stepModel: StepModel, position: Int) {
            txt_steps.text = "" + (position + 1) + " : " + stepModel.steps + " Steps"


        }

    }
    interface onClick {
        fun deleteSteps(stepid: Int) {}
    }

}
