package com.project.trackmydayapp.activity.user.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.trackmydayapp.R
import com.project.trackmydayapp.adapter.RecipeListAdapter
import com.project.trackmydayapp.adapter.StepAdapter
import com.project.trackmydayapp.database.DatabaseHandler
import com.project.trackmydayapp.databinding.FragmentAddRecipeBinding
import com.project.trackmydayapp.databinding.FragmentAddStepBinding
import com.project.trackmydayapp.helper.SessionManager

class ViewRecipeFragment : Fragment(), RecipeListAdapter.onClick {


    private lateinit var ry_recipe: RecyclerView
    private lateinit var txt_nodata_recipe: TextView
    private var _binding: FragmentAddRecipeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddRecipeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val sessionManager: SessionManager = activity?.let { SessionManager.getInstance(it) }!!

        val date = arguments?.get("date");

        ry_recipe = root.findViewById(R.id.ry_recipe);
        txt_nodata_recipe = root.findViewById(R.id.txt_nodata_recipe);
        ry_recipe.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        val db: DatabaseHandler = DatabaseHandler(activity?.applicationContext);


        if (db.viewRecipe(sessionManager.userId!!.toInt(), date.toString()).size > 0) {
            txt_nodata_recipe.visibility = View.GONE
        } else {
            txt_nodata_recipe.visibility = View.VISIBLE
        }
        ry_recipe.removeAllViews()
        val obj_adapter = RecipeListAdapter(
            activity?.baseContext,
            db.viewRecipe(sessionManager.userId!!.toInt(), date.toString()),
            this
        );
        ry_recipe.adapter = obj_adapter


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun deleteReciepe(reciepId: Int) {
        val db: DatabaseHandler = DatabaseHandler(activity?.applicationContext);
        val sessionManager: SessionManager = activity?.let { SessionManager.getInstance(it) }!!
        val date = arguments?.get("date");
        val alertDialog: AlertDialog = android.app.AlertDialog.Builder(activity).create()
        alertDialog.setTitle("Alert")
        alertDialog.setMessage("Alert message to be shown")
        alertDialog.setButton(
            AlertDialog.BUTTON_POSITIVE,
            "OK",
            DialogInterface.OnClickListener { dialog, which ->
                db.deleteRecipes(reciepId)
                ry_recipe.removeAllViews()
                val obj_adapter = RecipeListAdapter(
                    activity?.baseContext,
                    db.viewRecipe(sessionManager.userId!!.toInt(), date.toString()),
                    this
                );
                ry_recipe.adapter = obj_adapter

            })
        alertDialog.setButton(
            AlertDialog.BUTTON_NEGATIVE,
            "Cancel",
            DialogInterface.OnClickListener { dialog, which ->
                alertDialog.dismiss()
            })
        alertDialog.show()


    }
}