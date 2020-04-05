package cz.jakubturcovsky.seminar6

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val REQUEST_ACCESS_FINE_LOCATION_PERMISSION = 48
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBroadcastSample()

        startLocationSample()
    }

    private fun initBroadcastSample() {
        startService(Intent(this, BroadcastService::class.java))

        send_broadcast.setOnClickListener {
            val intent = Intent(BroadcastService.FOO_ACTION).apply { putExtra("message", "Do bears have claws?") }
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == REQUEST_ACCESS_FINE_LOCATION_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                LocationService.start(this)
            } else {
                Toast.makeText(this, "Couldn't get location", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun startLocationSample() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            LocationService.start(this)
        } else {
            if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                LocationService.start(this)
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                    showWritePermissionRationale(this)
                } else {
                    requestPermissions(
                            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                            REQUEST_ACCESS_FINE_LOCATION_PERMISSION
                    )
                }
            }
        }
    }

    private fun showWritePermissionRationale(context: Context) {
        val alertBuilder = AlertDialog.Builder(context)
        alertBuilder.setCancelable(true)
        alertBuilder.setTitle("Granting the permission needed")
        alertBuilder.setMessage("Hi there, please give this sample location permission.")
        alertBuilder.setPositiveButton(android.R.string.ok) { dialog, which ->
            // this way you can get to the screen to set the permissions manually
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.fromParts("package", packageName, null))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        val alert = alertBuilder.create()
        alert.show()
    }
}
