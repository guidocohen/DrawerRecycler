package com.guido.navigationdraw.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.guido.navigationdraw.R
import com.guido.navigationdraw.inflate
import com.guido.navigationdraw.listeners.RecyclerFlightListener
import com.guido.navigationdraw.loadByResource
import com.guido.navigationdraw.models.Flight
import kotlinx.android.synthetic.main.recycler_flight.view.*

class FlightAdapter(
    private val flights: ArrayList<Flight>, private val listener: RecyclerFlightListener
) : RecyclerView.Adapter<FlightAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        parent.inflate(R.layout.recycler_flight)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(flights[position], listener)

    override fun getItemCount() = flights.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(flight: Flight, listener: RecyclerFlightListener) = with(itemView) {
            textViewPizza.text = flight.pizza
            imageViewBg.loadByResource(flight.imgResource)
            // Clicks Events
            setOnClickListener { listener.onClick(flight, adapterPosition) }
            buttonDelete.setOnClickListener { listener.onDelete(flight, adapterPosition) }
        }
    }
}