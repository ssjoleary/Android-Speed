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
 *      ScreenThree is displayed when the user clicks on the second button on the HomeScreen activity.
 *      This activity takes the username and speed that the user entered on the HomeScreen and displays
 *      them in this new activity. ScreenThree also takes the speed and performs a calculation on it to
 *      determine what the equivalent distance of free fall for that speed is.
 *      The users name is also reversed and displayed at the bottom of the screen.
 *      When the user presses the 'Back' button to return to the home screen the 'reversed username'
 *      is passed back to the HomeScreen activity where it is also displayed for the user to see.
 */

public class ScreenThree extends Activity {
    private TextView scrThreeResult;
    private String usernameReversed;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screenthree);

        init();
    }

    /**
     * This initialize method creates the TextViews that will be needed to display the users name and the speed
     * they entered on the HomeScreen. These values are passed in through a Bundle. The speed value is then passed
     * to another method calculateFreeFall and the username is reversed using the StringBuilder class.
     * Once the distance of free fall is calculated and the username is reversed they are then displayed on
     * the screen for the user to see.
     * Once the user has finished on this activity and presses the 'Back' button the reversed username is put into
     * a Bundle and is passed back to the HomeScreen activity for the user to see.
     */

    private void init() {
        double speedIn;
        String username;
        TextView scrThreeUsername;
        TextView scrThreeUsernameReverse;

        scrThreeUsername = (TextView) findViewById(R.id.scrThreeName);
        scrThreeUsernameReverse = (TextView) findViewById(R.id.scr_three_usernamereverse);
        scrThreeResult = (TextView) findViewById(R.id.scrThreeText);

        Intent i = getIntent();
        username = i.getStringExtra("username");
        speedIn = i.getDoubleExtra("speedIn", 50);

        calculateFreefall(speedIn);

        scrThreeUsername.setText(username);

        usernameReversed = new  StringBuilder(username).reverse().toString();
        scrThreeUsernameReverse.setText(usernameReversed);

        Button finishButton = (Button) findViewById(R.id.scrThreeButton);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();
                i.putExtra("usernamereverse", usernameReversed);
                setResult(RESULT_OK, i);
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
        scrThreeResult.setText(resultText); //the result is then formatted and displayed

    }
}
