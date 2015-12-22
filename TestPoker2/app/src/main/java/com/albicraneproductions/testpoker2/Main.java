package com.albicraneproductions.testpoker2;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Main extends FragmentActivity implements View.OnClickListener{
    //declaring variables
    private ImageView s1, s2;
    private Button back, next;
    private ImageButton[] ib = new ImageButton[13];
    private int[] own_c, own_c_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getting buttons (cards) assigned
        ib[0] = (ImageButton) findViewById(R.id.c1);
        ib[1] = (ImageButton) findViewById(R.id.c2);
        ib[2] = (ImageButton) findViewById(R.id.c3);
        ib[3] = (ImageButton) findViewById(R.id.c4);
        ib[4] = (ImageButton) findViewById(R.id.c5);
        ib[5] = (ImageButton) findViewById(R.id.c6);
        ib[6] = (ImageButton) findViewById(R.id.c7);
        ib[7] = (ImageButton) findViewById(R.id.c8);
        ib[8] = (ImageButton) findViewById(R.id.c9);
        ib[9] = (ImageButton) findViewById(R.id.c10);
        ib[10] = (ImageButton) findViewById(R.id.cj);
        ib[11] = (ImageButton) findViewById(R.id.cq);
        ib[12] = (ImageButton) findViewById(R.id.ck);

        //set the card's listeners
        for (int i = 0; i < ib.length; i++){
            ib[i].setOnClickListener(this);
        }

        //back and next buttons
        back = (Button) findViewById(R.id.back_but);
        next = (Button) findViewById(R.id.next_but);
        //set on click listener
        back.setOnClickListener(this);
        next.setOnClickListener(this);
        //make it invisible to start with
        back.setVisibility(View.GONE);
        next.setVisibility(View.GONE);

        //we got no cards yet
        own_c = new int[2];
        own_c[0] = -1;
        own_c[1] = -1;
        own_c_id = new int[2];

        //selected cards on screen 1
        s1 = (ImageView) findViewById(R.id.s1);
        s2 = (ImageView) findViewById(R.id.s2);
        //set to invisible
        s1.setVisibility(View.GONE);
        s2.setVisibility(View.VISIBLE);
    }
    @Override
    public void onClick(View v){
        if(v.getId() == back.getId()) {
                own_c[0] = -1;
                own_c[1] = -1;
                s1.setVisibility(View.GONE);
                s2.setVisibility(View.GONE);
                for(int i = 0; i < ib.length; i ++) {
                    ib[i].setVisibility(View.VISIBLE);
                }
        }else if (v.getId() == next.getId()) {
            //moving to next activity
            Intent intent = new Intent(getBaseContext(), PublicCard.class);
            //pass data
            intent.putExtra("own_c", own_c);
            intent.putExtra("own_c_id", own_c_id);
            //start activity
            startActivity(intent);
            //remove transition
            overridePendingTransition(0,0);
        } else {
            //things to do on screen 1
                //if they press a card
                int i = 0;//loop var
                do {
                    if (v.getId() == ib[i].getId()) {
                        Drawable d = ib[i].getBackground();
                        if (own_c[1] == -1) {
                            own_c[1] = i;
                            own_c_id[1] = v.getId();
                            s2.setBackgroundDrawable(d);
                            s2.setVisibility(View.VISIBLE);
                            ib[i].setVisibility(View.GONE);
                        } else if (own_c[0] == -1) {
                            own_c[0] = i;
                            own_c_id[0] = v.getId();
                            s1.setBackgroundDrawable(d);
                            s1.setVisibility(View.VISIBLE);
                            ib[i].setVisibility(View.GONE);
                        }
                    }
                    i++;
                } while (i < ib.length);
                if (own_c[0] != own_c[1]) {
                    back.setVisibility(View.VISIBLE);
                } else {
                    back.setVisibility(View.GONE);
                }
        }
        if (own_c[0] != -1 && own_c[1] != -1) {
            next.setVisibility(View.VISIBLE);
        } else {
            next.setVisibility(View.GONE);
        }
    }
}

