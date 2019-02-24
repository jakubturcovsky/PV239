package cz.pv239.seminar4.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import cz.pv239.seminar4.R;

public class FileActivity
        extends AppCompatActivity {

    private static final String TAG = FileActivity.class.getSimpleName();

    private static final int REQUEST_WRITE_PERMISSION = 42;

    private boolean mSaving;

    EditText mContentEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        mContentEditText = findViewById(R.id.content);
        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mSaving = true;
                writePermissionCheck();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_WRITE_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (mSaving) {
                    saveData();
                }
            } else {
                Toast.makeText(FileActivity.this, "Couldn't save data", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void writePermissionCheck() {
        // runtime permissions start with the Marshmallow ->
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            // Lower than Marshmallow -> permissions were granted during the install process
            saveData();
        } else {
            // Let's check whethere we already have the permission
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                // Permissions granted -> save the data
                saveData();
            } else {
                // Android helper method to tell us if it's useful to show a hint
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    // Show some alert explaining why it is important to grant the permission
                    showWritePermissionRationale(this);
                } else {
                    // Just straight to the point
                    requestPermissions(
                            new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            REQUEST_WRITE_PERMISSION);
                }
            }
        }
    }

    private void showWritePermissionRationale(final Context context) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle("Granting the permission needed");
        alertBuilder.setMessage("Hi there, the app needs to write to the external storage to " +
                "work properly. The content from the screen is stored there. " +
                "Thank you for the understanding.");
        alertBuilder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // this way you can get to the screen to set the permissions manually
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.fromParts("package", getPackageName(), null));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }

    private void saveData() {
        try {
            String content = mContentEditText.getText().toString();

            File rootDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            if (!rootDir.exists() && !rootDir.mkdirs()) {
                Toast.makeText(FileActivity.this, "Couldn't create directory", Toast.LENGTH_SHORT).show();
                return;
            }

            File file = new File(rootDir, "seminar4.txt");
            Log.i(TAG, file.getAbsolutePath());

            // Make sure the file exists or create one
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    Toast.makeText(this, "Could not create the file", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            // Writing the data
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content);
            bufferedWriter.close();

            // Looks okay!
            Toast.makeText(this, "Saved.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Not saved: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
