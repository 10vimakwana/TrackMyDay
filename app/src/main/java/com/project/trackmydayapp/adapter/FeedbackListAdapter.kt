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
import com.project.trackmydayapp.model.FeedbackModel
import com.project.trackmydayapp.model.UserModel
import com.project.trackmydayapp.model.UserProfileModel


class FeedbackListAdapter(val context: Context?,val arraylist: ArrayList<FeedbackModel>) :
    RecyclerView.Adapter<FeedbackListAdapter.ViewHolder>() {

    val db: DatabaseHandler = DatabaseHandler(context);


    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_all_feedback, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var profilelist: ArrayList<UserProfileModel> = ArrayList<UserProfileModel>()
        profilelist = db.viewProfileUser(arraylist.get(position).userId)

        holder.bindItems(arraylist.get(position), profilelist)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return arraylist.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txt_allfeedback_firstname: TextView
        var txt_allfeedback_des: TextView
        var txt_allfeedback_title: TextView

        init {
            txt_allfeedback_firstname = itemView.findViewById<TextView>(R.id.txt_allfeedback_firstname)
            txt_allfeedback_des = itemView.findViewById<TextView>(R.id.txt_allfeedback_des)
            txt_allfeedback_title = itemView.findViewById<TextView>(R.id.txt_allfeedback_title)

        }

        fun bindItems(feedbackModel: FeedbackModel, arraylist: ArrayList<UserProfileModel>) {
            txt_allfeedback_des.text = "Description : " + feedbackModel.feedback
            txt_allfeedback_title.text = "Description : " + feedbackModel.title
            if (arraylist.size>0) {
                txt_allfeedback_firstname.text = "Name : " + arraylist.get(0).firstname
            }

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