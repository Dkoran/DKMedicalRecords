package hospital.com.dkhospitalrecords.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.ayatickets.Utils.Constants
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import hospital.com.dkhospitalrecords.R
import hospital.com.dkhospitalrecords.models.HistoryCls

class HistoryAdapter (private val context: Context, private val homeClass: ArrayList<HistoryCls>?, private val itemclick:(HistoryCls)->Unit): RecyclerView.Adapter<HistoryAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(context)
                .inflate(R.layout.item_history,parent,false)
        return Holder(view, itemclick)
    }

    override fun getItemCount(): Int {
        return homeClass!!.count()

    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bidEvents(homeClass!![position],context)
    }

    inner class Holder (itemView: View?, val itemclick: (HistoryCls) -> Unit) : RecyclerView.ViewHolder(itemView!!) {
        private val name = itemView?.findViewById<TextView>(R.id.clinic_name)
        private val location = itemView?.findViewById<TextView>(R.id.clinic_location)
        private val email = itemView?.findViewById<TextView>(R.id.clinic_mail)
        private val image = itemView?.findViewById<ImageView>(R.id.clinic_image)


        fun bidEvents(historyCls: HistoryCls, contexxt: Context) {

            name?.text = historyCls.clinic_name
            email?.text = historyCls.clinic_email
            location?.text = historyCls.clinic_email

             if (image != null) {
                val requestOptions = RequestOptions()
             //   requestOptions.placeholder(R.drawable.microphone)
               // requestOptions.error(R.drawable.logo_final)
                Glide.with(contexxt)
                        .setDefaultRequestOptions(requestOptions)
                        .load(historyCls.clinic_log).into(image)
                itemView.setOnClickListener { itemclick(historyCls) }
            }
        }

        }

    }
