package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Faculty faculty = (Faculty) getIntent().getSerializableExtra("faculty");

        TextView textViewDetails = findViewById(R.id.textViewDetails);

        int experience = CalculateExperience(faculty.getDateOfJoining());

        String details = "Faculty: " + faculty.getName() + "\n" +
                "Designation: " + faculty.getDesignation() + "\n" +
                "Gender: " + faculty.getGender() + "\n" +
                "Date of Joining: " + faculty.getDateOfJoining() + "\n" +
                "Experience: " + experience + " years";

        textViewDetails.setText(details);
    }

    private int CalculateExperience(String dateOfJoining) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date joiningDate = sdf.parse(dateOfJoining);
            Calendar joiningCalendar = Calendar.getInstance();
            joiningCalendar.setTime(joiningDate);

            Calendar today = Calendar.getInstance();

            int years = today.get(Calendar.YEAR) - joiningCalendar.get(Calendar.YEAR);

            if (today.get(Calendar.DAY_OF_YEAR) < joiningCalendar.get(Calendar.DAY_OF_YEAR)) {
                years--;
            }

            return years;
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
