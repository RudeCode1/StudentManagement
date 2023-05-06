package com.example.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mNameEditText, mIdEditText, mPhoneEditText;
    private Button mSaveButton, mViewSavedButton;
    private TextView mSavedDetailsTextView;
    private SharedPreferences mSharedPreferences;
    private static final String PREFS_NAME = "StudentPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        mNameEditText = findViewById(R.id.name_edit_text);
        mIdEditText = findViewById(R.id.id_edit_text);
        mPhoneEditText = findViewById(R.id.phone_edit_text);
        mSaveButton = findViewById(R.id.save_button);
        mViewSavedButton = findViewById(R.id.view_saved_button);
        mSavedDetailsTextView = findViewById(R.id.saved_details_text_view);

        // Initialize SharedPreferences
        mSharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Set onClickListener for Save button
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveStudentInfo();
            }
        });

        // Set onClickListener for View Saved Details button
        mViewSavedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displaySavedDetails();
            }
        });
    }

    private void saveStudentInfo() {
        String name = mNameEditText.getText().toString();
        String id = mIdEditText.getText().toString();
        String phone = mPhoneEditText.getText().toString();

        // Save student information to SharedPreferences
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("Name", name);
        editor.putString("ID", id);
        editor.putString("Phone", phone);
        editor.apply();

        Toast.makeText(this, "Student information saved", Toast.LENGTH_SHORT).show();
    }

    private void displaySavedDetails() {
        String name = mSharedPreferences.getString("Name", "");
        String id = mSharedPreferences.getString("ID", "");
        String phone = mSharedPreferences.getString("Phone", "");

        // Display saved student information in TextView
        mSavedDetailsTextView.setText("Name: " + name + "\nID: " + id + "\nPhone: " + phone);
    }
}
