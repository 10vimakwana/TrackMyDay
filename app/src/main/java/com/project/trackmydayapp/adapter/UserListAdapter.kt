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
import com.project.trackmydayapp.activity.admin.FoodDetailActivity
import com.project.trackmydayapp.activity.admin.UserDetailActivity
import com.project.trackmydayapp.database.DatabaseHandler
import com.project.trackmydayapp.model.UserModel
import com.project.trackmydayapp.model.UserProfileModel

class UserListAdapter(val context: Context?, val arraylist: ArrayList<UserModel>) :
    RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    val db: DatabaseHandler = DatabaseHandler(context);


    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_all_user, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var profilelist: ArrayList<UserProfileModel> = ArrayList<UserProfileModel>()
        profilelist = db.viewProfileUser(arraylist.get(position).userId)
        holder.iv_alluser_delete.setOnClickListener(View.OnClickListener {
            db.deleteProfileUser(profilelist.get(0))
            db.deleteUser(arraylist.get(position))
            profilelist.removeAt(0)
            arraylist.removeAt(position)
            notifyDataSetChanged()
        })
        holder.bindItems(arraylist.get(position), profilelist)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return arraylist.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txt_alluser_email: TextView
        var txt_alluser_firstname: TextView
        var txt_alluser_height: TextView
        var txt_alluser_weight: TextView
        var txt_alluser_age: TextView
        var iv_alluser_delete: ImageView

        init {
            txt_alluser_email = itemView.findViewById<TextView>(R.id.txt_alluser_email)
            txt_alluser_firstname = itemView.findViewById<TextView>(R.id.txt_alluser_firstname)
            txt_alluser_height = itemView.findViewById<TextView>(R.id.txt_alluser_height)
            txt_alluser_weight = itemView.findViewById<TextView>(R.id.txt_alluser_weight)
            txt_alluser_age = itemView.findViewById<TextView>(R.id.txt_alluser_age)
            iv_alluser_delete = itemView.findViewById<ImageView>(R.id.iv_alluser_delete)

        }

        fun bindItems(userModel: UserModel, arraylist: ArrayList<UserProfileModel>) {
            txt_alluser_email.text = "Email : " + userModel.email
            if (arraylist.size>0) {
                txt_alluser_firstname.text = "First Name : " + arraylist.get(0).firstname
                txt_alluser_height.text = "Height : " + arraylist.get(0).height
                txt_alluser_weight.text = "Weight : " + arraylist.get(0).weight
                txt_alluser_age.text = "Age : " + arraylist.get(0).age
            }

            itemView.setOnClickListener(View.OnClickListener {
                itemView.getContext()
                    .startActivity(
                        Intent(
                            itemView.getContext(),
                            UserDetailActivity::class.java
                        ).putExtra("userid", userModel.userId).putExtra("emailid", userModel.email)
                    )
            })

        }

    }

}
