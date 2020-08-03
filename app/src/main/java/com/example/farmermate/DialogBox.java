package com.example.farmermate;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DialogBox extends AppCompatDialogFragment {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Reset Password");
        builder.setMessage("กรุณาตั้งรหัสผ่านใหม่ผ่านอีเมลล์ของคุณ");
        builder.setPositiveButton("ดำเนินการต่อ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent myIntent = new Intent(getActivity(), ActivityLogin.class);
                myIntent.putExtra("key", "TEST VALUE"); //Optional parameters
                getActivity().startActivity(myIntent);            }
        });
        return builder.create();
    }
}
