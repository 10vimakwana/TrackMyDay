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
import com.project.trackmydayapp.adapter.StepAdapter
import com.project.trackmydayapp.database.DatabaseHandler
import com.project.trackmydayapp.databinding.FragmentAddStepBinding
import com.project.trackmydayapp.databinding.FragmentChartBinding
import com.project.trackmydayapp.helper.SessionManager

import com.anychart.AnyChartView

import com.anychart.chart.common.dataentry.ValueDataEntry

import com.anychart.chart.common.dataentry.DataEntry

import com.anychart.AnyChart

import com.anychart.charts.Pie
import com.project.trackmydayapp.model.UserProfileModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class ChartFragment : Fragment() {
    private var _binding: FragmentChartBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentChartBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val db: DatabaseHandler = DatabaseHandler(activity);
        val sessionManager: SessionManager = activity?.let { SessionManager.getInstance(it) }!!
        val c = Calendar.getInstance()
        val df = SimpleDateFormat("dd/MM/yyyy")
        val formattedDate = df.format(c.time)
        val anyChartView = root.findViewById(R.id.any_chart_view) as AnyChartView
        anyChartView.setProgressBar(root.findViewById(R.id.progress_bar));
        val column = AnyChart.column()
        val totalcalories =
            db.viewRecipeCal(sessionManager.userId.toString().toInt(), formattedDate)
        val totalsteps = db.viewStepsCal(sessionManager.userId.toString().toInt(), formattedDate)

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


        var profilelist: java.util.ArrayList<UserProfileModel> = ArrayList<UserProfileModel>()
        profilelist = db.viewProfileUser(sessionManager.userId.toString().toInt())
        var cal = 0.0
        if (profilelist.size > 0) {
            if (profilelist.get(0).gender.toString().equals("R.id.rb_male")) {
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
            } else {
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
            }
        }

        val data: MutableList<DataEntry> = ArrayList()

        data.add(ValueDataEntry("Recipe Calories", totalcalories))
        data.add(ValueDataEntry("Steps", stepcalories))
        data.add(ValueDataEntry("Calories", cal))

        column.data(data)

        anyChartView.setChart(column)

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}