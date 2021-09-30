package com.project.trackmydayapp.activity.user.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.trackmydayapp.R
import com.project.trackmydayapp.adapter.StepAdapter
import com.project.trackmydayapp.adapter.UserListAdapter
import com.project.trackmydayapp.database.DatabaseHandler
import com.project.trackmydayapp.databinding.FragmentAddStepBinding
import com.project.trackmydayapp.databinding.FragmentCalculateBinding
import com.project.trackmydayapp.helper.SessionManager


class ViewStepFragment : Fragment() {


    private lateinit var ry_step: RecyclerView
    private lateinit var txt_nodata_step: TextView
    private var _binding: FragmentAddStepBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddStepBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val sessionManager: SessionManager = activity?.let { SessionManager.getInstance(it) }!!
        val date = arguments?.get("date");

        ry_step = root.findViewById(R.id.ry_step);
        txt_nodata_step = root.findViewById(R.id.txt_nodata_step);
        ry_step.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        val db: DatabaseHandler = DatabaseHandler(activity?.applicationContext);
        ry_step.removeAllViews()
        if (db.viewSteps(sessionManager.userId!!.toInt(), date.toString()).size > 0) {
            txt_nodata_step.visibility = View.GONE
        } else {
            txt_nodata_step.visibility = View.VISIBLE

        }
        val obj_adapter = StepAdapter(activity?.baseContext, db.viewSteps(sessionManager.userId!!.toInt(), date.toString()));
        ry_step.adapter = obj_adapter


        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}