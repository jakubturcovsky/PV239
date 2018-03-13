package cz.pv239.seminar4.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.pv239.seminar4.R;

public class FileActivity
        extends AppCompatActivity {

    private static final String TAG = FileActivity.class.getSimpleName();

    private static final int REQUEST_WRITE_PERMISSION = 42;

    @BindView(R.id.content) EditText mContentEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        ButterKnife.bind(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_WRITE_PERMISSION) {
            // TODO: if permission was granted, save file
        }
    }

    @OnClick(R.id.save)
    protected void onSaveClicked() {
        writePermissionCheck();
    }

    private void writePermissionCheck() {
        // TODO: Check if you have the WRITE_EXTERNAL_STORAGE permission
        // TODO: https://developer.android.com/training/permissions/requesting.html#perm-check
    }

    /**
     * Use this method for displaying dialog with info about this permission.
     */
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

    /**
     * Save content of EditText to File
     */
    private void saveData() {
        try {
            String content = mContentEditText.getText().toString();

            // TODO: Write content to File in Documents folder

            // Looks okay!
            Toast.makeText(this, "Saved.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Not saved: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
