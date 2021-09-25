package com.project.trackmydayapp.activity.admin.fragment

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.trackmydayapp.R
import com.project.trackmydayapp.activity.admin.AddFoodActivity
import com.project.trackmydayapp.activity.admin.FoodDetailActivity
import com.project.trackmydayapp.adapter.FoodListAdapter
import com.project.trackmydayapp.adapter.PhotosAdapter
import com.project.trackmydayapp.database.DatabaseHandler
import com.project.trackmydayapp.databinding.FragmentFoodBinding

class FoodFragment : Fragment() {

    lateinit var ry_food_list: RecyclerView;
    private lateinit var txt_nodata_alluser: TextView
    private lateinit var ed_search: EditText
    private var _binding: FragmentFoodBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val obj_adapter = FoodListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFoodBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.fab.setOnClickListener { view ->
            //         Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            //  .setAction("Action", null).show()
            startActivity( Intent(
                activity,
                AddFoodActivity::class.java
            ));
        }
        txt_nodata_alluser = root.findViewById(R.id.txt_nodata_food);
        ed_search = root.findViewById(R.id.ed_search);
        ry_food_list = root.findViewById(R.id.ry_food_list);
        ry_food_list.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        ed_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                obj_adapter.filter.filter(s.toString())
            }
        });
        return root
    }

    override fun onResume() {
        super.onResume()
        ry_food_list.removeAllViews()
        val db: DatabaseHandler = DatabaseHandler(activity);
        if (db.viewFood().size > 0) {
            txt_nodata_alluser.visibility = View.GONE
        } else {
            txt_nodata_alluser.visibility = View.VISIBLE
        }

        obj_adapter.addData(db.viewFood())
        ry_food_list.adapter = obj_adapter
    }
    fun doSearch(query: String?) {
        println("query"+query)
//        obj_adapter.getFilter()?.filter(query)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}