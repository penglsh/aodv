package com.example.myapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateBluetoothMacAddressFromUser();
    }

    private void updateBluetoothMacAddressFromUser() {
        final EditText editText=new EditText(this);
        editText.setFocusable(true);
        editText.setVisibility(View.VISIBLE);

        AlertDialog.Builder dialogBuilder=new AlertDialog.Builder(this);
        AlertDialog alertDialog=dialogBuilder.setTitle("打开蓝牙的情况下，输入本机的蓝牙MAC地址")
                .setView(editText)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String bluetoothMacAddress=editText.getText().toString();
                        Intent intent=new Intent(getApplicationContext(),com.example.myapp.MainActivity.class);
                        intent.putExtra("bluetoothMacAddress",bluetoothMacAddress);
                        startActivity(intent);

                    }
                })
                .setCancelable(false)
                .create();
        alertDialog.show();

    }
}
