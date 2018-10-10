package hospital.com.dkhospitalrecords.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.CoordinatorLayout
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import hospital.com.dkhospitalrecords.R
import hospital.com.dkhospitalrecords.helper.BottomNavigationBehavior
import hospital.com.dkhospitalrecords.view.fragments.AppointmentsFragment
import hospital.com.dkhospitalrecords.view.fragments.DashBoardFragment
import hospital.com.dkhospitalrecords.view.fragments.HistoryFragment
import hospital.com.dkhospitalrecords.view.fragments.NotificationsFragment
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        val fragment: Fragment
        when (item.itemId) {
            R.id.navigation_home -> {
                fragment = HistoryFragment()
                text_view.text  = "History"
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                fragment = DashBoardFragment()
                text_view.text  = "Dashboard"
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_appoint -> {
                fragment = AppointmentsFragment()
                text_view.text  = "Appointment"
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                fragment = NotificationsFragment()
                loadFragment(fragment)
                text_view.text  = "Notifications"
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        val actionBar = supportActionBar
        actionBar!!.title = ""
        actionBar.elevation = 4.0F
        actionBar.setDisplayShowHomeEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        val navigation = findViewById<BottomNavigationView>(R.id.navigation)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        // attaching bottom sheet behaviour - hide / show on scroll
        val layoutParams = navigation.layoutParams as CoordinatorLayout.LayoutParams
        layoutParams.behavior = BottomNavigationBehavior()
        loadFragment(HistoryFragment())
        text_view.text  = "History"


    }
    private fun loadFragment(fragment: Fragment) {
        // load fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}
