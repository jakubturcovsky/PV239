package cz.pv239.seminar4.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import cz.pv239.seminar4.R
import kotlinx.android.synthetic.main.activity_file.*
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException

class FileActivity : AppCompatActivity() {

    private var saving: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file)
        findViewById<Button>(R.id.save).setOnClickListener {
            saving = true
            writePermissionCheck()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == REQUEST_WRITE_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (saving) {
                    saveData()
                }
            } else {
                Toast.makeText(this@FileActivity, "Couldn't save data", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun writePermissionCheck() {
        // runtime permissions start with the Marshmallow ->
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            // Lower than Marshmallow -> permissions were granted during the install process
            saveData()
        } else {
            // Let's check whether we already have the permission
            if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                // Permissions granted -> save the data
                saveData()
            } else {
                // Android helper method to tell us if it's useful to show a hint
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    // Show some alert explaining why it is important to grant the permission
                    showWritePermissionRationale(this)
                } else {
                    // Just straight to the point
                    requestPermissions(
                            arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                            REQUEST_WRITE_PERMISSION)
                }
            }
        }
    }

    private fun showWritePermissionRationale(context: Context) {
        val alertBuilder = AlertDialog.Builder(context)
        alertBuilder.setCancelable(true)
        alertBuilder.setTitle("Granting the permission needed")
        alertBuilder.setMessage("Hi there, the app needs to write to the external storage to " +
                "work properly. The content from the screen is stored there. " +
                "Thank you for the understanding.")
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

    private fun saveData() {
        try {
            val content = content.text.toString()

            val rootDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
            if (!rootDir.exists() && !rootDir.mkdirs()) {
                Toast.makeText(this@FileActivity, "Couldn't create directory", Toast.LENGTH_SHORT).show()
                return
            }

            val file = File(rootDir, "seminar4.txt")
            Log.i(TAG, file.absolutePath)

            // Make sure the file exists or create one
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    Toast.makeText(this, "Could not create the file", Toast.LENGTH_SHORT).show()
                    return
                }
            }

            // Writing the data
            val fileWriter = FileWriter(file, true)
            val bufferedWriter = BufferedWriter(fileWriter)
            bufferedWriter.write(content)
            bufferedWriter.close()

            // Looks okay!
            Toast.makeText(this, "Saved.", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            Toast.makeText(this, "Not saved: " + e.message, Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }

    }

    companion object {

        private val TAG = FileActivity::class.java.simpleName

        private const val REQUEST_WRITE_PERMISSION = 42
    }
}
