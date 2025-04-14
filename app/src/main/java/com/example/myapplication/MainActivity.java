package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class MainActivity extends AppCompatActivity {

    private EditText studentName, prelimGrade, midtermGrade, finalsGrade;
    private TextView resultTextView, studentNameTextView, semGradeTextView, ptEqTextView, remarksTextView;
    private Button computeButton, newEntryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentName = findViewById(R.id.studentName);
        prelimGrade = findViewById(R.id.editTextNumber2);
        midtermGrade = findViewById(R.id.editTextNumber3);
        finalsGrade = findViewById(R.id.editTextNumber4);
        resultTextView = findViewById(R.id.resultTextVIew);
        studentNameTextView = findViewById(R.id.studentNameTextView);
        semGradeTextView = findViewById(R.id.semGradeTextView);
        ptEqTextView = findViewById(R.id.ptEqTextView);
        remarksTextView = findViewById(R.id.remarksTextView);
        computeButton = findViewById(R.id.compute);
        newEntryButton = findViewById(R.id.newEntry);

        computeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showComputeWarning();
            }
        });

        newEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNewEntryWarning();
            }
        });
    }

    private void computeGrades() {
        try {
            String name = studentName.getText().toString();
            double prelim = Double.parseDouble(prelimGrade.getText().toString());
            double midterm = Double.parseDouble(midtermGrade.getText().toString());
            double finals = Double.parseDouble(finalsGrade.getText().toString());

            double semestralGrade = (prelim + midterm + finals) / 3;
            String pointEquivalent;

            if (semestralGrade <= 100 && semestralGrade >= 96) {
                pointEquivalent = "1.00";
            } else if (semestralGrade <= 95 && semestralGrade >= 91) {
                pointEquivalent = "1.50";
            } else if (semestralGrade <= 90 && semestralGrade >= 86) {
                pointEquivalent = "2.00";
            } else if (semestralGrade <= 85 && semestralGrade >= 81) {
                pointEquivalent = "2.50";
            } else if (semestralGrade <= 80 && semestralGrade >= 76) {
                pointEquivalent = "3.00";
            } else if (semestralGrade <= 75) {
                pointEquivalent = "5.00";
            } else {
                pointEquivalent = "Invalid"; 
            }

            String remarks = semestralGrade >= 75 ? "PASSED" : "FAILED";

            studentNameTextView.setText("Student Name: " + name);
            semGradeTextView.setText("Semestral Grade: " + String.format("%.2f", semestralGrade));
            ptEqTextView.setText("Pt. Equivalent: " + pointEquivalent);
            remarksTextView.setText("Remarks: " + remarks);
 
            if (remarks.equals("PASSED")) {
                remarksTextView.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
            } else {
                remarksTextView.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
            }
        } catch (NumberFormatException e) {
            resultTextView.setText("Please enter valid grades.");
        }
    }

    private void resetFields() {
        studentName.setText("");
        prelimGrade.setText("");
        midtermGrade.setText("");
        finalsGrade.setText("");
        studentNameTextView.setText("Student Name: ");
        semGradeTextView.setText("Semestral Grade: ");
        ptEqTextView.setText("Pt. Equivalent: ");
        remarksTextView.setText("Remarks: ");
        resultTextView.setText("Results: ");
    }

    private void showNewEntryWarning() {
        new AlertDialog.Builder(this)
            .setTitle("Warning")
            .setMessage("Are you sure?")
            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    resetFields();
                    remarksTextView.setTextColor(getResources().getColor(android.R.color.black));
                }
            })
            .setNegativeButton("No", null)
            .show();
    }

    private void showComputeWarning() {
        new AlertDialog.Builder(this)
            .setTitle("Confirmation")
            .setMessage("All Entries Correct?")
            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    computeGrades();
                }
            })
            .setNegativeButton("No", null)
            .show();
    }
}