package com.yupa.guessnumber;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static int initMoney = 100;


    @Override
    protected void onResume() {
        super.onResume();
        TextView tv = findViewById(R.id.txtMoney);
        int temp = getIntent().getIntExtra("v", 0);
        if (temp != 0) {
            initMoney += temp;
        }
        tv.setText(String.valueOf(initMoney));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = findViewById(R.id.txtMoney);
        Button firstButton = findViewById(R.id.btnFirst);
        Button secondButton = findViewById(R.id.btnSecond);
        Button thirdButton = findViewById(R.id.btnThird);
        final Intent intent = new Intent(MainActivity.this, Guess.class);
        tv.setText(String.valueOf(initMoney));
        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("m", initMoney);
                intent.putExtra("numberScope", 10);
                startActivity(intent);
            }
        });
        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("m", initMoney);
                intent.putExtra("numberScope", 50);
                startActivity(intent);

            }
        });

        thirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("m", initMoney);
                intent.putExtra("numberScope", 100);
                startActivity(intent);
            }
        });

    }
}
