package com.example.newsonly

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class MySingleton constructor(context:Context)
{
    companion object
    {
        @Volatile
        private var INSTANCE: MySingleton? =null
        fun getInstance(context: Context )=
            INSTANCE ?: synchronized(this){
                INSTANCE ?: MySingleton(context).also {
                    INSTANCE=it
                }
            }
    }

    private val requestQueue:RequestQueue by lazy {
        // application context is key,it keeps u from leaking the
        // Activity or Broadcastreciever if someone passes on in
        Volley.newRequestQueue(context.applicationContext)
    }
    fun <T> addToRequestQueue(req: Request<T>)
    {
        requestQueue.add(req)
    }

}