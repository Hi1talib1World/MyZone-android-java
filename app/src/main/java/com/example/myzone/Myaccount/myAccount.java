package com.example.myzone.Myaccount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.myzone.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class myAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setElevation(0);

        final String address = "mrmambobryan@gmail.com";
        final String text = "Hello there, \nMy name is ";
        final String subject = "Greetings";

        FloatingActionButton emailButton = findViewById(R.id.email_button);
        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_SENDTO);
                myIntent.setData(Uri.parse("mailto:")); // only email apps should handle this
                myIntent.putExtra(Intent.EXTRA_EMAIL, address);
                myIntent.putExtra(Intent.EXTRA_TEXT, text);
                myIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                if (myIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(myIntent);
                }
            }
        });

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);

    }
}
