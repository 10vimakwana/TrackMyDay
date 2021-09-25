package com.project.trackmydayapp.activity.user.fragment

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
import com.project.trackmydayapp.adapter.UserListAdapter
import com.project.trackmydayapp.database.DatabaseHandler
import com.project.trackmydayapp.databinding.FragmentAlluserBinding
import com.project.trackmydayapp.databinding.FragmentFeedbackBinding
import com.project.trackmydayapp.helper.SessionManager


class FeedbackFragment : Fragment() {

    private lateinit var ed_fedback_title: EditText
    private lateinit var ed_feedback: EditText
    private lateinit var btn_feedback: TextView
    private var _binding: FragmentFeedbackBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFeedbackBinding.inflate(inflater, container, false)
        val root: View = binding.root
        ed_fedback_title = root.findViewById(R.id.ed_fedback_title);
        ed_feedback = root.findViewById(R.id.ed_feedback);
        btn_feedback = root.findViewById(R.id.btn_feedback);

        btn_feedback.setOnClickListener {
            if (ed_fedback_title.text.isEmpty()) {
                ed_fedback_title.setError("Enter Title")
            } else if (ed_feedback.text.isEmpty()) {
                ed_feedback.setError("Enter Feedback")
            } else {
                val db: DatabaseHandler = DatabaseHandler(activity);
                val sessionManager: SessionManager = activity?.let { it1 ->
                    SessionManager.getInstance(
                        it1
                    )
                }!!

                val isAdded =
                    db.addFeedback(
                        sessionManager.userId.toString().toInt(),
                        ed_fedback_title.text.toString(),
                        ed_feedback.text.toString()
                    );
                if (isAdded > 0) {
                    Toast.makeText(activity, "Feedback Save Successfully", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(activity, "Something went wrong", Toast.LENGTH_SHORT).show()
                }
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}