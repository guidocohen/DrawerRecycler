package com.guido.navigationdraw.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.guido.navigationdraw.R
import com.guido.navigationdraw.adapters.FlightAdapter
import com.guido.navigationdraw.listeners.RecyclerFlightListener
import com.guido.navigationdraw.models.Flight
import com.guido.navigationdraw.toast
import kotlinx.android.synthetic.main.fragment_comida.view.*

class ComidaFragment : Fragment() {

    private val list: ArrayList<Flight> by lazy { getFlights() }
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: FlightAdapter
    private val layoutManager by lazy { LinearLayoutManager(context) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.setTitle(R.string.comida_fragment_title)
        val rootView = inflater.inflate(R.layout.fragment_comida, container, false)
        recycler = rootView.recyclerView as RecyclerView
        setRecyclerView()

        return rootView
    }

    private fun setRecyclerView() {
        recycler.setHasFixedSize(true)
        recycler.itemAnimator = DefaultItemAnimator()
        recycler.layoutManager = layoutManager
        adapter = (FlightAdapter(list, object: RecyclerFlightListener {
            override fun onClick(flight: Flight, position: Int) {
                activity?.toast("A comer la pizza de ${flight.pizza}!!")
            }

            override fun onDelete(flight: Flight, position: Int) {
                list.remove(flight)
                adapter.notifyItemRemoved(position)
                activity?.toast("Pizza de ${flight.pizza} eliminada correctamente")
            }
        }))
        recycler.adapter = adapter
    }


    private fun getFlights(): ArrayList<Flight> {
        return object: ArrayList<Flight>() {
            init {
                add(Flight(1, "Mozzarella", R.drawable.mozzarella))
                add(Flight(2, "Calabresa", R.drawable.calabresa))
                add(Flight(3, "Champiniones", R.drawable.champiniones))
                add(Flight(4, "Jamón y Morrones", R.drawable.especial))
                add(Flight(5, "Fugazzetta", R.drawable.fugazzetta))
                add(Flight(6, "Hawaiana", R.drawable.hawaiana))
                add(Flight(7, "Mozzarella", R.drawable.mozzarella))
                add(Flight(8, "Calabresa", R.drawable.calabresa))
                add(Flight(9, "Champiniones", R.drawable.champiniones))
            }
        }
    }

}
