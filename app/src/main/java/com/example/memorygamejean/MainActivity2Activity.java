package com.example.memorygamejean;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity2Activity extends ActionBarActivity {


    int[] images = {R.drawable.pic01, R.drawable.pic02, R.drawable.pic03, R.drawable.pic04,
            R.drawable.pic05, R.drawable.pic06, R.drawable.pic07, R.drawable.pic08,
            R.drawable.pic09, R.drawable.pic10, R.drawable.pic11, R.drawable.pic12,
            R.drawable.pic13, R.drawable.pic14, R.drawable.pic15, R.drawable.pic16,
            R.drawable.pic17, R.drawable.pic18};

    int currentCard;
    ImageView image[];
    Card cards[];
    public int[] num;
    public int counter = 0;
    public int matchCount = 0;
    public static int cardA = -1;
    public static int cardB = -1;
    public boolean isMatch = false;
    public int Number = 36;
    public double t1 = 0;
    public double t2 = 0;
    public int ncol ;
    public int nrow ;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    static final String PREFERENCES_NAME = "MyPreferences";
    public String prefName = "game4x3";
    public int maxScore = 0;
    public int Grade = 0;
    public double Points = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);

        ncol = Game.NCOL;
        nrow = Game.NROW;

        playTheGame();

    }

    public void playTheGame(){

        if ( ncol == 6 ) {
            setContentView(R.layout.activity_6x6);
        }

        if ( ncol == 4 ){
            setContentView(R.layout.activity_4x6);
        }

        Number = ncol * nrow ;

        switch (Number){
            case 12:
                Grade = 100;
                break;
            case 16:
                Grade = 200;
                break;
            case 20:
                Grade = 300;
                break;
            case 24:
                Grade = 400;
                break;
            case 30:
                Grade = 500;
                break;
            case 36:
                Grade = 600;
                break;
            default:
                Grade = 100;
        }

        preferences = getApplicationContext().getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
        editor = preferences.edit();
        prefName = "game" + ncol + "x" + nrow;

        maxScore = preferences.getInt(prefName, 0);

        //Toast.makeText(this,prefName + " " + maxScore,Toast.LENGTH_LONG).show();

        image = new ImageView[Number];

        String cardid = "";
        for (int i = 0; i < Number; i++) {
            int num = i + 1;
            if (num < 10) {
                cardid = "card0" + num;
            } else {
                cardid = "card" + num;
            }
            int resID = getResources().getIdentifier(cardid, "id", "com.example.memorygamejean");
            image[i] = (ImageView) findViewById(resID);
        }

        num = new int[Number];
        num = generateCardPosition();

        cards = new Card[Number];
        for (int i = 0; i < Number; i++) {
            cards[i] = new Card(R.drawable.rectblue1);
        }
        int count = 0;
        for (int i = 0; i < Number; i += 2) {
            cards[num[i]].setImage(images[count]);
            cards[num[i + 1]].setImage(images[count]);
            count++;
        }


        for (int i = 0; i < nrow; i++) {
            for (int j = 0; j < ncol; j++) {
                currentCard = i * ncol + j;
                image[currentCard].setImageResource(R.drawable.rectblue1);
                image[currentCard].setTag(currentCard + 1);
            }
        }
    }



    public void onClickCard(View v) {
        int cardID = (int) v.getTag();
        counter++;
        if (counter > 1) {
            if (counter % 2 == 0) {   //cardB
                if (cardID == cardA) {
                    counter--;
                    return;
                }
                cardB = cardID;

                if (cards[cardB - 1].getImage() == cards[cardA - 1].getImage()) {
                    isMatch = true;
                    image[cardA - 1].setClickable(false);
                    image[cardB - 1].setClickable(false);
                    matchCount++;
                } else {
                    isMatch = false;
                }
            } else { //cardA
                if (cardA != -1 && !isMatch) {
                    image[cardA - 1].setImageResource(R.drawable.rectblue1);
                    image[cardB - 1].setImageResource(R.drawable.rectblue1);
                }
                cardA = cardID;
            }
        } else {
            cardA = cardID;
            t1 = SystemClock.uptimeMillis();
        }
        image[cardID - 1].setImageResource(cards[cardID - 1].getImage());
        if (matchCount == Number/2) {

            t2 = SystemClock.uptimeMillis();
            double delta = (t2 -t1) / 1000;

            int minute = (int)delta/60;
            int secunde = (int)(delta - (minute * 60));

            //Points = ( (double)(Number) / (double)counter ) * ((double)100 / delta) ;
            Points = ( (double)(Number * Grade) / (double)counter ) * ((double)100 / delta) ;


            showAlert( minute, secunde);

            if ( (int)Points > maxScore){
                maxScore = (int)Points;
                editor.putInt(prefName, maxScore);
                editor.apply();
                editor.commit();
            }


            counter = 0;
            matchCount = 0;
            cardA = -1;
            cardB = -1;
        }
    }

    public int[] generateCardPosition() {
        int numbers[];
        numbers = new int[Number];
        shuffleArray(numbers, Number);
        return numbers;
    }

    public void shuffleArray(int[] numbers, int num) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(i);
        }
        Collections.shuffle(list);

        for (int i = 0; i < num; i++) {
            numbers[i] = list.get(i);
        }
    }

    public void showAlert(int minute, int secunde) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        // Setting Dialog Title
        alertDialog.setTitle("END of the GAME!!!");
        String smin = "";
        if(minute < 10)
            smin += smin + "0" + minute;
        else
            smin += smin + minute;
        String ssec = "";
        if ( secunde < 10)
            ssec += ssec + "0" + secunde;
        else
            ssec += ssec + secunde;
        // Setting Dialog Message
        String sss = "is: ";
        if ( (int)Points > maxScore){
            sss = "was: ";
        }
        String ss = "Using: " + counter + " clicks!!!" + "\nElapsed time: " + smin + ":" + ssec +
                "\n You win " + (int)(Points) + "\n Your best score " + sss + maxScore;
        alertDialog.setMessage(ss);

        alertDialog.setPositiveButton("Play again this Game !", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //initGame();
                counter = 0;
                matchCount = 0;
                cardA = -1;
                cardB = -1;
                num = generateCardPosition();

                int count = 0;
                for (int i = 0; i < Number; i += 2) {
                    cards[num[i]].setImage(images[count]);
                    cards[num[i + 1]].setImage(images[count]);
                    count++;
                }
                for (int i = 0; i < Number; i++) {
                    image[i].setImageResource(R.drawable.rectblue1);
                    image[i].setClickable(true);
                }
            }
        });

        alertDialog.setNegativeButton("Finish", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // for (int i = 0; i < Number; i++) {
                //     image[i].setImageResource(R.drawable.rectred1);
                //     image[i].setClickable(false);
                // }
                dialog.cancel();
                finish();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
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
