package com.example.android.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class GameTypePlayer2Activity extends GameTypeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_type_player2);


    }


    private String enterName1() {
        EditText player1NameBox = findViewById(R.id.player1_nameView);
        String nameOfPlayer1 = player1NameBox.getEditableText().toString();

        if (nameOfPlayer1.equals("")) {
            nameOfPlayer1 = "Player 1";

        }
        return nameOfPlayer1;
    }

    private String enterName2() {
        EditText player2NameBox = findViewById(R.id.player2_nameView);
        String nameOfPlayer2 = player2NameBox.getEditableText().toString();
        if (nameOfPlayer2.equals("")) {
            nameOfPlayer2 = "Player 2";

        }
        return nameOfPlayer2;
    }


    public void startGame(View view) {
        RadioButton radioButton3 = findViewById(R.id.threeBythree_Button);
        Boolean gT33 = radioButton3.isChecked();

        RadioButton radioButton5 = findViewById(R.id.fiveByfive_Button);
        Boolean gT55 = radioButton5.isChecked();

        // selecting player button
        RadioButton radioO = findViewById(R.id.radio_O);
        Boolean radio_O = radioO.isChecked();

        RadioButton radioX = findViewById(R.id.radio_X);
        Boolean radio_X = radioX.isChecked();


        String player1Name = enterName1();
        String player2Name = enterName2();

        /** if(player1Name.equals("")&&(player2Name.equals(""))) {
         Toast toast = Toast.makeText(this, "Please enter names of player (s)", Toast.LENGTH_SHORT);
         toast.show();
         }**/

        if (gT33) {

            if (radio_O || radio_X) {
                Intent startIntent = new Intent(GameTypePlayer2Activity.this, StartGameThreeModePlayer2Activity.class);
                startIntent.putExtra("player1Name", player1Name);
                startIntent.putExtra("player2Name", player2Name);
                startIntent.putExtra("O", radio_O);
                startIntent.putExtra("X", radio_X);
                if (startIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(startIntent);
                    finish();
                }
            }
        } else if (gT55) {

            if (radio_O ||radio_X) {
                Intent startFIntent = new Intent(GameTypePlayer2Activity.this, StartGameFiveModePlayer2Activity.class);
                startFIntent.putExtra("player1Name", player1Name);
                startFIntent.putExtra("player2Name", player2Name);
                startFIntent.putExtra("O", radio_O);
                startFIntent.putExtra("X", radio_X);
                if (startFIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(startFIntent);
                    finish();

                }
            }


        }
    }

    public void homeButton(View view) {
        Intent homeIntent = new Intent(this, MainActivity.class);
        if (homeIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(homeIntent);
            finish();
        }
    }
}

