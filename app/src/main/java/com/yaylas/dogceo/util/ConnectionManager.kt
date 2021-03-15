package com.yaylas.dogceo.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Build
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConnectionManager @Inject constructor(@ApplicationContext private val context : Context) {
    private val connectivity : ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private var connected = true
    init {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val networkCallback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    connected = true
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    connected = false
                }
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                connectivity.registerDefaultNetworkCallback(networkCallback)
            } else {
                connectivity.registerNetworkCallback(NetworkRequest.Builder().build(), networkCallback)
            }
        }

    }


    fun isConnected() : Boolean {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return connected
        } else {
            val networkInfo = connectivity.activeNetworkInfo
            return networkInfo?.isConnected ?: false
        }
    }


}