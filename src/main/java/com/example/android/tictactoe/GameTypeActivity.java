package com.example.android.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class GameTypeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_type);

    }

    // get the name of the player
    public String enterName() {
        EditText player1NameBox = findViewById(R.id.player1_nameView);
        return player1NameBox.getEditableText().toString();
    }


    // move to the next activity where player name is captured first before game begins
    public void startSingleGame(View view) {

        String player1Name = enterName();

        //check if the buttons were checked
        RadioButton radioButton3 = findViewById(R.id.threeBythree_Button);
        Boolean gT33 = radioButton3.isChecked();

        RadioButton radioButton5 = findViewById(R.id.fiveByfive_Button);
        Boolean gT55 = radioButton5.isChecked();


// selecting player button
        RadioButton radioO = findViewById(R.id.radio_O);
        Boolean radio_O = radioO.isChecked();

        RadioButton radioX = findViewById(R.id.radio_X);
        Boolean radio_X = radioX.isChecked();


// if checked, load the respective activity for the game to begin

        if (gT33) {
            if (radio_O || radio_X) {

                Intent startIntent = new Intent(GameTypeActivity.this, StartGameThreeActivity.class);
                startIntent.putExtra("gt33", gT33);
                startIntent.putExtra("O", radio_O);
                startIntent.putExtra("X", radio_X);
                startIntent.putExtra("playerName", player1Name);
                if (startIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(startIntent);
                    finish();
                }
            }

        } else if (gT55) {
            if (radio_O ||radio_X) {
                Intent startIntent = new Intent(GameTypeActivity.this, StartGameFiveActivity.class);
                startIntent.putExtra("gt55", gT55);
                startIntent.putExtra("O", radio_O);
                startIntent.putExtra("X", radio_X);
                startIntent.putExtra("playerName", player1Name);
                if (startIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(startIntent);
                    finish();
                }

            }
        }
        // Show an error message as a toast for null entry of player Name
        if (player1Name.equals("")) {
            Toast toast = Toast.makeText(this, "Please enter names of player (s)", Toast.LENGTH_LONG);
            toast.show();
        }

    }


    // Button to return to the main page
    public void homeButton(View view) {
        Intent homeIntent = new Intent(this, MainActivity.class);
        if (homeIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(homeIntent);
            finish();
        }
    }


}



