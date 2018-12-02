package com.example.diceout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView rollResult;

    Button rollButton;

    Random rand;

    int die1;
    int die2;
    int die3;

    ArrayList<Integer> dice;

    ArrayList<ImageView> dieImageViews;

    TextView scoreText;


    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        score=0;

        rollResult=findViewById(R.id.rollResult);
        rollButton=findViewById(R.id.rollButton);

        rand= new Random();

        dice= new ArrayList<Integer>();

        ImageView die1Image=findViewById(R.id.die1Image);
        ImageView die2Image=findViewById(R.id.die2Image);
        ImageView die3Image=findViewById(R.id.die3Image);

        dieImageViews=new ArrayList<ImageView>();
        dieImageViews.add(die1Image);
        dieImageViews.add(die2Image);
        dieImageViews.add(die3Image);

        scoreText=findViewById(R.id.score);

        Toast.makeText(getApplicationContext(),"Welcome to DiceOut",Toast.LENGTH_SHORT).show();
    }


    public void rollDice(View v){
        rollResult.setText("Clicked!!!");

        die1 = rand.nextInt(6)+1;
        die2 = rand.nextInt(6)+1;
        die3 = rand.nextInt(6)+1;

        dice.clear();
        dice.add(die1);
        dice.add(die2);
        dice.add(die3);

        String msg;

        if (die1==die2 && die1==die3)
        {
            int scoreDelta=die1*100;
            msg="You rolled triple "+die1+"! you scored "+ scoreDelta +" points";
            score+=scoreDelta;
        }
        else if (die1==die2 ||die2==die3 ||die1==die3 )
        {
            msg="You rolled double .You scored 50 points";
            score+=50;

        }
        else{
            msg="Try Again";
        }


        for (int dieOfSet=0;dieOfSet<3; dieOfSet++)
        {
            String imageName="die_"+dice.get(dieOfSet)+".png";

            try{
                InputStream stream = getAssets().open(imageName);
                Drawable d=Drawable.createFromStream(stream,null);
                dieImageViews.get(dieOfSet).setImageDrawable(d);
                } catch (IOException e){
                e.printStackTrace();
                }


            }
        rollResult.setText(msg);
        scoreText.setText("Score : "+score);

        }

        //Toast.makeText(getApplicationContext(),randomValue,Toast.LENGTH_SHORT).show();


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
