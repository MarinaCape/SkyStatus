package com.skystatus.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.skystatus.R
import com.skystatus.domain.model.DailyForecast
import java.time.format.TextStyle
import java.util.Locale
import kotlin.math.roundToInt

class DailyAdapter(
    private val list: List<DailyForecast>,
    private val clickItem: (DailyForecast) -> Unit
) : RecyclerView.Adapter<DailyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.day_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], clickItem)
    }

    override fun getItemCount() = list.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(day: DailyForecast, clickItem: (DailyForecast) -> Unit) {
            val layout = itemView.findViewById<ConstraintLayout>(R.id.layout)
            val dayText = itemView.findViewById<TextView>(R.id.day_text)
            val minTemperature = itemView.findViewById<TextView>(R.id.min_temperature_text)
            val maxTemperature = itemView.findViewById<TextView>(R.id.max_temperature_text)
            val windText = itemView.findViewById<TextView>(R.id.wind_text)
            val rainText = itemView.findViewById<TextView>(R.id.probability_rain)
            val iconWeather = itemView.findViewById<ImageView>(R.id.icon_weather)

            dayText.text = day.dateTime.dayOfWeek.getDisplayName(TextStyle.SHORT_STANDALONE, Locale.getDefault()).replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
            minTemperature.text = "${day.temperature.minimum.value.roundToInt()}º"
            maxTemperature.text = "${day.temperature.maximum.value.roundToInt()}º"
            windText.text = "${day.wind.speed.value.roundToInt()} ${day.wind.speed.unit}"
            rainText.text = "${day.precipitationProbability} %"

            Glide.with(itemView.context)
                .load(day.weatherIcon.icon)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(iconWeather)

            layout.setOnClickListener {
                clickItem(day)
            }

        }

    }
}