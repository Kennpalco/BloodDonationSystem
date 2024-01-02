package com.example.blood;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class ListActivity extends AppCompatActivity {
    private TextView dataTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        dataTextView = findViewById(R.id.dataTextView);

        // Retrieve the data passed from MainActivity
        ArrayList<String> namesList = getIntent().getStringArrayListExtra("NAMES_LIST");
        ArrayList<String> bloodTypesList = getIntent().getStringArrayListExtra("BLOOD_TYPES_LIST");

        if (namesList != null && bloodTypesList != null) {
            // Display the data in TextView
            StringBuilder data = new StringBuilder();
            for (int i = 0; i < namesList.size(); i++) {
                data.append("Name: ").append(namesList.get(i))
                        .append("\nBlood Type: ").append(bloodTypesList.get(i))
                        .append("\n\n");
            }
            dataTextView.setText(data.toString());
        }

    }
}