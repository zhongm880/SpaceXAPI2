package com.example.spacexapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spacexapi.R
import com.example.spacexapi.viewmodel.LaunchViewModel
import com.example.spacexapi.databinding.ActivityMainBinding
import com.example.spacexapi.model.CompanyResponse
import com.example.spacexapi.view.adapter.SpaceXAdapter

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private val spaceXAdapter = SpaceXAdapter(null, this)

    private val viewModel: LaunchViewModel by lazy {
        LaunchViewModel.LaunchViewModelFactory().create(LaunchViewModel::class.java)
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()
        viewModel.launchLiveData.observe(this, {
            spaceXAdapter.updateList(it)
        })
        viewModel.companyLiveData.observe(this, {
            updateCompanyData(it)
        })
    }

    private fun updateCompanyData(companyResponse: CompanyResponse) {
        binding.tvDescribe.text = getString(
            R.string.tv_data_company,
            companyResponse.name,
            companyResponse.founder,
            companyResponse.founded.toString(),
            companyResponse.employees.toString(),
            companyResponse.launch_sites.toString(),
            companyResponse.valuation.toString()
        )
    }

    private fun initialize() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "SpaceX"
        }
        binding.launchesRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.launchesRecyclerview.adapter = spaceXAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val actionMenu = menu?.findItem(R.id.filter)
        val searchView = actionMenu?.actionView as SearchView
        searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Log.d(TAG, "onQueryTextSubmit: $query")
                    viewModel.searchByYear(query)
                    if (!searchView.isIconified)
                        searchView.isIconified = true
                    actionMenu.collapseActionView()
                    return false
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            }
        )
        return true
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId){
//            android.R.id.home -> onBackPressed()
//        }
//        return super.onOptionsItemSelected(item)
//    }
}