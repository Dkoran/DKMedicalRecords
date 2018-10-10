package com.ayatickets.Utils

import android.annotation.SuppressLint
import android.content.Context
import org.json.JSONException
import org.json.JSONObject

class SharedPrefManager private constructor(context: Context) {

    val isLoggedIn: Boolean
        get() {
            val sharedPreferences = mCtx?.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences?.getString(KEY_FULL_NAME, null) != null
        }
    val userEmail: String?
        get() {
            val sharedPreferences = mCtx?.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences?.getString(KEY_USER_EMAIL, null)
        }
    val keyUserAvatar: String?
        get() {
            val sharedPreferences = mCtx?.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences?.getString(KEY_USER_AVATAR, null)
        }

    val keyFullName: String?
        get() {
            val sharedPreferences = mCtx?.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences?.getString(KEY_FULL_NAME, null)
        }
    val keyHubtelResponse: String?
        get() {
            val sharedPreferences = mCtx?.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences?.getString(KEY_HUBTEL_RESPONSE, null)
        }


    val keyPhoneNumber: String?
        get() {
            val sharedPreferences = mCtx?.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences?.getString(KEY_PHONE_NUMBER, null)
        }
    val keyAccessToken: String?
        get() {
            val sharedPreferences = mCtx?.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences?.getString(KEY_ACCESS_TOKEN, null)
        }
    init {
        mCtx = context

    }
    @SuppressLint("CommitPrefEdits")
    fun HubtelResponse(response: JSONObject) {
        val sharedPreferences = mCtx?.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.putString(KEY_HUBTEL_RESPONSE, response.toString())
    }


    @Throws(JSONException::class)
    fun userLogin(user: JSONObject, loginObject: JSONObject): Boolean {

        val sharedPreferences = mCtx?.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()

        editor?.putString(KEY_FULL_NAME, user.getString("name"))
        editor?.putString(KEY_PHONE_NUMBER, user.getString("phone"))
        editor?.putString(KEY_USER_EMAIL, user.getString("email"))
        editor?.putString(KEY_USER_AVATAR, user.getString("avatar"))
        editor?.putString(KEY_ACCESS_TOKEN, loginObject.getString("access_token"))
        editor?.putString(KEY_REFRESH_TOKEN, loginObject.getString("refresh_token"))
        editor?.apply()
        return true
    }

    @Throws(JSONException::class)
    fun userLogin(user: JSONObject): Boolean {
        val sharedPreferences = mCtx?.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.putString(KEY_FULL_NAME, user.getString("name"))
        editor?.putString(KEY_PHONE_NUMBER, user.getString("phone"))
        editor?.putString(KEY_USER_EMAIL, user.getString("email"))
        editor?.putString(KEY_USER_AVATAR, user.getString("avatar"))
        editor?.apply()

        return true
    }


    fun logout(): Boolean {
        val sharedPreferences = mCtx?.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.clear()
        editor?.apply()
        return true
    }

    companion object {

        @SuppressLint("StaticFieldLeak")
        private var mInstance: SharedPrefManager? = null
        @SuppressLint("StaticFieldLeak")
        private var mCtx: Context? = null
        private const val SHARED_PREF_NAME = "mysharedpref12"
        private const val KEY_FULL_NAME = "name"
        private const val KEY_PHONE_NUMBER = "phone"
        private const val KEY_USER_EMAIL = "email"
        private const val KEY_USER_AVATAR = "avatar"
        private const val KEY_ACCESS_TOKEN = "access_token"
        private const val KEY_REFRESH_TOKEN = "refresh_token"
        private const val KEY_HUBTEL_RESPONSE = "response"


        @Synchronized
        fun getInstance(context: Context): SharedPrefManager {
            if (mInstance == null) {
                mInstance = SharedPrefManager(context)
            }
            return mInstance as SharedPrefManager
        }
    }
}