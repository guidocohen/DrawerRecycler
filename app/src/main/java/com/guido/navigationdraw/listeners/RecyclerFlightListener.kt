package com.guido.navigationdraw.listeners

import com.guido.navigationdraw.models.Flight

interface RecyclerFlightListener {
    fun onClick(recyclerFlightListener: Flight, position: Int)
    fun onDelete(flight: Flight, position: Int)
}
