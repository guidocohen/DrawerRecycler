package com.guido.navigationdraw.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.guido.navigationdraw.R
import com.guido.navigationdraw.fragments.ArrivalsFragment
import com.guido.navigationdraw.fragments.DeparturesFragment
import com.guido.navigationdraw.fragments.HomeFragment
import com.guido.navigationdraw.toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setNavDrawer()
        setUserHeaderInformation()

        if(savedInstanceState == null) {
            fragmentTransaction(HomeFragment())
            nav_view.menu.getItem(0).isChecked = true
        }
    }

    private fun setNavDrawer() {
        var toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.open_drawer,
            R.string.close_drawer
        )
        toggle.isDrawerIndicatorEnabled = true
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    private fun fragmentTransaction(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    private fun loadFragmentById(id: Int) {
        when (id) {
            R.id.nav_home -> fragmentTransaction(HomeFragment())
            R.id.nav_departures -> fragmentTransaction(DeparturesFragment())
            R.id.nav_arrivals -> fragmentTransaction(ArrivalsFragment())
        }
    }

    private fun showMessageNavItemSelectedById(id: Int) {
        when (id) {
            R.id.nav_profile -> toast("Profile")
            R.id.nav_settings -> toast("Settings")
        }
    }

    private fun setUserHeaderInformation() {
        val name = nav_view.getHeaderView(0).findViewById<TextView>(R.id.textViewName)
        val email = nav_view.getHeaderView(0).findViewById<TextView>(R.id.textViewEmail)

        name?.let { name.text = getString(R.string.user_name) }
        email?.let { email.text = getString(R.string.user_email) }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        showMessageNavItemSelectedById(item.itemId)
        loadFragmentById(item.itemId)
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


    // Si está abierto el drawer y presiono back => me lo cierra pero no la app
    override fun onBackPressed() {
        if(drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else{
            super.onBackPressed()
        }
    }
}
