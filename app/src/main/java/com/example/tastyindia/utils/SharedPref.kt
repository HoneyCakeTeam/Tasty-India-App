package com.example.tastyindia.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPref(val context: Context) {
    companion object{
        var dark = 0
        var sharedPref:SharedPreferences? = null

        fun sharedPref(context: Context){
            sharedPref =context.getSharedPreferences("fileName" , Context.MODE_PRIVATE)
        }

        fun setNightModeState(state:Boolean?){
            val editor = sharedPref!!.edit()
            editor.putBoolean("nightMode" , state!!)
            editor.apply()
        }

        fun loadNightModeState():Boolean?{
            return sharedPref!!.getBoolean("nightMode" , false)
        }
    }
}