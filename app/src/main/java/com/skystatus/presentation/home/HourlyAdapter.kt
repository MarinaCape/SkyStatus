package com.skystatus.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.skystatus.R
import com.skystatus.domain.entity.HourlyForecast
import com.skystatus.presentation.utils.formatLocalDateTime
import kotlin.math.roundToInt

class HourlyAdapter(
    private val list: List<HourlyForecast>
) : RecyclerView.Adapter<HourlyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.hour_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(hour: HourlyForecast) {
            val hourText = itemView.findViewById<TextView>(R.id.hour_text)
            val temperatureText = itemView.findViewById<TextView>(R.id.temperature_text)
            val iconWeather = itemView.findViewById<ImageView>(R.id.icon_weather)

            hourText.text = hour.dateTime.formatLocalDateTime()
            temperatureText.text = hour.temperature.value.roundToInt().toString()

            Glide.with(itemView.context)
                .load(hour.weatherIcon.icon)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(iconWeather)
        }

    }
}