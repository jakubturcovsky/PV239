package cz.jakubturcovsky.seminar6

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*

class LocationService : Service() {

    companion object {

        val TAG = LocationService::class.java.simpleName

        fun start(context: Context) {
            context.startService(Intent(context, LocationService::class.java))
        }
    }

    private var fusedLocationClient: FusedLocationProviderClient? = null
    private val locationCallback = LocationChangedCallback()

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        initLocationServices()

        return START_STICKY
    }

    private fun initLocationServices() {
        if (!checkLocationPermissions()) {
            Log.w(TAG, "Service has been started without permissions")
            stopSelf()
            return
        }

        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        this.fusedLocationClient = fusedLocationClient

        try {
            val locationRequest = LocationRequest.create().apply {
                priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
                interval = 1000 * 30
            }
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

    private fun checkLocationPermissions(): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true
        }

        if (ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PERMISSION_GRANTED) {
            return true
        }

        return false
    }

    override fun onDestroy() {
        super.onDestroy()
        fusedLocationClient?.removeLocationUpdates(locationCallback)
    }

    private inner class LocationChangedCallback : LocationCallback() {

        override fun onLocationResult(locationResult: LocationResult?) {
            Log.d(TAG, "Location = ${locationResult?.lastLocation}")
        }
    }
}