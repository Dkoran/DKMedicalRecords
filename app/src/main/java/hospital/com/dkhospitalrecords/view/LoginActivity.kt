package hospital.com.dkhospitalrecords.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.support.design.widget.Snackbar
import android.support.design.widget.TextInputEditText
import android.support.v7.widget.AppCompatButton
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.ayatickets.Utils.Constants
import com.ayatickets.Utils.SharedPrefManager
import com.wang.avi.AVLoadingIndicatorView
import hospital.com.dkhospitalrecords.R
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONException
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    private lateinit var login: AppCompatButton
    private lateinit var edphone: TextInputEditText
    private lateinit var preloader: AVLoadingIndicatorView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        login = this.btn_login
        edphone = this.ed_phone
        preloader = this.avi_login
        login.setOnClickListener(clicklistener)


    }
    private val clicklistener = View.OnClickListener {view->
        when (view.id){
            R.id.btn_login ->{
                if (isNetworkConnected()) {
                    pushlogin()
                } else {
                    Snackbar.make(constraint, getString(R.string.no_internet_connected), Snackbar.LENGTH_INDEFINITE)
                            .setAction(getString(R.string.settings)) { startActivity(Intent(Settings.ACTION_WIRELESS_SETTINGS)) }
                            .setActionTextColor(Color.RED)
                            .show()
                }
            }

        }

    }
    private fun pushlogin() {
        if (!validate()) {
            onLoginFailed()
            return
        }
        btn_login.isEnabled = false
        avi_login.show()
        avi_login.visibility = View.VISIBLE
        btn_login.visibility = View.GONE
        //edphone = ed_phone.text.toString()
        onLoginSuccess()
    }

    private fun isNetworkConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager //1
        val networkInfo = connectivityManager.activeNetworkInfo //2
        return networkInfo != null && networkInfo.isConnected //3
    }

    private fun onLoginSuccess() {
        val intent = Intent(this,HomeActivity::class.java)
        startActivity(intent)
        finish()


        // btn_signup.setEnabled(true)
//        setResult(Activity.RESULT_OK, null)
//        AndroidNetworking.post(Constants.LOGIN_URL)
//                .addBodyParameter("phone", ed_phone.text.toString())
//                .setPriority(Priority.MEDIUM)
//                .build()
//                .getAsJSONObject(object : JSONObjectRequestListener {
//                    override fun onResponse(response: JSONObject) {
//                        // do anything with response
//                         Toast.makeText(this@LoginActivity, response.toString(), Toast.LENGTH_LONG).show()
//                         Log.d("Response", "onResponse : " + response.toString())
//                         startActivity(Intent(this@LoginActivity, MainActivity::class.java))
//                         finish()
//                        try {
//                            val loginObject: JSONObject = response
//                            getUser(loginObject)
//                            val success = response.getString("message")
//                            // Toast.makeText(this@LoginActivity, success, Toast.LENGTH_LONG).show()
//
//                        } catch (e: JSONException) {
//                            e.printStackTrace()
//                        }
//                    }
//
//                    override fun onError(error: ANError) {
//                        btn_login.isEnabled = true
//                        avi_login.hide()
//                        avi_login.visibility = View.GONE
//                        btn_login.visibility = View.VISIBLE
//
//                        try {
//                            val tmessage = JSONObject(error.errorBody).getString("message")
//                            val toast = Toast.makeText(applicationContext, tmessage, Toast.LENGTH_LONG)
//                            val toastLayout = toast.view as LinearLayout
//                            val toastTV = toastLayout.getChildAt(0) as TextView
//                            toastTV.setTextSize(TypedValue.COMPLEX_UNIT_PX,
//                                    application.resources.getDimension(R.dimen.TEXT_SIZE))
//                            toast.show()
//                        } catch (e: JSONException) {
//                            e.printStackTrace()
//                        }
//                    }
//                })
    }


    private fun getUser(loginObject: JSONObject) {
        val accessToken = loginObject.getString("access_token")
        AndroidNetworking.get(Constants.USER_DETAILS_URL)
                .addHeaders("Authorization", "Bearer $accessToken")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        val userObject: JSONObject = response
                        SharedPrefManager.getInstance(applicationContext)
                                .userLogin(userObject, loginObject)
                        avi_login.hide()
                        avi_login.visibility = View.GONE
                        btn_login.visibility = View.VISIBLE
                        startActivity(Intent(applicationContext, MainActivity::class.java))
                        finish()
                    }

                    override fun onError(error: ANError) {
                        // handle error
                    }
                })
    }


    fun onLoginFailed() {
        Toast.makeText(baseContext, "Kindly check your fields", Toast.LENGTH_LONG).show()
        avi_login.hide()
        avi_login.visibility = View.GONE
        login.visibility = View.VISIBLE
        login.isEnabled = true
    }


    private fun validate(): Boolean {
        var valid = true
        val phone = ed_phone.text.toString()

        if (phone.isEmpty() || !android.util.Patterns.PHONE.matcher((phone)).matches()) {
            edphone.error = "enter a valid phone number"
            edphone.background.setColorFilter(resources.getColor(android.R.color.black), PorterDuff.Mode.SRC_ATOP)
            valid = false
        } else {
            edphone.error = null
        }

        return valid
    }


}
