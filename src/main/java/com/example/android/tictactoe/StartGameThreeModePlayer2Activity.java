package com.example.android.tictactoe;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class StartGameThreeModePlayer2Activity extends AppCompatActivity {

    int play_turn = 1;
    int win = 0;
    int isGameOver = 0;
    int checkEndOfGame = 0;
    String activeTurn;
    GridLayout threeGrid;
    Button threeBoard[][] = new Button[3][3];
    int threeBoardMatrix[][] = new int[3][3];
    TextView playerTurn;
    String player1Name;
    String player2Name;
    String turnMessageO;
    String turnMessageX;
    Boolean radio_O;
    Boolean radio_X;
    int counter = 0;
    int player1Win = 0, player2Win = 0, draw = 0;
    int ChangePlaySate = 0;
    AlertDialog.Builder builder;
    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_game_three_mode_player2);

        builder = new AlertDialog.Builder(this);


        Intent intent = getIntent();
        player1Name = intent.getExtras().getString("player1Name");
        player2Name = intent.getExtras().getString("player2Name");
        radio_O = intent.getBooleanExtra("O", true);
        radio_X = intent.getBooleanExtra("X", true);
        playerOXTurn();

        turnMessageO = "'s turn(O)";
        turnMessageX = "'s turn (X)";



        threeGrid = findViewById(R.id.gridview_Layout);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                threeBoard[i][j] = (Button) threeGrid.getChildAt(3 * i + j);
                threeBoardMatrix[i][j] = 0;
            }
        }
        if (ChangePlaySate == 1) {
            ChangePlaySate = 0;
            play_turn = 2;
        }
    }



    public void playerOXTurn() {

        if (ChangePlaySate == 0) {
            if (radio_O) {
                playerTurn = findViewById(R.id.playerTurn_view);
                activeTurn = player1Name + turnMessageO;
                playerTurn.setText(activeTurn);
            } else if (radio_X) {
                playerTurn = findViewById(R.id.playerTurn_view);
                activeTurn = player1Name + turnMessageX;
                playerTurn.setText(activeTurn);
            }
        } else if (ChangePlaySate == 1) {
            if (radio_O) {
                playerTurn = findViewById(R.id.playerTurn_view);
                activeTurn = player2Name + turnMessageO;
                playerTurn.setText(activeTurn);
            } else if (radio_X) {
                playerTurn = findViewById(R.id.playerTurn_view);
                activeTurn = player2Name + turnMessageX;
                playerTurn.setText(activeTurn);
            }
        }
    }


    public void selectCell(View view) {
        int index = threeGrid.indexOfChild(view);
        int i = index / 3;
        int j = index % 3;
        flag = 0;
        if (play_turn == 1 && isGameOver == 0 && !(threeBoard[i][j].getText().toString().equals("X")) && !(threeBoard[i][j].getText().toString().equals("O"))) {


            if (ChangePlaySate == 0) {
                playerOXTurn();
                threeBoard[i][j].setText("O");
                threeBoardMatrix[i][j] = 1;
                play_turn = 2;

                ChangePlaySate = 1;


            }


        } else if (play_turn == 2 && isGameOver == 0 && !(threeBoard[i][j].getText().toString().equals("X")) && !(threeBoard[i][j].getText().toString().equals("O"))) {

            if (ChangePlaySate == 1) {

                threeBoard[i][j].setText("X");
                threeBoardMatrix[i][j] = 1;
                play_turn = 1;
                playerOXTurn();
                ChangePlaySate = 0;
            }

        }

        anyWinner();
        if (isGameOver == 1) {
            if (win == 1) {
                builder.setMessage(player1Name + " wins!").setTitle("Game over");
                if (checkEndOfGame == 0) {
                    player1Win++;
                    TextView Xwin = findViewById(R.id.oWins_view);
                    String play1Win = Integer.toString(player1Win);
                    Xwin.setText(play1Win);
                    counter++;

                }


            } else if (win == 2) {
                builder.setMessage(player2Name + " wins!").setTitle("Game over");
                if (checkEndOfGame == 0) {
                    player2Win++;
                    TextView Xwin = findViewById(R.id.xWins_view);
                    String play2Win = Integer.toString(player2Win);
                    Xwin.setText(play2Win);

                    counter++;
                }player2Win++;

            }
            checkEndOfGame = 1;
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    newGame(new View(getApplicationContext()));

                }

            });
            AlertDialog dialog = builder.create();
            dialog.show();


        }
        if (isGameOver == 0) {
            for (i = 0; i < 3; i++) {
                for (j = 0; j < 3; j++) {
                    if (!threeBoard[i][j].getText().toString().equals("X") && !threeBoard[i][j].getText().toString().equals("O")) {
                        flag = 1;
                        break;

                    }
                }
            }
            if (flag == 0) {
                builder.setMessage("It's a draw!").setTitle("Game over");
                if (checkEndOfGame == 0) {
                    counter++;
                    draw++;
                    TextView playerDraw = findViewById(R.id.playDraw_view);
                    String play_draw = Integer.toString(draw);
                    playerDraw.setText(play_draw);
                }draw++;
                checkEndOfGame = 1;
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        newGame(new View(getApplicationContext()));

                    }

                });
                AlertDialog dialog = builder.create();
                dialog.show();


            }


        }


    }

    public void newGame(View view) {

        win = 0;
        isGameOver = 0;
        draw = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                threeBoard[i][j].setText(" ");
                threeBoard[i][j].setTextColor(Color.WHITE);
                threeBoardMatrix[i][j] = 0;
            }
        }

    }

    public void anyWinner() {
        for (int i = 0; i < 3; i++) {
            if (threeBoard[i][0].getText().toString().equals(threeBoard[i][1].getText().toString()) && threeBoard[i][0].getText().toString().equals(threeBoard[i][2].getText().toString())) {
                if (threeBoard[i][0].getText().toString().equals("X")) {
                    isGameOver = 1;
                    if (ChangePlaySate == 0)
                        win = 1;
                    else if (ChangePlaySate == 1)
                        win = 2;


                } else if (threeBoard[i][0].getText().toString().equals("O")) {
                    isGameOver = 1;
                    if (ChangePlaySate == 0)
                        win = 2;
                    else if (ChangePlaySate == 1)
                        win = 1;

                }
                if (!threeBoard[i][0].getText().toString().equals(" ")) {
                    threeBoard[i][0].setTextColor(Color.RED);
                    threeBoard[i][1].setTextColor(Color.RED);
                    threeBoard[i][2].setTextColor(Color.RED);

                }

            }
            if (threeBoard[0][i].getText().toString().equals(threeBoard[1][i].getText().toString()) && threeBoard[0][i].getText().toString().equals(threeBoard[2][i].getText().toString())) {
                if (threeBoard[0][i].getText().toString().equals("X")) {
                    isGameOver = 1;
                    if (ChangePlaySate == 0)
                        win = 1;
                    else if (ChangePlaySate == 1)
                        win = 2;


                } else if (threeBoard[0][i].getText().toString().equals("O")) {
                    isGameOver = 1;
                    if (ChangePlaySate == 0)
                        win = 2;
                    else if (ChangePlaySate == 1)
                        win = 1;

                }
                if (!threeBoard[0][i].getText().toString().equals(" ")) {
                    threeBoard[0][i].setTextColor(Color.RED);
                    threeBoard[1][i].setTextColor(Color.RED);
                    threeBoard[2][i].setTextColor(Color.RED);
                }

            }


        }
        if (threeBoard[0][0].getText().toString().equals(threeBoard[1][1].getText().toString()) && threeBoard[0][0].getText().toString().equals(threeBoard[2][2].getText().toString())) {
            if (threeBoard[0][0].getText().toString().equals("X")) {
                isGameOver = 1;
                if (ChangePlaySate == 0)
                    win = 1;
                else if (ChangePlaySate == 1)
                    win = 2;


            } else if (threeBoard[0][0].getText().toString().equals("O")) {
                isGameOver = 1;
                if (ChangePlaySate == 0)
                    win = 2;
                else if (ChangePlaySate == 1)
                    win = 1;

            }
            if (!threeBoard[0][0].getText().toString().equals(" ")) {
                threeBoard[0][0].setTextColor(Color.RED);
                threeBoard[1][1].setTextColor(Color.RED);
                threeBoard[2][2].setTextColor(Color.RED);
            }


        }
        if (threeBoard[0][2].getText().toString().equals(threeBoard[1][1].getText().toString()) && threeBoard[0][2].getText().toString().equals(threeBoard[2][0].getText().toString())) {
            if (threeBoard[0][2].getText().toString().equals("X")) {
                isGameOver = 1;
                if (ChangePlaySate == 0)
                    win = 1;
                else if (ChangePlaySate == 1)
                    win = 2;


            } else if (threeBoard[0][2].getText().toString().equals("O")) {
                isGameOver = 1;
                if (ChangePlaySate == 0)
                    win = 2;
                else if (ChangePlaySate == 1)
                    win = 1;

            }
            if (!threeBoard[2][0].getText().toString().equals(" ")) {
                threeBoard[2][0].setTextColor(Color.RED);
                threeBoard[1][1].setTextColor(Color.RED);
                threeBoard[0][2].setTextColor(Color.RED);
            }


        }
    }


    // This button takes the user back to the Home page
    public void homeButton(View view) {
        Intent homeIntent = new Intent(this, MainActivity.class);
        if (homeIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(homeIntent);
            finish();
        }
    }

    // This button resets the game by taking the user back to selecting the type of game.
    public void resetGame(View view) {
        Intent homeIntent = new Intent(this, GameTypePlayer2Activity.class);
        if (homeIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(homeIntent);
            finish();
        }
    }
}