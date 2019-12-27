//program in java for a game called Tic Tac Toe

//package import statements
package com.example.tictactoe;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


//start of  main activity
//inherits from app compact activity
//using the interface on click listner of  view interface

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //defining the variables to hold the references of layout

    private Button[][] btn = new Button[3][3];      //as the layout is in linear and parallel we are using 2d array of button variables
    private TextView tvPlayer1;
    private TextView tvPlayer2;
    private Button reset;

    //defining variables to use in program for processing purposes
    private boolean player1Turn = true;
    private int roundCount;
    private int player1Points;
    private int player2Points;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvPlayer1 = findViewById(R.id.tvPlayer1);
        tvPlayer2 = findViewById(R.id.tvPlayer2);
        reset = findViewById(R.id.btnReset);

//loop for assigning the variables of button type with layout refrences
        for(int i = 0;i<3;i++)
        {       for(int j = 0;j<3;j++){
                String button = "btn_" + i + j;
                int btnId = getResources().getIdentifier(button,"id",getPackageName());
                btn[i][j] = findViewById(btnId);
                btn[i][j].setOnClickListener(this);
            }
        }

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            resetGame();
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals(""))
            return;

        if(player1Turn)
        { ((Button) v).setText("X");
            ((Button) v).setTextColor(Color.rgb(255,0,0));}
        else
        {((Button) v).setText("O");
        ((Button) v).setTextColor(  Color.rgb(0,255,0));}

        roundCount++;

        if (checkWin()) {
            if (player1Turn) {
                player1Win();
            } else {
                player2Win();
            }
        } else if (roundCount == 9) {
            draw();
        } else {
            player1Turn = !player1Turn;
        }
    }


    private boolean checkWin(){
        String [][] chk = new String[3][3];
        for(int i = 0;i < 3; i++){
            for(int j= 0; j < 3; j++){
                chk [i][j] = btn[i][j].getText().toString();

            }
        }

        for (int i = 0;i<3;i++){
            if (chk[i][0].equals(chk[i][1]) && chk[i][0].equals(chk[i][2]) && !chk[i][2].equals("")){
                return true;}
        }
        for (int i = 0;i<3;i++){
            if (chk[0][i].equals(chk[1][i]) && chk[0][i].equals(chk[2][i]) && !chk[2][i].equals("")){
                return true;}
        }
        if (chk[0][0].equals(chk[1][1]) && chk[0][0].equals(chk[2][2]) && !chk[2][2].equals(""))
            return true;
        if (chk[0][2].equals(chk[1][1]) && chk[0][2].equals(chk[2][0]) && !chk[2][0].equals(""))
            return true;

        return false;
    }

    private void player1Win(){
        player1Points++;
        Toast.makeText(this,"Player 1 Wins !",Toast.LENGTH_SHORT).show();
        updatePoints();
        resetBoard();
    }
    private void player2Win(){
        player2Points++;
        Toast.makeText(this,"Player 2 Wins !",Toast.LENGTH_SHORT).show();
        updatePoints();
        resetBoard();
    }
    private void draw(){
        Toast.makeText(this,"Draw",Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePoints(){
        tvPlayer1.setText("Player 1: "+player1Points);
        tvPlayer2.setText("Player 2: " + player2Points);
    }
    private void resetBoard(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                {
                    btn[i][j].setText("");

                }
            }
        }
        roundCount = 0;
        player1Turn = true;
    }
    private void resetGame(){
        player2Points = 0;
        player1Points = 0;
        updatePoints();
        resetBoard();
    }
}
