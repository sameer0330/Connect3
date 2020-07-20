package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    int  yellow=0;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][] winningPositons={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive=true;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void playAgain(View view)
    {
        Button playAgain=(Button)findViewById(R.id.playAgainButton);
        TextView ed=(TextView) findViewById(R.id.displayWinner);
        playAgain.setVisibility(View.INVISIBLE);
        ed.setVisibility(View.INVISIBLE);
       GridLayout gridLayout=  findViewById(R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++)
        {
            System.out.println(i);
            ImageView counter=(ImageView)gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
          yellow=0;
        for(int i=0;i<gameState.length;i++)
         gameState[i]=2;
         gameActive=true;
         count=0;
    }
    public void dropIn(View view)
    {

        int currentSlot=0;
        count++;
        ImageView counter=(ImageView) view;
        currentSlot=Integer.parseInt(counter.getTag().toString());
        if(gameState[currentSlot-1]==2&&gameActive==true) {
            counter.setTranslationY(-500);
            gameState[currentSlot - 1] = yellow;
            if (yellow == 0) {
                counter.setImageResource(R.drawable.yellowchip);
                yellow = 1;
            } else {
                counter.setImageResource(R.drawable.redchip);
                yellow = 0;
            }
            counter.animate().translationYBy(500).rotationBy(1800).setDuration(200);
            String winner = "";
            for (int[] pos : winningPositons) {
                if (gameState[pos[0]] == gameState[pos[1]] && gameState[pos[1]] == gameState[pos[2]] && gameState[pos[0]] != 2) {
                    gameActive=false;
                    if (gameState[pos[0]] == 0)
                    {
                        winner = " Yellow Wins!";
                       //Toast.makeText(this, winner, Toast.LENGTH_SHORT).show();
                    }
                    else {
                        winner = "Red Wins!";
                      // Toast.makeText(this, winner, Toast.LENGTH_SHORT).show();
                    }
                    // winner="We have a winner";
                    Button playAgain=(Button)findViewById(R.id.playAgainButton);
                    TextView ed=(TextView) findViewById(R.id.displayWinner);
                    playAgain.setVisibility(View.VISIBLE);
                    ed.setVisibility(View.VISIBLE);
                    ed.setText(winner);
                }
                else if(count==9)
                {
                    gameActive=false;
                    winner="It is a Draw!";
                    Button playAgain=(Button)findViewById(R.id.playAgainButton);
                    TextView ed=(TextView) findViewById(R.id.displayWinner);
                    playAgain.setVisibility(View.VISIBLE);
                    ed.setVisibility(View.VISIBLE);
                    ed.setText(winner);
                }
            }

        }
    }

}
