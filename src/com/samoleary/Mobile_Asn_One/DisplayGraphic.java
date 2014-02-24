package com.samoleary.Mobile_Asn_One;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Author: Sam O'Leary
 * Email: somhairle.olaoire@mycit.ie
 * Created: 10/10/13
 * Revision: 1
 * Revision History:
 *      1: 1/11/13
 *
 * Description:
 *      ScreenTwo is displayed when the user clicks on the first button on the HomeScreen activity.
 *      This activity takes the username and speed that the user entered on the HomeScreen and displays
 *      them in this new activity. ScreenTwo also takes the speed and performs a calculation on it to
 *      determine what the equivalent distance of free fall for that speed is.
 *      There is also a button displayed that will return the user to the main activity.
 */

public class DisplayGraphic extends Activity {

    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.graphicscreen);

        init();
    }

    /**
     * This initialize method creates an ImageView object corresponding to the ImageView defined in the graphicscreen.xml file.
     * This XML file defines what image is to be used and where it will be placed on the screen.
     * The image is stored in the 'res' folder.
     * The method then creates an Animation object using the animation settings defined in the animated.xml file.
     * This XML file is stored within the 'anim' folder inside 'res' and contains the settings for the behaviour of the image to be animated.
     * The method then creates a button to take the user back to the HomeScreen.
     */

    private void init() {
        ImageView appInfoImg = (ImageView) this.findViewById(R.id.graphicview);
        Animation animSettings = AnimationUtils.loadAnimation(this, R.anim.animated);

        appInfoImg.setVisibility(View.VISIBLE);     //make sure that the user can see the image
        appInfoImg.startAnimation(animSettings);    //start the animation according to the settings defined

        Button backBtn = (Button) this.findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                finish();
            }
        });
    }
}
