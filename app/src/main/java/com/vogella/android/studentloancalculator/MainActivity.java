package com.vogella.android.studentloancalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout loanAmount, loanTerm, yearlyInterestRate, loanFee;
    private Button buttonCalculate;
    private TextView totalPayment, totalInterestAmount, monthlyPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListenerOnButton();
    }

    private void addListenerOnButton() {
        loanAmount = (TextInputLayout) findViewById(R.id.loanAmount);
        loanTerm = (TextInputLayout) findViewById(R.id.loanTerm);
        yearlyInterestRate = (TextInputLayout) findViewById(R.id.yearlyInterestRate);
        loanFee = (TextInputLayout) findViewById(R.id.loanFee);
        buttonCalculate = (Button) findViewById(R.id.calculateButton);
        totalPayment = (TextView) findViewById (R.id.totalPayment);
        totalInterestAmount = (TextView) findViewById (R.id.totalInterestAmount);
        monthlyPayment = (TextView) findViewById (R.id.monthlyPayment);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get Values From TextInputLayouts
                String temp = "";
                temp = loanAmount.getEditText().getText().toString();
                int loanAmount = Integer.parseInt(temp);
                temp = loanTerm.getEditText().getText().toString();
                int loanTerm= Integer.parseInt(temp);
                temp = yearlyInterestRate.getEditText().getText().toString();
                int yearlyInterestRate = Integer.parseInt(temp);
                temp = loanFee.getEditText().getText().toString();
                int loanFee = Integer.parseInt(temp);

                double totalPaymentNum = ((loanAmount+loanFee)*Math.pow(1+yearlyInterestRate*0.01,loanTerm));
                double monthlyPaymentNum = totalPaymentNum/(loanTerm*12);
                double totalInterestAmountNum = totalPaymentNum - (loanAmount+loanFee);

                DecimalFormat df = new DecimalFormat("#.##");

                totalPayment.setText("Total Payment: $" + df.format(totalPaymentNum));
                totalInterestAmount.setText("Total Interest Amount: $" + df.format(totalInterestAmountNum));
                monthlyPayment.setText("Monthly Payment: $" + df.format(monthlyPaymentNum));


            }
        });

    }
}