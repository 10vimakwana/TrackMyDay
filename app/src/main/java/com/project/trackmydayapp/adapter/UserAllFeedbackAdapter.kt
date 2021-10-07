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
import com.project.trackmydayapp.model.FeedbackModel
import com.project.trackmydayapp.model.StepModel
import com.project.trackmydayapp.model.UserModel
import com.project.trackmydayapp.model.UserProfileModel

class UserAllFeedbackAdapter(val context: Context?, val arraylist: ArrayList<FeedbackModel>) :
    RecyclerView.Adapter<UserAllFeedbackAdapter.ViewHolder>() {



    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_userall_feedback, parent, false)
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

        var txt_userall_que: TextView
        var txt_userall_ans: TextView

        init {
            txt_userall_que = itemView.findViewById<TextView>(R.id.txt_userall_que)
            txt_userall_ans = itemView.findViewById<TextView>(R.id.txt_userall_ans)

        }

        fun bindItems(feedbackModel: FeedbackModel, position: Int) {
            txt_userall_que.text = "Question : "+feedbackModel.title
            txt_userall_ans.text = "Answer : "+feedbackModel.reply


        }

    }

}
