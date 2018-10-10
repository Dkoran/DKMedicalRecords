package hospital.com.dkhospitalrecords.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.AppCompatButton
import android.util.Log
import android.view.View
import android.widget.TextView
import hospital.com.dkhospitalrecords.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {
    private lateinit var login: AppCompatButton
    private lateinit var signup: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        login = this.main_login
        signup = this.main_signup
        login.setOnClickListener (clickListener)
        signup.setOnClickListener (clickListener)

    }
    private val clickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.main_login-> {
                Log.e("clicked 1","clicked")

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            R.id.main_signup->{
                Log.e("clicked2","clicked")
                val intent = Intent(this,SignUpActivity::class.java)
                startActivity(intent)


            }

        }



    }
}
