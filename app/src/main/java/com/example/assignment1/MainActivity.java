package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    com.example.assignment1.Faculty faculty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        faculty = new com.example.assignment1.Faculty();

        FillDesignation();
    }

    public void SaveData(View view) {
        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextDOJ = findViewById(R.id.editTextDOJ);

        faculty.setName(editTextName.getText().toString());
        faculty.setGender(GetGender());
        faculty.setDateOfJoining(editTextDOJ.getText().toString());

        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("faculty", faculty);
        startActivity(intent);
    }

    private String GetGender() {
        RadioGroup rbGroupGender = findViewById(R.id.rbGroupGender);
        RadioButton selectedRadioButton;

        int selectedRadioButtonId = rbGroupGender.getCheckedRadioButtonId();

        if (selectedRadioButtonId != -1) {
            selectedRadioButton = findViewById(selectedRadioButtonId);
            return selectedRadioButton.getText().toString();
        } else {
            return "";
        }
    }

    private void FillDesignation() {
        String[] designations = {"Professor", "Associate Professor", "Assistant Professor"};
        Spinner spinnerDesignation = findViewById(R.id.spinnerDesignation);

        ArrayAdapter<String> aa = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, designations);

        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDesignation.setAdapter(aa);
        spinnerDesignation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                faculty.setDesignation(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                faculty.setDesignation("");
            }
        });
    }

    public void ShowDatePicker(View view) {
        DatePickerDialog dialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        monthOfYear++;
        faculty.setDateOfJoining(dayOfMonth + "-" + monthOfYear + "-" + year);

        EditText editTextDOJ = findViewById(R.id.editTextDOJ);
        editTextDOJ.setText(faculty.getDateOfJoining());
    }
}
