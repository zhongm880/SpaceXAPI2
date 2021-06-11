package com.example.spacexapi.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.spacexapi.viewmodel.LaunchViewModel
import com.example.spacexapi.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {

    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_search.setOnClickListener {
            getLaunch()
            val launchIntent = Intent(this, LaunchListActivity::class.java)
            launchIntent.putExtra("company_name", et_company_name.text.toString().capitalize(Locale.getDefault()))
            startActivity(launchIntent)
        }
    }

    private fun getLaunch() {
        val flightNum = 1
        job = lifecycleScope.launch(Dispatchers.Main){
            LaunchViewModel.getLaunchData(flightNum)
        }

    }
}