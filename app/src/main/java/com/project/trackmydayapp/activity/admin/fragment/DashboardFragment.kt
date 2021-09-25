package com.project.trackmydayapp.activity.admin.fragment

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.trackmydayapp.R
import com.project.trackmydayapp.adapter.UserListAdapter
import com.project.trackmydayapp.database.DatabaseHandler
import com.project.trackmydayapp.databinding.FragmentAlluserBinding
import com.project.trackmydayapp.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {
    private lateinit var txt_count_user: TextView
    private lateinit var txt_count_food: TextView
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        txt_count_user = root.findViewById(R.id.txt_count_user);
        txt_count_food = root.findViewById(R.id.txt_count_food);
        val db: DatabaseHandler = DatabaseHandler(activity?.applicationContext);

        txt_count_user.text = ""+db.viewUser().size
        txt_count_food.text = ""+db.viewFood().size
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}