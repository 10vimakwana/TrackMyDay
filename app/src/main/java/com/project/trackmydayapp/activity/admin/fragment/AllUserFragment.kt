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

class AllUserFragment : Fragment() {

    private lateinit var ry_all_user: RecyclerView
    private lateinit var txt_nodata_alluser: TextView
    private var _binding: FragmentAlluserBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAlluserBinding.inflate(inflater, container, false)
        val root: View = binding.root
        ry_all_user = root.findViewById(R.id.ry_all_user);
        txt_nodata_alluser = root.findViewById(R.id.txt_nodata_alluser);
        ry_all_user.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        return root
    }

    override fun onResume() {
        super.onResume()
        val db: DatabaseHandler = DatabaseHandler(activity?.applicationContext);
        ry_all_user.removeAllViews()
        if (db.viewUser().size > 0) {
            txt_nodata_alluser.visibility = View.GONE
        } else {
            txt_nodata_alluser.visibility = View.VISIBLE

        }
        val obj_adapter = UserListAdapter(activity?.baseContext, db.viewUser());
        ry_all_user.adapter = obj_adapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}