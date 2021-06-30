package com.duskdevotee.aidda;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MonitorFragment extends Fragment {
    private int CAMERA_PERMISSION_CODE = 1;
    Button buttonRequest;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_monitor, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        buttonRequest = getView().findViewById(R.id.btn_permission_mon_frag);
        Button btndrive = getView().findViewById(R.id.btn_letsdrive);
        btndrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoFaceDetectionActivity(v);
            }
        });

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            //Toast.makeText(getActivity(), "Permission Already Granted!", Toast.LENGTH_SHORT).show();
            buttonRequest.setVisibility(View.GONE);
        } else {
            requestStoragePermission();
        }

        buttonRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Pre-Check::if already permission granted or not?
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity(), "OK! Permission is Granted!", Toast.LENGTH_SHORT).show();
                    buttonRequest.setVisibility(View.GONE);
                } else {
                    requestStoragePermission();
                }
            }
        });


    }

    //explanation why we needed permission:
    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA)) {
            new AlertDialog.Builder(getActivity())
                    .setTitle("Camera Access Needed")
                    .setMessage("This permission is needed to capture the realtime face in order to detect landmarks for alerting user.")
                    .setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);

                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                buttonRequest.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "Permission Granted!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Permission Denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void VideoFaceDetectionActivity(View view) {
        startActivity(new Intent(getActivity(), VideoFaceDetectionActivity.class));
    }
}



