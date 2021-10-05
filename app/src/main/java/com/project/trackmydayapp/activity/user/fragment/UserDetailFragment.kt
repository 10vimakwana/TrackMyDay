package com.project.trackmydayapp.activity.user.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.project.trackmydayapp.R
import com.project.trackmydayapp.activity.user.DashboardActivity
import com.project.trackmydayapp.database.DatabaseHandler
import com.project.trackmydayapp.databinding.FragmentUserDetailBinding
import com.project.trackmydayapp.helper.SessionManager
import com.project.trackmydayapp.model.UserProfileModel

class UserDetailFragment : Fragment() {

    lateinit var ed_firstname: EditText;
    lateinit var ed_height: EditText;
    lateinit var ed_weight: EditText;
    lateinit var ed_age: EditText;
    lateinit var btn_update: TextView;
    lateinit var gender: RadioGroup;
    lateinit var rb_male: RadioButton;
    lateinit var rb_female: RadioButton;
    lateinit var one: RadioButton;
    lateinit var two: RadioButton;
    lateinit var three: RadioButton;
    lateinit var four: RadioButton;
    lateinit var rg_user_activity: RadioGroup;
    private var _binding: FragmentUserDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initUI(root)
        val db: DatabaseHandler = DatabaseHandler(activity);
        val sessionManager: SessionManager = activity?.let { SessionManager.getInstance(it) }!!
        var profilelist: ArrayList<UserProfileModel> = ArrayList<UserProfileModel>()
        profilelist = db.viewProfileUser(sessionManager.userId.toString().toInt())

        if (profilelist.size > 0) {
            ed_firstname.setText(profilelist.get(0).firstname)
            ed_height.setText(profilelist.get(0).height.toString())
            ed_weight.setText(profilelist.get(0).weight.toString())
            ed_age.setText(profilelist.get(0).age.toString())
            if (profilelist.get(0).gender.equals("rb_male")) {
                rb_male.isChecked = true
            } else {
                rb_female.isChecked = true
            }
            if (profilelist.get(0).activity.equals("one")) {
                one.isChecked = true
            } else if (profilelist.get(0).activity.equals("two")) {
                two.isChecked = true
            } else if (profilelist.get(0).activity.equals("three")) {
                three.isChecked = true
            } else if (profilelist.get(0).activity.equals("four")) {
                four.isChecked = true
            } else {
                one.isChecked = true
            }

        }
        btn_update.setOnClickListener {
            if (ed_firstname.text.isEmpty()) {
                ed_firstname.setError("Enter First Name")
            } else if (ed_height.text.isEmpty()) {
                ed_height.setError("Enter Height")
            } else if (ed_weight.text.isEmpty()) {
                ed_weight.setError("Enter Weight")
            } else if (ed_age.text.isEmpty()) {
                ed_age.setError("Enter Age")
            } else {
                val genderId = gender.checkedRadioButtonId
                val genderString = resources.getResourceEntryName(genderId).toString()
                val actvityid = rg_user_activity.checkedRadioButtonId
                val activityString = resources.getResourceEntryName(actvityid).toString()
                val status = db.updateUserData(sessionManager.userId.toString().toInt(),ed_firstname.text.toString(), ed_weight.text.toString().toDouble(),ed_height.text.toString().toDouble(),ed_age.text.toString(),genderString,activityString);
                if (status > -1) {
                    Toast.makeText(activity, "Update Profile Saved", Toast.LENGTH_SHORT).show()
                    requireActivity().onBackPressed()
                } else {
                    Toast.makeText(activity, "Something want wrong", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initUI(root: View) {
        ed_firstname = root.findViewById(R.id.ed_user_firstname);
        ed_height = root.findViewById(R.id.ed_user_height);
        ed_weight = root.findViewById(R.id.ed_user_weight);
        ed_age = root.findViewById(R.id.ed_user_age);
        gender = root.findViewById(R.id.rg_user_gender);
        rb_male = root.findViewById(R.id.rb_male);
        rb_female = root.findViewById(R.id.rb_female);
        one = root.findViewById(R.id.one);
        two = root.findViewById(R.id.two);
        three = root.findViewById(R.id.three);
        four = root.findViewById(R.id.four);
        rg_user_activity = root.findViewById(R.id.rg_user_activity);
        btn_update = root.findViewById(R.id.btn_profile_update);

    }

}