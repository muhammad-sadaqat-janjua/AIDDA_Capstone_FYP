package com.duskdevotee.aidda;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ContactFragment extends Fragment {
    private EditText mEditTextSubject;
    private EditText mEditTextMessage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_contact, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mEditTextSubject = getView().findViewById(R.id.txtsubject_f_contact);
        mEditTextMessage = getView().findViewById(R.id.txtmsg_f_contact);

        Button button = getView().findViewById(R.id.btn_send_frag_contact);
        Button button_clear = getView().findViewById(R.id.btn_clear_frag_contact);
        button_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditTextSubject.setText("");
                mEditTextMessage.setText("");
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
                /*new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // This method will be executed once the timer is over
                        Toast.makeText(getActivity().getApplicationContext(), "Message Sent. We will respond you back ASAP.", Toast.LENGTH_SHORT).show();
                    }
                }, 20000);*/
            }
        });


    }

    private void sendMail() {
        //String recipientList = mEditTextTo.getText().toString();
        String[] recipients = {"muhammad.sadaqat.janjua@gmail.com", "mjanjua@asu.edu"}; //recipientList.split(",");

        String subject = mEditTextSubject.getText().toString();
        String message = mEditTextMessage.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        //only open email clients
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose your Email Client:"));


    }

}
