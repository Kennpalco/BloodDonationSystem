package com.example.blood;
import java.util.ArrayList;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> namesList = new ArrayList<>();
    private ArrayList<String> bloodTypesList = new ArrayList<>();
    private EditText nameEditText, bloodTypeEditText;
    private Button submitButton, listButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.nameEditText);
        bloodTypeEditText = findViewById(R.id.bloodTypeEditText);
        submitButton = findViewById(R.id.submitButton);
        listButton = findViewById(R.id.listButton);

        bloodTypeEditText.setFilters(new InputFilter[]{
                new InputFilter.AllCaps(),
                new InputFilter.LengthFilter(2),
                new InputFilter() {
                    @Override
                    public CharSequence filter(
                            CharSequence source, int start, int end,
                            Spanned dest, int dstart, int dend) {

                        String regex = "[ABO]";
                        if (source instanceof Spanned) {
                            Spanned spanned = (Spanned) source;
                            for (int i = start; i < end; i++) {
                                if (!String.valueOf(spanned.charAt(i)).matches(regex)) {
                                    return "";
                                }
                            }
                        } else {
                            for (int i = start; i < end; i++) {
                                if (!String.valueOf(source.charAt(i)).matches(regex)) {
                                    return "";
                                }
                            }
                        }
                        return null;
                    }
                }

        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String bloodType = bloodTypeEditText.getText().toString();

                // Store the data in lists
                namesList.add(name);
                bloodTypesList.add(bloodType);
                clearInputFields();

                displaySubmissionMessage(name, bloodType);
            }
        });

        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openListActivity();
            }
        });

    }

    private void displaySubmissionMessage(String name, String bloodType) {
        String message = "Thank you, " + name + " for your submission!\n" +
                "Your blood type is: " + bloodType + ".\n"+
                "We appreciate your willingness to donate.";

        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.show();

        // Show the toast for a specific duration (6 seconds) and then cancel it
        new CountDownTimer(6000, 1000) { // 6000 milliseconds (6 seconds) with a 1000ms interval
            public void onTick(long millisUntilFinished) {
                // Countdown in progress (if needed)
            }

            public void onFinish() {
                toast.cancel(); // Dismiss the toast after 6 seconds
            }
        }.start();
    }
    private void clearInputFields() {
        nameEditText.setText("");
        bloodTypeEditText.setText("");
    }
    private void openListActivity() {
        Intent intent = new Intent(MainActivity.this, ListActivity.class);

        // Pass the lists to ListActivity
        intent.putStringArrayListExtra("NAMES_LIST", namesList);
        intent.putStringArrayListExtra("BLOOD_TYPES_LIST", bloodTypesList);

        startActivity(intent);
    }



}
