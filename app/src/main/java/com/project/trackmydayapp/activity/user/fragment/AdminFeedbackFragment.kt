package com.project.trackmydayapp.activity.user.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.trackmydayapp.R
import com.project.trackmydayapp.adapter.RecipeListAdapter
import com.project.trackmydayapp.adapter.UserAllFeedbackAdapter
import com.project.trackmydayapp.database.DatabaseHandler
import com.project.trackmydayapp.databinding.FragmentAddRecipeBinding
import com.project.trackmydayapp.databinding.FragmentAdmiFeedbackBinding
import com.project.trackmydayapp.helper.SessionManager
import com.project.trackmydayapp.model.FeedbackModel


class AdminFeedbackFragment : Fragment() {


    private lateinit var ry_admin_feedback: RecyclerView
    private var _binding: FragmentAdmiFeedbackBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAdmiFeedbackBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val sessionManager: SessionManager = activity?.let { SessionManager.getInstance(it) }!!
        val db: DatabaseHandler = DatabaseHandler(activity?.applicationContext);


        ry_admin_feedback = root.findViewById(R.id.ry_admin_feedback);
        ry_admin_feedback.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        val arraylist: ArrayList<FeedbackModel> =
            db.viewFeedback(sessionManager.userId.toString().toInt());
        val obj_adapter = UserAllFeedbackAdapter(activity?.baseContext, arraylist);
        ry_admin_feedback.adapter = obj_adapter

//        if (arraylist.size>0) {
//
//
//            txt_adminfeedback_que.text = "Question :  " + arraylist.get(0).title
//            txt_adminfeedback_ans.text = "Ans : " + arraylist.get(0).reply
//        }else{
//            txt_adminfeedback_que.text = " No Question "
//
//        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}