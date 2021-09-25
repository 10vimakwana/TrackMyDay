package com.project.trackmydayapp.activity.admin.fragment

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
import com.project.trackmydayapp.adapter.FeedbackListAdapter
import com.project.trackmydayapp.adapter.UserListAdapter
import com.project.trackmydayapp.database.DatabaseHandler
import com.project.trackmydayapp.databinding.FragmentAllFeedbackBinding
import com.project.trackmydayapp.databinding.FragmentFeedbackBinding
import com.project.trackmydayapp.helper.SessionManager

class AllFeedbackFragment : Fragment() {

    private lateinit var ry_all_feedback: RecyclerView
    private var _binding: FragmentAllFeedbackBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAllFeedbackBinding.inflate(inflater, container, false)
        val root: View = binding.root
        ry_all_feedback = root.findViewById(R.id.ry_all_feedback);
        ry_all_feedback.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        val db: DatabaseHandler = DatabaseHandler(activity?.applicationContext);

        val obj_adapter = FeedbackListAdapter(activity?.baseContext, db.viewFeedback());
        ry_all_feedback.adapter = obj_adapter


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}