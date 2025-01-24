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

        com.example.assignment1.Faculty faculty = (com.example.assignment1.Faculty) getIntent().getSerializableExtra("faculty");

        TextView textViewDetails = findViewById(R.id.textViewDetails);

        String experience = CalculateExperience(faculty.getDateOfJoining());

        String details = "Faculty: " + faculty.getName() + "\n" +
                "Designation: " + faculty.getDesignation() + "\n" +
                "Gender: " + faculty.getGender() + "\n" +
                "Date of Joining: " + faculty.getDateOfJoining() + "\n" +
                "Experience: " + experience;

        textViewDetails.setText(details);
    }

    private String CalculateExperience(String dateOfJoining) {
        if (dateOfJoining == null || dateOfJoining.isEmpty()) {
            return "0 years, 0 months";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date joiningDate = sdf.parse(dateOfJoining);
            Calendar joiningCalendar = Calendar.getInstance();
            joiningCalendar.setTime(joiningDate);

            Calendar today = Calendar.getInstance();

            int years = today.get(Calendar.YEAR) - joiningCalendar.get(Calendar.YEAR);

            if (today.get(Calendar.MONTH) < joiningCalendar.get(Calendar.MONTH) ||
                    (today.get(Calendar.MONTH) == joiningCalendar.get(Calendar.MONTH) &&
                            today.get(Calendar.DAY_OF_MONTH) < joiningCalendar.get(Calendar.DAY_OF_MONTH))) {
                years--;
            }

            int months = today.get(Calendar.MONTH) - joiningCalendar.get(Calendar.MONTH);
            if (months < 0) {
                months += 12;
            }

            if (today.get(Calendar.DAY_OF_MONTH) < joiningCalendar.get(Calendar.DAY_OF_MONTH)) {
                months--;
                if (months < 0) {
                    months = 11;
                }
            }

            return years + " years, " + months + " months";
        } catch (ParseException e) {
            e.printStackTrace();
            return "Invalid Date";
        }
    }
}
