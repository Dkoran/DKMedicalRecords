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
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.ayatickets.Utils.Constants
import com.wang.avi.AVLoadingIndicatorView

import hospital.com.dkhospitalrecords.R
import hospital.com.dkhospitalrecords.adapters.HistoryAdapter
import hospital.com.dkhospitalrecords.models.HistoryCls
import hospital.com.dkhospitalrecords.view.HistoryDetailActivity
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

class HistoryFragment : Fragment() {
    private var historylist = ArrayList<HistoryCls>()
    private var recyclerView: RecyclerView? = null
    private var mAdapter: HistoryAdapter? = null
   private  lateinit var loader : AVLoadingIndicatorView
    private var labss:String = ""
    private var intake:String = ""
    private val precrept:String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
    val view :View = inflater.inflate(R.layout.fragment_history, container, false)
        recyclerView =view.findViewById(R.id.recycler_history) as RecyclerView
        loader =view.findViewById(R.id.avi_history) as AVLoadingIndicatorView

        mAdapter = HistoryAdapter(context!!,historylist){homecls ->
       Log.e("clicked",homecls.clinic_name)
            val intent = Intent(context, HistoryDetailActivity::class.java)
            intent.putExtra("labs", labss)
            intent.putExtra("intake", intake)
            intent.putExtra("cover", homecls.clinic_log)
            intent.putExtra("name", homecls.clinic_name)
            startActivity(intent)

        }
        val mLayoutManager = LinearLayoutManager(context)
        recyclerView!!.layoutManager = mLayoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.adapter = mAdapter

        fetchdata()


        return view
    }
    private fun fetchdata(){
        AndroidNetworking.get(Constants.HISTORY_URL+".json")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(object :JSONObjectRequestListener{
                    override fun onResponse(response: JSONObject?) {
                        loader.visibility = View.GONE
                        if (response != null){
                            loader.visibility = View.GONE

                     val  patienceInfo : JSONObject   =  response.getJSONObject("Patient")
                            val details : JSONArray    =patienceInfo.getJSONArray("CareLocation")
                            val labsobj : JSONObject    =patienceInfo.getJSONObject("Labs")
                            val intakeobject : JSONObject    =patienceInfo.getJSONObject("IntakeCriteria")
                            intake = "DmDxDate\n"+intakeobject.getString("DmDxDate")+"\nInitialHgbA1c"+" "+intakeobject.getString("InitialHgbA1c")
                            val labs : JSONObject    =labsobj.getJSONObject("LDLCholestrol")
                            labss = "LDLCholestrol\n"+"LDLLevel\t:"+labs.getString("LDLLevel")+"\nLDLResultDate"+labs.getString("LDLResultDate")
                            for (i in 0 until details.length()) {
                                val clinicInfo: JSONObject = details.getJSONObject(i)
                                val getinfo = HistoryCls(clinicInfo.getString("FacilityName"),clinicInfo.getString("logo"),clinicInfo.getString("location"),clinicInfo.getString("ContactEmail"))
                                historylist.add(getinfo)
                            }

                        }
                        mAdapter?.notifyDataSetChanged()


                    }

                    override fun onError(anError: ANError?) {
                        loader.visibility = View.GONE
                        Log.e("error",anError?.message)
                        Log.e("error2",anError?.errorBody)
                        Log.e("error3",anError?.errorDetail)


                    }

                }

                )

    }


}
