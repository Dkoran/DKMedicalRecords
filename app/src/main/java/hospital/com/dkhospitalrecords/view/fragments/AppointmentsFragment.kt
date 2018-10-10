package hospital.com.dkhospitalrecords.view.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.ayatickets.Utils.Constants
import com.wang.avi.AVLoadingIndicatorView

import hospital.com.dkhospitalrecords.R
import hospital.com.dkhospitalrecords.adapters.AppointmentsAdapter
import hospital.com.dkhospitalrecords.adapters.HistoryAdapter
import hospital.com.dkhospitalrecords.models.AppointmentsCls
import hospital.com.dkhospitalrecords.models.HistoryCls
import hospital.com.dkhospitalrecords.view.HistoryDetailActivity
import org.json.JSONArray
import org.json.JSONObject
import java.util.ArrayList


class AppointmentsFragment : Fragment() {
    private var appointlist = ArrayList<AppointmentsCls>()
    private var recyclerView: RecyclerView? = null
    private var mAdapter: AppointmentsAdapter? = null
    private  lateinit var loader : AVLoadingIndicatorView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view :View = inflater.inflate(R.layout.fragment_appointments, container, false)
        recyclerView =view.findViewById(R.id.recycler_appointments) as RecyclerView
        loader =view.findViewById(R.id.avi_appoints) as AVLoadingIndicatorView
        mAdapter = AppointmentsAdapter(context!!,appointlist){

        }
        val mLayoutManager = LinearLayoutManager(context)
        recyclerView!!.layoutManager = mLayoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.adapter = mAdapter
        fetchdata()

        return view
    }
    private fun fetchdata(){
        AndroidNetworking.get(Constants.APPOINTMENTS_URL+".json")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONArray(object : JSONArrayRequestListener {
                    override fun onResponse(response: JSONArray?) {
                        Log.e("response",response.toString())
                        loader.visibility = View.GONE
                        if (response != null){

                            for (i in 0 until response.length()) {
                                val geet: JSONObject = response.getJSONObject(i)
                                 val appts = AppointmentsCls(geet.getString("name"),geet.getString("location"),geet.getString("date"),geet.getString("status"))

                                appointlist.add(appts)
                            }
                            mAdapter?.notifyDataSetChanged()


                        }

                    }

                    override fun onError(anError: ANError?) {
                        Log.e("error pp1", anError?.errorBody.toString())
                        Log.e("error pp2", anError?.errorDetail)
                        Log.e("error pp2", anError?.errorCode.toString())

                        loader.visibility = View.GONE


                    }


                })
    }


}
