package com.famous.attendancesystem;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    BiometricAct biometricAct;
    Button button_login;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_login = findViewById(R.id.loginBtn);
        button_login.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onClick(View v) {
                biometricAct.setBiometricPrompt(MainActivity.this);
            }
        });


        biometricAct = new BiometricAct();
        if (biometricAct.checkCompatibility(this) == true) {

        }else {
            alertDialog();
        }
    }

    private void alertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Fingerprint Attendance");
        builder.setIcon(R.drawable.ic_launcher_background);
        builder.setMessage("Your device doesn't support fingerprint feature ").setCancelable(false)
                .setPositiveButton("Exit", ((dialog, which) -> finish()));

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}