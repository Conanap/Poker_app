package com.albicraneproductions.testpoker2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class PublicCard extends FragmentActivity implements View.OnClickListener{
    private ImageButton[] ib = new ImageButton[13];
    int[] own_c, own_c_id, p_c;
    private Button next, back;
    private ImageView s1, s2, p1, p2, p3, p4, p5;
    private Drawable d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_card);
        Bundle extra = getIntent().getExtras();
        own_c = extra.getIntArray("own_c");
        own_c_id = extra.getIntArray("own_c_id");

        p_c = new int[5];
        for(int i = 0; i < p_c.length; i++){
            p_c[i] = -1;
        }

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
        for (int i = 0; i < 13; i++){
            ib[i].setOnClickListener(this);
        }

        //back and next buttons
        back = (Button) findViewById(R.id.back_but);
        next = (Button) findViewById(R.id.next_but);
        //set on click listener
        back.setOnClickListener(this);
        next.setOnClickListener(this);
        //make next invisible to start with
        back.setVisibility(View.GONE);
        next.setVisibility(View.GONE);

        s1 = (ImageView) findViewById(R.id.s1);
        s2 = (ImageView) findViewById(R.id.s2);
        d = ib[own_c[0]].getBackground();
        s1.setBackgroundDrawable(d);
        d = ib[own_c[1]].getBackground();
        s2.setBackgroundDrawable(d);



        for(int i = 0; i < ib.length; i++){
            if(ib[i].getId() == own_c_id[0] || ib[i].getId() == own_c_id[1]){
                ib[i].setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onClick(View v){
        if(v.getId() == back.getId()){//pressed reset
            for(int i = 0; i < ib.length; i++) {
                if (ib[i].getId() != own_c_id[0] || ib[i].getId() != own_c_id[1]) {
                    ib[i].setVisibility(View.VISIBLE);
                }
            }
            for(int g = 0; g > p_c.length; g++){
                p_c[g] = -1;
            }
        }else if(v.getId() == next.getId()){//pressed next
            Intent intent = new Intent(getBaseContext(), Main.class);
            finish();
            startActivity(intent);
            overridePendingTransition(0,0);
        }else{//pressed a card
            for(int i = 0; i < ib.length; i++){
                if(ib[i].getId() == v.getId()){
                    for(int g = 0; g < p_c.length; g++){
                        if(p_c[g] == -1){
                            p_c[g] = i;
                            ib[i].setVisibility(View.GONE);
                            break;
                        }
                    }
                }
            }
        }
        if(p_c[0] != -1){
            back.setVisibility(View.VISIBLE);
        }else{
            back.setVisibility(View.GONE);
        }
        if(p_c[p_c.length-1] != -1){
            next.setVisibility(View.VISIBLE);
        }else{
            next.setVisibility(View.GONE);
        }
    }

}
