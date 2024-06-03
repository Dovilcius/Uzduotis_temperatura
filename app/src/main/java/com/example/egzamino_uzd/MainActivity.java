package com.example.egzamino_uzd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.text.Editable;
import android.text.TextWatcher;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText editTextCelsius;
    private EditText editTextFahrenheit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextCelsius = findViewById(R.id.editTextText);
        editTextFahrenheit = findViewById(R.id.editTextText2);

        editTextCelsius.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                convertCelsiusToFahrenheit();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        editTextFahrenheit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                convertFahrenheitToCelsius();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        Button button = findViewById(R.id.nextPageButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        FrameLayout frameLayout = findViewById(R.id.frameLayout2);

        int[] imageArray = {
                R.drawable.image2,
                R.drawable.image3,
                R.drawable.image4,
                R.drawable.image5,
                R.drawable.image6
        };

        Random random = new Random();
        int randomImageId = imageArray[random.nextInt(imageArray.length)];
        frameLayout.setBackgroundResource(randomImageId);
    }

    private void convertCelsiusToFahrenheit() {
        try {
            String celsiusText = editTextCelsius.getText().toString();
            if (!celsiusText.isEmpty()) {
                double celsius = Double.parseDouble(celsiusText);
                double fahrenheit = (celsius * 9 / 5) + 32;
                editTextFahrenheit.setText(formatTemperature(fahrenheit));
            } else {
                editTextFahrenheit.setText("");
            }
        } catch (NumberFormatException e) {
            editTextFahrenheit.setText("Invalid input");
        }
    }

    private void convertFahrenheitToCelsius() {
        try {
            String fahrenheitText = editTextFahrenheit.getText().toString();
            if (!fahrenheitText.isEmpty()) {
                double fahrenheit = Double.parseDouble(fahrenheitText);
                double celsius = (fahrenheit - 32) * 5 / 9;
                editTextCelsius.setText(formatTemperature(celsius));
            } else {
                editTextCelsius.setText("");
            }
        } catch (NumberFormatException e) {
            editTextCelsius.setText("Invalid input");
        }
    }


    private String formatTemperature(double temperature) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(temperature);
    }
}



