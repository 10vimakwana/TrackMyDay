package com.project.trackmydayapp.activity.user.fragment

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.project.trackmydayapp.R
import com.project.trackmydayapp.database.DatabaseHandler
import com.project.trackmydayapp.databinding.FragmentCalculateBinding
import com.project.trackmydayapp.databinding.FragmentFeedbackBinding
import com.project.trackmydayapp.helper.SessionManager

import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.project.trackmydayapp.activity.admin.AddFoodActivity
import com.project.trackmydayapp.activity.admin.UserDetailActivity
import com.project.trackmydayapp.activity.user.AddRecipeActivity
import com.project.trackmydayapp.activity.user.AddStepActivity
import com.project.trackmydayapp.activity.user.ProfileUserActivity
import com.project.trackmydayapp.activity.user.ViewDataActivity
import com.project.trackmydayapp.model.UserProfileModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS
import kotlin.collections.ArrayList
import android.text.format.DateUtils





class CalculateFragment : Fragment() {


    private lateinit var fab1: FloatingActionButton
    private lateinit var fab2: FloatingActionButton
    private lateinit var fab3: FloatingActionButton
    private lateinit var fab4: FloatingActionButton
    private lateinit var ed_date: EditText
    private lateinit var btn_view: TextView
    private lateinit var txt_calories: TextView
    private lateinit var btn_today: TextView
    private lateinit var txt_step_calories: TextView
    private lateinit var txt_receipe_calories: TextView
    private lateinit var txt_target_calories: TextView

    private var _binding: FragmentCalculateBinding? = null
    var flag = true

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCalculateBinding.inflate(inflater, container, false)
        val root: View = binding.root
        btn_view = root.findViewById(R.id.btn_view);
        btn_today = root.findViewById(R.id.btn_today);

        fab1 = root.findViewById(R.id.fab1) as FloatingActionButton
        fab2 = root.findViewById(R.id.fab2) as FloatingActionButton
        fab3 = root.findViewById(R.id.fab3) as FloatingActionButton
        fab4 = root.findViewById(R.id.fab4) as FloatingActionButton
        ed_date = root.findViewById(R.id.ed_date) as EditText
        txt_calories = root.findViewById(R.id.txt_calories) as TextView
        txt_receipe_calories = root.findViewById(R.id.txt_receipe_calories) as TextView
        txt_step_calories = root.findViewById(R.id.txt_step_calories) as TextView
        txt_target_calories = root.findViewById(R.id.txt_target_calories) as TextView

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val df = SimpleDateFormat("dd/MM/yyyy")
        val formattedDate = df.format(c.time)
        ed_date.setText(formattedDate)
        ed_date.setOnClickListener(View.OnClickListener {
            val dpd = DatePickerDialog(
                requireActivity(),
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    // Display Selected date in textbox
                    val date = SimpleDateFormat("dd/MM/yyyy").parse( ""+dayOfMonth + "/" + (monthOfYear + 1) + "/" + year)
                    ed_date.setText(df.format(date.time))
                },
                year,
                month,
                day
            )
            dpd.show()

        })
        fab1.setOnClickListener(View.OnClickListener {
            if (flag) {
                fab2.show()
                fab3.show()
                fab4.show()
                flag = false
            } else {
                fab2.hide()
                fab3.hide()
                fab4.hide()
                flag = true
            }
        })

        btn_today.setOnClickListener {
            startActivity(
                Intent(
                    activity,
                    ViewDataActivity::class.java
                ).putExtra("date", formattedDate)
            )
        }
        btn_view.setOnClickListener {
            startActivity(
                Intent(
                    activity,
                    ViewDataActivity::class.java
                ).putExtra("date", ed_date.text.toString())
            )
        }
        fab4.setOnClickListener {
            val db: DatabaseHandler = DatabaseHandler(activity);
            val sessionManager: SessionManager = activity?.let { SessionManager.getInstance(it) }!!
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val df = SimpleDateFormat("dd/MM/yyyy")
            val formattedDate = df.format(c.time)
            var dt: Date? = Date()
            c.time = dt
            c.add(Calendar.DATE, -1)
            val beforedate = df.format(c.time)

            val recipelist = db.viewRecipe(sessionManager.userId!!.toInt(), beforedate)
            for (item in recipelist) {
                println("geting " +item)
                val status = db.addRecipe(
                    sessionManager.userId.toString().toInt(),
                    item.foodId,
                    item.calories,
                    item.recipeName,
                    formattedDate
                );
            }
            fab2.hide()
            fab3.hide()
            fab4.hide()
        }
        fab2.setOnClickListener {
            startActivity(
                Intent(
                    activity,
                    AddRecipeActivity::class.java
                ).putExtra("date", ed_date.text.toString())
            )
        }
        fab3.setOnClickListener {
            startActivity(
                Intent(
                    activity,
                    AddStepActivity::class.java
                ).putExtra("date", ed_date.text.toString())
            )
        }


        return root
    }

    override fun onResume() {
        super.onResume()
        val db: DatabaseHandler = DatabaseHandler(activity);
        val sessionManager: SessionManager = activity?.let { SessionManager.getInstance(it) }!!
        var profilelist: ArrayList<UserProfileModel> = ArrayList<UserProfileModel>()
        profilelist = db.viewProfileUser(sessionManager.userId.toString().toInt())
        if (profilelist.size > 0) {
            if (profilelist.get(0).gender.toString().equals("R.id.rb_male")) {
                var cal = 0.0
                if (profilelist.get(0).activity.equals("one")) {
                    cal =
                        ((66 + (6.2 * profilelist.get(0).weight) + (12.7 * profilelist.get(0).height) - (6.76 * profilelist.get(
                            0
                        ).age)) * 1.2)
                } else if (profilelist.get(0).activity.equals("two")) {
                    cal =
                        ((66 + (6.2 * profilelist.get(0).weight) + (12.7 * profilelist.get(0).height) - (6.76 * profilelist.get(
                            0
                        ).age)) * 1.37)
                } else if (profilelist.get(0).activity.equals("three")) {
                    cal =
                        ((66 + (6.2 * profilelist.get(0).weight) + (12.7 * profilelist.get(0).height) - (6.76 * profilelist.get(
                            0
                        ).age)) * 1.55)
                } else if (profilelist.get(0).activity.equals("four")) {
                    cal =
                        ((66 + (6.2 * profilelist.get(0).weight) + (12.7 * profilelist.get(0).height) - (6.76 * profilelist.get(
                            0
                        ).age)) * 1.725)
                }
                val c = Calendar.getInstance()
                val df = SimpleDateFormat("dd/MM/yyyy")
                val formattedDate = df.format(c.time)

                val cal2place:Double = String.format("%.2f", cal).toDouble()

                txt_target_calories.text = ""+cal2place
                val totalcalories =
                    db.viewRecipeCal(sessionManager.userId.toString().toInt(), formattedDate)
                txt_receipe_calories.text = ""+totalcalories
                val t_cal = totalcalories + cal
                val totalsteps =
                    db.viewStepsCal(sessionManager.userId.toString().toInt(), formattedDate)
                var stepcalories = 0;
                if (totalsteps == 0) {
                    stepcalories = 0
                } else if (totalsteps < 1000) {
                    stepcalories = 20
                } else if (totalsteps > 1000) {
                    stepcalories = 40
                } else if (totalsteps > 2000) {
                    stepcalories = 80
                } else if (totalsteps > 3000) {
                    stepcalories = 120
                } else if (totalsteps > 4000) {
                    stepcalories = 160
                } else if (totalsteps > 5000) {
                    stepcalories = 200
                } else if (totalsteps > 6000) {
                    stepcalories = 240
                } else if (totalsteps > 7000) {
                    stepcalories = 280
                } else if (totalsteps > 8000) {
                    stepcalories = 320
                } else if (totalsteps > 9000) {
                    stepcalories = 340
                } else if (totalsteps > 10000) {
                    stepcalories = 380
                } else {
                    stepcalories = 500
                }
                txt_step_calories.text =""+ stepcalories
                val gradtotal = t_cal - stepcalories
                val total2place:Double = String.format("%.2f", gradtotal).toDouble()
                txt_calories.text = "" + total2place
            } else {
                var cal = 0.0
                if (profilelist.get(0).activity.equals("one")) {
                    cal =
                        ((655.1 + (6.2 * profilelist.get(0).weight) + (12.7 * profilelist.get(0).height) - (6.76 * profilelist.get(
                            0
                        ).age)) * 1.2)
                } else if (profilelist.get(0).activity.equals("two")) {
                    cal =
                        ((655.1 + (6.2 * profilelist.get(0).weight) + (12.7 * profilelist.get(0).height) - (6.76 * profilelist.get(
                            0
                        ).age)) * 1.37)
                } else if (profilelist.get(0).activity.equals("three")) {
                    cal =
                        ((655.1 + (6.2 * profilelist.get(0).weight) + (12.7 * profilelist.get(0).height) - (6.76 * profilelist.get(
                            0
                        ).age)) * 1.55)
                } else if (profilelist.get(0).activity.equals("four")) {
                    cal =
                        ((655.1 + (6.2 * profilelist.get(0).weight) + (12.7 * profilelist.get(0).height) - (6.76 * profilelist.get(
                            0
                        ).age)) * 1.725)
                }
                val c = Calendar.getInstance()
                val df = SimpleDateFormat("dd/MM/yyyy")
                val formattedDate = df.format(c.time)
                val cal2place:Double = String.format("%.2f", cal).toDouble()

                txt_target_calories.text = ""+cal2place

                val totalcalories =
                    db.viewRecipeCal(sessionManager.userId.toString().toInt(), formattedDate)
                txt_receipe_calories.text = ""+totalcalories
                val t_cal = totalcalories + cal

                val totalsteps =
                    db.viewStepsCal(sessionManager.userId.toString().toInt(), formattedDate)
                var stepcalories = 0;
                if (totalsteps == 0) {
                    stepcalories = 0
                } else if (totalsteps < 1000) {
                    stepcalories = 20
                } else if (totalsteps > 1000) {
                    stepcalories = 40
                } else if (totalsteps > 2000) {
                    stepcalories = 80
                } else if (totalsteps > 3000) {
                    stepcalories = 120
                } else if (totalsteps > 4000) {
                    stepcalories = 160
                } else if (totalsteps > 5000) {
                    stepcalories = 200
                } else if (totalsteps > 6000) {
                    stepcalories = 240
                } else if (totalsteps > 7000) {
                    stepcalories = 280
                } else if (totalsteps > 8000) {
                    stepcalories = 320
                } else if (totalsteps > 9000) {
                    stepcalories = 340
                } else if (totalsteps > 10000) {
                    stepcalories = 380
                } else {
                    stepcalories = 500
                }
                txt_step_calories.text =""+ stepcalories

                val gradtotal = t_cal - stepcalories
                val total2place:Double = String.format("%.2f", gradtotal).toDouble()
                txt_calories.text = "" + total2place
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}