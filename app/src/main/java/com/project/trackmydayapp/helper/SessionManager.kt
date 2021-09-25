package com.project.trackmydayapp.helper

import android.content.Context
import android.content.SharedPreferences
import kotlin.jvm.Synchronized

class SessionManager(private val context: Context) {
    var preferences: SharedPreferences
    var editor: SharedPreferences.Editor
    fun setString(key: String?, value: String?) {
        editor.putString(key, value)
        editor.apply()
    }

    fun getInteger(key: String?): Int {
        return preferences.getString(key, "0")!!.toInt()
    }

    fun getString(key: String?, defaultValues: String): String? {
        return preferences.getString(key, defaultValues)
    }

    fun getBoolean(key: String?): Boolean {
        return java.lang.Boolean.parseBoolean(preferences.getString(key, "false"))
    }

    var token: String?
        get() = preferences.getString("token", "")
        set(link) {
            editor.putString("token", link)
            editor.apply()
        }
    var userId: String?
        get() = preferences.getString("userId", "0")
        set(userid) {
            editor.putString("userId", userid)
            editor.apply()
        }

    companion object {
        private var sessionManager: SessionManager? = null
        @Synchronized
        fun getInstance(context: Context): SessionManager? {
            if (sessionManager == null) sessionManager = SessionManager(context)
            return sessionManager
        }
    }

    init {
        preferences = context.getSharedPreferences("axnxnx", 0)
        editor = preferences.edit()
    }
}