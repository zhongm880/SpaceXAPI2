package com.example.spacexapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.spacexapi.view.adapter.SpaceXAdapter
import com.example.spacexapi.viewmodel.LaunchViewModel
import com.example.spacexapi.R
import kotlinx.android.synthetic.main.activity_launch_list.*


class LaunchListActivity : AppCompatActivity() {

    private val spaceXAdapter = SpaceXAdapter(this, listOf(),this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_list)

        initialize()

        LaunchViewModel.launchLiveData.observe(this, {
            spaceXAdapter.updateList(it)
        })


    }

    private fun initialize() {
        val cityName = intent.getStringExtra("city_name_main")
        setSupportActionBar(tb_list)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = cityName
        }

        rv_launch_list.adapter = spaceXAdapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }
}