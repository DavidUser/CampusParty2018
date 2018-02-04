package com.ford.sa.campuspartyexample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class CarHealth extends AppCompatActivity {

    private static long defautlPressure = 30, tolerance = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_health);
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

        TextView leftFront = (TextView) findViewById(R.id.textLeftFrontTire);
        TextView rightFront = (TextView) findViewById(R.id.textRightFrontTire);
        TextView leftBack = (TextView) findViewById(R.id.textLeftBackTire);
        TextView rightBack = (TextView) findViewById(R.id.textRightBackTire);

        CarData car = CarData.getInstance();

        leftFront.setText(getTireState(car.getLeftFront()));
        leftBack.setText(getTireState(car.getLeftRear()));
        rightFront.setText(getTireState(car.getRightFront()));
        rightBack.setText(getTireState(car.getRightRear()));
    }

    /***
     * Apply classification to measure health of Tire
     * This implementation use a Linear classifier with a threshold and tolerance
     * @param pressure
     * @return classification
     */
    private String getTireState(String pressure) {
        try {
            long value = Long.parseLong(pressure);
            long deviation = Math.abs(defautlPressure - value);
            if (deviation <= tolerance)
                return "Good";
            else
                return "Bad";
        } catch (Exception ex) {
            return pressure;
        }
    }

}
