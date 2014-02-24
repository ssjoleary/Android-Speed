package com.samoleary.Mobile_Asn_One;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * Author: Sam O'Leary
 * Email: somhairle.olaoire@mycit.ie
 * Created: 10/10/13
 * Revision: 4
 * Revision History:
 *      1: 10/10/13
 *      2: 17/10/13
 *      3: 24/10/13
 *      4: 1/11/13
 *
 * Description:
 *      ScreenTwo is displayed when the user clicks on the first button on the HomeScreen activity.
 *      This activity takes the username and speed that the user entered on the HomeScreen and displays
 *      them in this new activity. ScreenTwo also takes the speed and performs a calculation on it to
 *      determine what the equivalent distance of free fall for that speed is.
 *      There is also a button displayed that will return the user to the main activity.
 */

public class ScreenTwo extends Activity {
    private String username;
    private double speedIn;
    private TextView scrTwoTextView;
    private TextView scrTwoTextResult;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screentwo);

        init();
    }


    /**
     * This initialize method creates the TextViews that will be needed to display the users name and the speed
     * they entered on the HomeScreen. These values are passed in through a Bundle. The speed value is then passed
     * to another method calculateFreeFall.
     * Once the distance of free fall is calculated it is then displayed on the screen for the user to see.
     * Once the user has finished on this activity and presses the 'Back' button they are returned to the HomeScreen.
     */
    private void init() {

        scrTwoTextView = (TextView) findViewById(R.id.scrTwoName);
        scrTwoTextResult = (TextView) findViewById(R.id.scrTwoText);

        Intent i = getIntent();
        username = i.getStringExtra("username");
        speedIn = i.getDoubleExtra("speedIn", 50);

        calculateFreefall(speedIn);

        scrTwoTextView.setText(username);

        Button finishButton = (Button) findViewById(R.id.scrTwoButton);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * This method takes the value for the Speed the user entered on the HomeScreen and uses this value
     * to determine what the equivalent distance of free fall for that speed is.
     * Once determined it is displayed on the screen for the user to see.
     *
     * @param speedIn
     * This value is a Double. It is passed from the HomeScreen activity into this activity, ScreenThree,
     * and then it is passed into this method, calculateFreeFall.
     */

    private void calculateFreefall(double speedIn) {
        double result;
        double distance;
        double resultSquared;
        String resultText;
        double mileTokm;

        mileTokm = speedIn/.62137;  //this converts the speed from miles/hour to kilometres/hour

        result = mileTokm / 3.6;    //this converts the kilometres/hour to metres/second
        resultSquared = (result*result);
        /**
        the equation to determine the distance is
        (distance)^2 / 2(9.8)
        9.8 m/s^2 is the acceleration due to gravity
        */
        distance = resultSquared / 19.6;

        resultText = String.format(getResources().getString(R.string.result_text), speedIn, distance);
        scrTwoTextResult.setText(resultText); //the result is then formatted and displayed

    }
}
