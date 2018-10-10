package hospital.com.dkhospitalrecords.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import hospital.com.dkhospitalrecords.R
import hospital.com.dkhospitalrecords.models.AppointmentsCls

class AppointmentsAdapter(private val context: Context, private val homeClass: ArrayList<AppointmentsCls>?, private val itemclick:(AppointmentsCls)->Unit): RecyclerView.Adapter<AppointmentsAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(context)
                .inflate(R.layout.item_appointments,parent,false)
        return Holder(view, itemclick)
    }

    override fun getItemCount(): Int {
        return homeClass!!.count()

    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bidEvents(homeClass!![position])
    }

    inner class Holder (itemView: View?, val itemclick: (AppointmentsCls) -> Unit) : RecyclerView.ViewHolder(itemView!!) {
        private val name = itemView?.findViewById<TextView>(R.id.ap_name)
        private val location = itemView?.findViewById<TextView>(R.id.ap_location)
        private val date = itemView?.findViewById<TextView>(R.id.ap_date)
       // private val stus = itemView?.findViewById<ImageButton>(R.id.ap_location)

        fun bidEvents(historyCls: AppointmentsCls) {

            name?.text = historyCls.hospital_name
            date?.text = historyCls.hospital_date
            location?.text = historyCls.hospital_location

        }

    }

}

