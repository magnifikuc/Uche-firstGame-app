package com.example.android.tictactoe;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;


public class MainActivity extends AppCompatActivity {

    /**
     * initialize the score of both players
     */
    public static Boolean singlePlayer;
    public static Boolean multiplePlayer;
    public static String radio1String;
    public static String radio2String;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * this Button starts the GameType activity which is to accept the player name and game type
     */


    public void nextButton(View v) {

        RadioButton radioButton1 = findViewById(R.id.single_Player_RButton);
        singlePlayer = radioButton1.isChecked();

        RadioButton radioButton2 = findViewById(R.id.multiple_Player_RButton);
        multiplePlayer = radioButton2.isChecked();

        /**
         * condition to load player type activity
         **/
        if (singlePlayer) {
            radio1String = radioButton1.getText().toString();
            Intent nextSIntent = new Intent(MainActivity.this, GameTypeActivity.class);
            nextSIntent.putExtra("Single Player", singlePlayer);
            nextSIntent.putExtra("single", radio1String);

            if (nextSIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(nextSIntent);
                finish();

            }
        } else {
            radio2String = radioButton2.getText().toString();
            Intent nextMIntent = new Intent(MainActivity.this, GameTypePlayer2Activity.class);
            nextMIntent.putExtra("Multiple Player", multiplePlayer);
            nextMIntent.putExtra("multiple", radio2String);

            if (nextMIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(nextMIntent);
                finish();
            }

        }
    }

}