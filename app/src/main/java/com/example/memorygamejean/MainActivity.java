package com.example.memorygamejean;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

public class MainActivity extends ActionBarActivity {



    public int ncol = 4;
    public int nrow = 3;
    ToggleButton button1;
    ToggleButton button2;
    ToggleButton button3;
    ToggleButton button4;
    ToggleButton button5;
    ToggleButton button6;
    Button buttonClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

         button1 = (ToggleButton) findViewById(R.id.toggleButton1);
         button2 = (ToggleButton) findViewById(R.id.toggleButton2);
         button3 = (ToggleButton) findViewById(R.id.toggleButton3);
         button4 = (ToggleButton) findViewById(R.id.toggleButton4);
         button5 = (ToggleButton) findViewById(R.id.toggleButton5);
         button6 = (ToggleButton) findViewById(R.id.toggleButton6);

        button1.setChecked(false);
        button2.setChecked(false);
        button3.setChecked(false);
        button4.setChecked(false);
        button5.setChecked(false);
        button6.setChecked(false);
        buttonClick = (Button) findViewById(R.id.buttonChoose);
        buttonClick.setOnClickListener(btnListener);

    }
    private View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //String s = "button " + Game.NCOL + "x" + Game.NROW + " is pressed";
            boolean isclicked = ((Button) view).isPressed();
            if( isclicked) {
               //Toast.makeText(getBaseContext(), s, Toast.LENGTH_LONG).show();
                Intent i = new Intent(getBaseContext(), MainActivity2Activity.class);
                startActivity(i);
            }
        }
    };

    public void onToggle(View view) {

        if (button1.isPressed()) {
            if (button1.isChecked()) {
                button1.setChecked(true);
                button2.setChecked(false);
                button3.setChecked(false);
                button4.setChecked(false);
                button5.setChecked(false);
                button6.setChecked(false);
                ncol =4;
                nrow =3;

            }
        }
        if (button2.isPressed()) {
            if (button2.isChecked()) {
                button2.setChecked(true);
                button1.setChecked(false);
                button3.setChecked(false);
                button4.setChecked(false);
                button5.setChecked(false);
                button6.setChecked(false);
                ncol =4;
                nrow =4;
            }
        }
        if (button3.isPressed()) {
            if (button3.isChecked()) {
                button3.setChecked(true);
                button1.setChecked(false);
                button2.setChecked(false);
                button4.setChecked(false);
                button5.setChecked(false);
                button6.setChecked(false);
                ncol =4;
                nrow =5;
            }
        }
        if (button4.isPressed()) {
            if (button4.isChecked()) {
                button4.setChecked(true);
                button1.setChecked(false);
                button2.setChecked(false);
                button3.setChecked(false);
                button5.setChecked(false);
                button6.setChecked(false);
                ncol =4;
                nrow =6;
            }
        }
        if (button5.isPressed()) {
            if (button5.isChecked()) {
                button5.setChecked(true);
                button1.setChecked(false);
                button2.setChecked(false);
                button3.setChecked(false);
                button4.setChecked(false);
                button6.setChecked(false);
                ncol =6;
                nrow =5;
            }
        }
        if (button6.isPressed()) {
            if (button6.isChecked()) {
                button6.setChecked(true);
                button1.setChecked(false);
                button2.setChecked(false);
                button3.setChecked(false);
                button4.setChecked(false);
                button5.setChecked(false);
                ncol =6;
                nrow =6;
            }
        }
        Game.NCOL = ncol;
        Game.NROW = nrow;
    }


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
