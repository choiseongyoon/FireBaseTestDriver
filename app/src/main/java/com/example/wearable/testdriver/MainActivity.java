package com.example.wearable.testdriver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;



/*
*
* Bluetooth Indoor Position 어플리케이션을 통해 추정해서 Firebase 에 저장된 좌표를 가져옴
*
* */
public class MainActivity extends AppCompatActivity {

    long Time = System.currentTimeMillis();
    String day_s ;
    SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        Time = System.currentTimeMillis();
        day_s = dayTime.format(new Date(Time));

        final ImageView iv_position;
        ImageView iv_candy1,iv_candy3,iv_beetroot1,iv_beetroot2,iv_beetroot3,iv_lemon1,iv_lemon2,iv_lemon3;

        int x_initial_postion = 400;
        int y_initial_postion = 100;
        double ratio =2;


        int r_candy1[]= {0, 0};
        int r_candy3[]= {200, 0};

        int r_lemon1[]= {0, 200};
        int r_lemon2[]= {200, 200};
        int r_lemon3[]= {0, 400};

        int r_beetroot1[]= {200, 400};
        int r_beetroot2[]= {0, 600};
        int r_beetroot3[]= {200, 600};


        iv_lemon1=(ImageView)findViewById(R.id.lemon1);
        iv_lemon2=(ImageView)findViewById(R.id.lemon2);
        iv_lemon3=(ImageView)findViewById(R.id.lemon3);

        iv_candy1=(ImageView)findViewById(R.id.candy1);
        iv_candy3=(ImageView)findViewById(R.id.candy3);

        iv_beetroot1=(ImageView)findViewById(R.id.beetroot1);
        iv_beetroot2=(ImageView)findViewById(R.id.beetroot2);
        iv_beetroot3=(ImageView)findViewById(R.id.beetroot3);

        iv_beetroot1.setX((float) (ratio*r_beetroot1[0]+x_initial_postion));
        iv_beetroot1.setY((float) (ratio*r_beetroot1[1]+y_initial_postion));
        iv_beetroot2.setX((float) (ratio*r_beetroot2[0]+x_initial_postion));
        iv_beetroot2.setY((float) (ratio*r_beetroot2[1]+y_initial_postion));
        iv_beetroot3.setX((float) (ratio*r_beetroot3[0]+x_initial_postion));
        iv_beetroot3.setY((float) (ratio*r_beetroot3[1]+y_initial_postion));

        iv_candy1.setX((float) (ratio*r_candy1[0]+x_initial_postion));
        iv_candy1.setY((float) (ratio*r_candy1[1]+y_initial_postion));
        iv_candy3.setX((float) (ratio*r_candy3[0]+x_initial_postion));
        iv_candy3.setY((float) (ratio*r_candy3[1]+y_initial_postion));

        iv_lemon1.setX((float) (ratio*r_lemon1[0]+x_initial_postion));
        iv_lemon1.setY((float) (ratio*r_lemon1[1]+y_initial_postion));
        iv_lemon2.setX((float) (ratio*r_lemon2[0]+x_initial_postion));
        iv_lemon2.setY((float) (ratio*r_lemon2[1]+y_initial_postion));
        iv_lemon3.setX((float) (ratio*r_lemon3[0]+x_initial_postion));
        iv_lemon3.setY((float) (ratio*r_lemon3[1]+y_initial_postion));

        iv_position= (ImageView) findViewById(R.id.imageView);


        final TextView x = (TextView) findViewById(R.id.x);
        final TextView y = (TextView) findViewById(R.id.y);

        String url = "https://bluetooth-3e589.firebaseio.com/users/user2/"+day_s;
        Firebase ref = new Firebase(url);




        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String x_s = String.valueOf( snapshot.child("last_x").getValue());
                String y_s =String.valueOf( snapshot.child("last_y").getValue());

                float x_f = Float.parseFloat(x_s);
                float y_f = Float.parseFloat(y_s);

                x.setText(x_s);
                y.setText(y_s);

                iv_position.setX(x_f);
                iv_position.setY(y_f);
                iv_position.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });

    }

}
