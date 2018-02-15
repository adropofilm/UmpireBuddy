package com.example.umpirebuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Variables to
    private int total_balls = 0;
    private int total_strikes = 0;
    private int total_outs = 0;

    TextView strike_value;
    Button strike_button;
    TextView ball_value;
    Button ball_button;
    TextView outs_value;

    // Function called to increment values on click of buttons:
    private void update_values() {
        strike_value.setText("Strikes: " + Integer.toString(total_strikes));
        ball_value.setText("Balls: " + Integer.toString(total_balls));
        outs_value.setText("Total Outs: " +Integer.toString(total_outs));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link java variables to XML IDs:
        strike_value = findViewById(R.id.strikeValue);
        strike_button = findViewById(R.id.strike_button);

        ball_value = findViewById(R.id.ballValue);
        ball_button = findViewById(R.id.ball_button);

        outs_value = findViewById(R.id.outsValue);

        // When strike button is clicked:
        strike_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total_strikes += 1;
                if (total_strikes == 3) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("OUT!");
                    builder.setCancelable(true);
                    builder.show();
                    total_strikes = 0;
                    total_outs += 1;
                }
                update_values();
            }
        });

        ball_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total_balls += 1;
                if (total_balls == 4) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("WALK!");
                    builder.setCancelable(true);
                    builder.show();
                    total_balls = 0;
                }
                update_values();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    // When menu items are clicked:
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reset:
                total_strikes = 0;
                total_outs = 0;
                total_balls = 0;
                update_values();
                return true;
            case R.id.about:
                Intent intent_two = new Intent(this, About.class);
                this.startActivity(intent_two);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}