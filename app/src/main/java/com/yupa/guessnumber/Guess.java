package com.yupa.guessnumber;

import android.content.Intent;
import android.database.Cursor;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class Guess extends AppCompatActivity {
    static int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess);
        TextView tvIntro = findViewById(R.id.txtIntro);
        TextView tvGame = findViewById(R.id.txtGameTitle);

        //get values
        int money = getIntent().getIntExtra("m", -1);
        final int scope = getIntent().getIntExtra("numberScope", -1);
        final int rightNumber = new Random().nextInt(scope);
        tvGame.setText("Game Guess NUmber Scope IN:\n\n" + scope);
        tvIntro.setText("Rules : \n\nNow you are in guessing number Game!\n\nand you have three chances to guess it!!\n\nThe more you guess, the less bonus you get." +
                "\n\n If you failed, you will loss 1/2 of the highest bonus!");

        count = 0;
        ShowMessage.showCenter(this, " " + rightNumber);
        Button btnGuess = findViewById(R.id.btnGuess);
        btnGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Guess.this, MainActivity.class);
                EditText gNumber = findViewById(R.id.edtGNumber);
                int baseB = -1;
                int g = Integer.valueOf(gNumber.getText().toString());
                if (Guess.count < 3) {
                    if (g == rightNumber) {
                        ShowMessage.showCenter(Guess.this, "Cool!");
                        switch (scope) {
                            case 10:
                                if (count == 0) {
                                    baseB = 10;
                                } else if (count == 1) {
                                    baseB = 5;
                                } else {
                                    baseB = 2;
                                }
                                break;
                            case 50:
                                if (count == 0) {
                                    baseB = 50;
                                } else if (count == 1) {
                                    baseB = 25;
                                } else {
                                    baseB = 10;
                                }
                                break;
                            case 100:
                                if (count == 0) {
                                    baseB = 100;
                                } else if (count == 1) {
                                    baseB = 50;
                                } else {
                                    baseB = 20;
                                }
                                break;
                        }
                        intent.putExtra("v", baseB);
                        ringPlayback(RingtoneManager.TYPE_NOTIFICATION);
                        startActivity(intent);
                    } else if (g > rightNumber && count < 2) {
                        ShowMessage.showCenter(Guess.this, "Your number is bigger!");
                        count++;
                    } else if (g < rightNumber && count < 2) {
                        ShowMessage.showCenter(Guess.this, "Your number is smaller!");
                        count++;
                    } else {
                        ShowMessage.showCenter(Guess.this, "Too much times tried, you Failed!");
                        intent.putExtra("v", scope / 2 * -1);
                        ringPlayback(RingtoneManager.TYPE_NOTIFICATION);
                        startActivity(intent);
                    }

                }
            }

        });
    }

    private void ringPlayback(int type){
        Uri notification = RingtoneManager.getDefaultUri(type);
        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
        r.play();
    }
}