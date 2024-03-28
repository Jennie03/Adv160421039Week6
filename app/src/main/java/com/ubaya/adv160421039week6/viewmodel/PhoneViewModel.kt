package com.ubaya.adv160421039week6.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.adv160421039week6.model.Phone

class PhoneViewModel(application: Application): AndroidViewModel(application)
{
    val phonesLD = MutableLiveData<ArrayList<Phone>>()
    val phoneLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh(){
        Log.d("CEKMASUK", "masukvolley")
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/phone/phone.json"
        val stringRequest = StringRequest(Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<Phone>>() { }.type
                val result = Gson().fromJson<List<Phone>>(it, sType)
                phonesLD.value = result as ArrayList<Phone>?
                loadingLD.value = false

                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
                phoneLoadErrorLD.value = false
                loadingLD.value = false
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
        phoneLoadErrorLD.value = false
        loadingLD.value = false
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

}