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

        // Retrieve the Faculty object passed from MainActivity
        com.example.assignment1.Faculty faculty = (com.example.assignment1.Faculty) getIntent().getSerializableExtra("faculty");

        // Get the TextView where details will be displayed
        TextView textViewDetails = findViewById(R.id.textViewDetails);

        // Calculate the experience using the DateOfJoining
        String experience = CalculateExperience(faculty.getDateOfJoining());

        // Prepare the details string to display
        String details = "Faculty: " + faculty.getName() + "\n" +
                "Designation: " + faculty.getDesignation() + "\n" +
                "Gender: " + faculty.getGender() + "\n" +
                "Date of Joining: " + faculty.getDateOfJoining() + "\n" +
                "Experience: " + experience;

        // Set the details text to the TextView
        textViewDetails.setText(details);
    }

    private String CalculateExperience(String dateOfJoining) {
        // If the DateOfJoining is empty or null, return "0 years, 0 months"
        if (dateOfJoining == null || dateOfJoining.isEmpty()) {
            return "0 years, 0 months";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            // Parse the DateOfJoining to a Date object
            Date joiningDate = sdf.parse(dateOfJoining);
            Calendar joiningCalendar = Calendar.getInstance();
            joiningCalendar.setTime(joiningDate);

            // Get the current date
            Calendar today = Calendar.getInstance();

            // Calculate the number of full years
            int years = today.get(Calendar.YEAR) - joiningCalendar.get(Calendar.YEAR);

            // Adjust the years if the current day hasn't reached the joining day this year
            if (today.get(Calendar.MONTH) < joiningCalendar.get(Calendar.MONTH) ||
                    (today.get(Calendar.MONTH) == joiningCalendar.get(Calendar.MONTH) &&
                            today.get(Calendar.DAY_OF_MONTH) < joiningCalendar.get(Calendar.DAY_OF_MONTH))) {
                years--;
            }

            // Calculate the number of months
            int months = today.get(Calendar.MONTH) - joiningCalendar.get(Calendar.MONTH);
            if (months < 0) {
                months += 12;
            }

            // If the month is negative due to a year difference, adjust it
            if (today.get(Calendar.DAY_OF_MONTH) < joiningCalendar.get(Calendar.DAY_OF_MONTH)) {
                months--;
                if (months < 0) {
                    months = 11;
                }
            }

            return years + " years, " + months + " months";
        } catch (ParseException e) {
            e.printStackTrace();
            return "Invalid Date";  // Return a message if date parsing fails
        }
    }
}
