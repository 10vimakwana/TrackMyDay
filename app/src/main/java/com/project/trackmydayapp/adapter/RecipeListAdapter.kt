package com.project.trackmydayapp.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.trackmydayapp.R
import com.project.trackmydayapp.database.DatabaseHandler
import com.project.trackmydayapp.model.RecipeModel
import com.project.trackmydayapp.model.StepModel

class RecipeListAdapter(
    val context: Context?,
    val arraylist: ArrayList<RecipeModel>,
    val onadapterClick: onClick
) :
    RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {

    val db: DatabaseHandler = DatabaseHandler(context);


    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_recipe, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(arraylist.get(position), position)


        holder.iv_delete_recepie.setOnClickListener {
            onadapterClick.deleteReciepe(arraylist.get(position).recipeId)

        }

    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return arraylist.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txt_recipeName: TextView
        var iv_delete_recepie: ImageView

        init {
            txt_recipeName = itemView.findViewById<TextView>(R.id.txt_recipeName)
            iv_delete_recepie = itemView.findViewById<ImageView>(R.id.iv_delete_recepie)

        }

        fun bindItems(recipeModel: RecipeModel, position: Int) {
            txt_recipeName.text = "" + (position + 1) + " : " + recipeModel.recipeName
        }

    }


    interface onClick {
        fun deleteReciepe(reciepId: Int) {}
    }

}
