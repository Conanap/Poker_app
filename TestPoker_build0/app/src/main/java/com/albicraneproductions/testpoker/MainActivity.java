package com.albicraneproductions.testpoker;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private ImageView resultView, shade, c1, c2;
    private ImageButton b1, b2, b3, b4, b5, b6, b7, b8;
    private Button back, next;
    private ImageButton[] ib = new ImageButton[8];
    private int[] own_c, own_c_id;
    private boolean c1_set, c2_set, screen1, screen2;
    private int card_s2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //button stuff
        //some buttons dne yet
        //b0 = (ImageButton) findViewById(R.id.s0);
        ib[0] = b1 = (ImageButton) findViewById(R.id.s1);
        ib[1] = b2 = (ImageButton) findViewById(R.id.s2);
        ib[2] = b3 = (ImageButton) findViewById(R.id.s3);
        ib[3] = b4 = (ImageButton) findViewById(R.id.s4);
        ib[4] = b5 = (ImageButton) findViewById(R.id.s5);
        ib[5] = b6 = (ImageButton) findViewById(R.id.s6);
        ib[6] = b7 = (ImageButton) findViewById(R.id.s7);
        ib[7] = b8 = (ImageButton) findViewById(R.id.s8);
        shade = (ImageView) findViewById(R.id.shade_card);

        back = (Button) findViewById(R.id.back_but);
        back.setVisibility(View.GONE);

        next = (Button) findViewById(R.id.next_but);
        next.setVisibility(View.GONE);

        for(int i = 0; i < 8; i++) {
            ib[i].setOnClickListener(this);
        }

        back.setOnClickListener(this);
        next.setOnClickListener(this);

        own_c = new int[2];
        own_c_id = new int[2];
        own_c[0] = -1;
        own_c[1] = -1;
        c1 = (ImageView) findViewById(R.id.c1);
        c2 = (ImageView) findViewById(R.id.c2);
        c1_set = false;
        c2_set = false;
        c1.setVisibility(View.GONE);
        c2.setVisibility(View.GONE);

        screen1 = true;
        screen2 = false;

        card_s2 = 0;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == back.getId()) {
            for (int i = 0; i < 8; i++) {
                if(screen1) {
                    ib[i].setVisibility(View.VISIBLE);
                }
                else if(!screen1 && !screen2 || !screen1 && screen2){
                    if(ib[i].getId() != own_c_id[0] && ib[i].getId() != own_c_id[1]){
                        ib[i].setVisibility(View.VISIBLE);
                    }
                }
            }
            if(screen1) {
                c2_set = false;
                c1_set = false;

                c1.setVisibility(View.GONE);
                c2.setVisibility(View.GONE);
                back.setVisibility(View.GONE);
            }
            shade.setVisibility(View.VISIBLE);
            next.setVisibility(View.GONE);
            if(!screen1 && !screen2){
                screen2 = true;
            }else if(!screen1 && screen2 && card_s2 == 0){
                screen1 = true;
                screen2 = false;
            }
            card_s2 = 0;
        } else if (v.getId() == next.getId()) {
            for (int i = 0; i < 8; i++) {
                if (!(ib[i].getId() == own_c_id[0]) && !(ib[i].getId() == own_c_id[1])) {
                    ib[i].setVisibility(View.VISIBLE);
                }
                shade.setVisibility(View.VISIBLE);
                next.setVisibility(View.GONE);
            }
            screen1 = false;
            screen2 = true;
        } else {
            if (screen1) {
                    for (int i = 0; i < 8; i++) {
                        if ((v.getId() == ib[i].getId())) {
                            Drawable d = ib[i].getBackground();
                            if (!c1_set) {
                                c1.setBackgroundDrawable(d);
                                c1_set = true;
                                own_c[0] = i;
                                own_c_id[0] = ib[i].getId();
                                c1.setVisibility(View.VISIBLE);
                            } else if (!c2_set) {
                                c2.setBackgroundDrawable(d);
                                c2_set = true;
                                own_c[1] = i;
                                own_c_id[1] = ib[i].getId();
                                c2.setVisibility(View.VISIBLE);
                                shade.setVisibility(View.GONE);
                                for (int e = 0; e < 8; e++) {
                                    ib[e].setVisibility(View.GONE);
                                }
                                next.setVisibility(View.VISIBLE);
                                break;
                            }
                            ib[i].setVisibility(View.GONE);
                        }
                    }
                    back.setVisibility(View.VISIBLE);
            } else if (screen2) {
                    for (int i = 0; i < 8; i++) {
                        if (v.getId() == ib[i].getId()) {
                            ib[i].setVisibility(View.GONE);
                            card_s2++;
                        }
                    }
                if (card_s2 == 3) {
                    screen2 = false;
                }
            }
        }
    }

}
