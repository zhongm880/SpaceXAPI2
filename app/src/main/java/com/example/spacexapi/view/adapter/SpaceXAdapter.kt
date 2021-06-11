package com.example.spacexapi.view.adapter

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spacexapi.databinding.ItemLaunchDataBinding
import com.example.spacexapi.model.LaunchesResponse
import com.example.spacexapi.view.LaunchListActivity

class SpaceXAdapter(val activity: Activity, var launchList: List<LaunchesResponse>, val context: Context): RecyclerView.Adapter<SpaceXAdapter.LaunchViewHolder>() {

    class LaunchViewHolder(val binding: ItemLaunchDataBinding): RecyclerView.ViewHolder(binding.root)

    private var compName: String? = "SpaceX"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        val binding = ItemLaunchDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LaunchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        val launchResponseData = launchList[position]

        holder.binding.apply {
            tvDate.text = launchResponseData[position].launch_date_utc
            tvLaunchStatus.text = launchResponseData[position].flight_number.toString()
            //intent to alert button for choosing links
            cvItem.setOnClickListener {
                val detailIntent = Intent(activity, LaunchListActivity::class.java)
                detailIntent.putExtra("detailPosition", position)
                detailIntent.putExtra("articleLink", launchResponseData[position].links.article_link.toString())
                activity.startActivity(detailIntent)
            }
        }
    }

    override fun getItemCount(): Int = launchList.size

    fun updateList(launchList: LaunchesResponse){

        this.launchList = listOf(launchList)
        notifyDataSetChanged()
    }


}