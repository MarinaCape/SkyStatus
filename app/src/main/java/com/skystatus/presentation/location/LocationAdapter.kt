package com.skystatus.presentation.location

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.skystatus.R
import com.skystatus.domain.entity.City

class LocationAdapter(
    private val list: List<City>,
    private val clickLocation: (city: City) -> Unit
) : RecyclerView.Adapter<LocationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.location_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], clickLocation)
    }

    override fun getItemCount() = list.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(city: City, clickLocation: (city: City) -> Unit) {
            val locationText = itemView.findViewById<TextView>(R.id.location_text)
            val layout = itemView.findViewById<ConstraintLayout>(R.id.locationLayout)

            locationText.text = "${city.localizedName}, ${city.country.localizedName}"
            layout.setOnClickListener {
                clickLocation(city)
            }
        }

    }
}