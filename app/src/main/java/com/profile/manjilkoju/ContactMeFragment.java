package com.profile.manjilkoju;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ContactMeFragment extends Fragment {
    TextView tvCall,tvEmail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_me, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvCall = view.findViewById(R.id.tvCall);
        tvEmail = view.findViewById(R.id.tvEmail);

        //setting onClickListener to open the dialer
        tvCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialer();
            }
        });

        //setting onClickListener to open the email app
        tvEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEmail();
            }
        });
    }

    // to open dialer app and call the given number
    public void openDialer(){
        Intent dialerIntent = new Intent(Intent.ACTION_DIAL);
        dialerIntent.setData(Uri.parse("tel:9845124521"));
        startActivity(dialerIntent);
    }

    // to open email app and draft an email to the given emailAddress
    public void openEmail(){
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL,new String[]{"mymail@gmail.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "App Review");
        startActivity(emailIntent);
    }
}