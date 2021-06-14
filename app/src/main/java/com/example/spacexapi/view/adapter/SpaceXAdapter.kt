package com.example.spacexapi.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spacexapi.R
import com.example.spacexapi.databinding.ItemLaunchDataBinding
import com.example.spacexapi.model.LaunchesResponse
import com.example.spacexapi.util.Constants.Companion.getTodayFromDays
import com.example.spacexapi.util.Constants.Companion.parseUTCDate
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "SpaceXAdapter"
class SpaceXAdapter(var launchList: LaunchesResponse?, val context: Context) :
    RecyclerView.Adapter<SpaceXAdapter.LaunchViewHolder>() {

    class LaunchViewHolder(val binding: ItemLaunchDataBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        val binding =
            ItemLaunchDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LaunchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        launchList?.let {
            val launchResponseData = it[position]
            holder.binding.apply {
                tvLaunchDate.text = parseUTCDate(launchResponseData.launch_date_utc)
                tvLaunchMission.text = launchResponseData.flight_number.toString()
                tvLaunchRocket.text = context.getString(
                    R.string.tv_launch_rocket,
                    launchResponseData.rocket.rocket_name,
                    launchResponseData.rocket.rocket_type
                )
                tvLaunchDays.text = getTodayFromDays(launchResponseData.launch_date_unix)
                if (launchResponseData.launch_success)
                    ivLaunchSuccess.visibility = View.VISIBLE
                else
                    ivLaunchSuccess.visibility = View.INVISIBLE
                Picasso.get().load(launchResponseData.links.mission_patch)
                    .placeholder(R.drawable.ic_baseline_directions_bus_filled_24)
                    .into(ivPatchImage)
            }
        }
    }

    override fun getItemCount(): Int = launchList?.size ?: 0

    fun updateList(launchList: LaunchesResponse) {
        this.launchList = launchList
        notifyDataSetChanged()
    }


}