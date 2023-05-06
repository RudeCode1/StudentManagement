package com.example.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class ViewDetailsActivity extends AppCompatActivity {

    private TextView mNameTextView, mIdTextView, mPhoneTextView;
    private SharedPreferences mSharedPreferences;
    private static final String PREFS_NAME = "StudentPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);

        // Initialize views
        mNameTextView = findViewById(R.id.name_text_view);
        mIdTextView = findViewById(R.id.id_text_view);
        mPhoneTextView = findViewById(R.id.phone_text_view);

        // Initialize SharedPreferences
        mSharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Retrieve saved student information
        String name = mSharedPreferences.getString("name", "");
        String id = mSharedPreferences.getString("id", "");
        String phone = mSharedPreferences.getString("phone", "");

        // Display saved student information
        mNameTextView.setText("Name: " + name);
        mIdTextView.setText("ID: " + id);
        mPhoneTextView.setText("Phone: " + phone);
    }
}
