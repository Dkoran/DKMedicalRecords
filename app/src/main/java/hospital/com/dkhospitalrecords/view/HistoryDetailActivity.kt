package hospital.com.dkhospitalrecords.view

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import hospital.com.dkhospitalrecords.R
import kotlinx.android.synthetic.main.activity_history_detail.*

class HistoryDetailActivity : AppCompatActivity() {
  private lateinit var  cover:ImageView
  private lateinit var  labs:TextView
  private lateinit var  intake:TextView
  private lateinit var  name:TextView



    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_detail)
        cover =this.d_image
        labs =this.labs_details
        intake =this.intak_details
        name =this.d_hospiatl_name
        name.text = intent.getStringExtra("name")
        intake.text = intent.getStringExtra("intake")
        labs.text = intent.getStringExtra("labs")
        Glide.with(this)
                .load(intent.getStringExtra("cover"))
                .into(cover)

    }
}
