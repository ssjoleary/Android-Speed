package com.samoleary.Mobile_Asn_One;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Author: Sam O'Leary
 * Email: somhairle.olaoire@mycit.ie
 * Created: 10/10/13
 * Revision: 5
 * Revision History:
 *      1: 10/10/13
 *      2: 16/10/13
 *      3: 17/10/13
 *      4: 24/10/13
 *      5: 1/11/13
 *
 * Description:
 *      HomeScreen is the main activity of the Android App.
 *      It displays an Alert Dialog welcoming the user, it also contains some brief information and advice.
 *      Two input fields are then displayed, one for the users name and the other for the speed,
 *      followed by a series of buttons. Three of these buttons lead to new activities while the last
 *      button is used to exit the app. Upon exiting the user is asked if they would like their username
 *      saved for the next time they run the app.
 */

public class HomeScreen extends Activity {

    private String username;                    //this string will contain the username that the user entered. It is taken from a TextEdit
    private String usernameReverse;             //this string will contain the reversed username pass back from the ScreenThree activity
    private EditText mainEditText;              //this is the field where the user enters their username
    private EditText mainEditSpeed;             //this is the field where the user enters the speed
    private TextView usernameReverseView;       //this view displays the reversed username for the user to see
    private static final int REQ_CODE = 1010;   //this code is used when retrieving variables from a Bundle expected back from another activity

    private double speedIn;                     //this double contains the speed entered by the user


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        init();
    }

    /**
     * This method initializes all the TextViews, EditViews and Buttons for the user to see and use.
     * It is also responsible for calling the welcomeAlert method that will display the AlertDialog to the user when they start the app, as well
     * as looking for any saved information from previous instances of the app through Shared Preferences.
     * If information is found to be saved then it is loaded into the appropriate place.
     */
    private void init() {
        TextView mainTextView = (TextView) findViewById(R.id.main_textView);
        mainEditText = (EditText) findViewById(R.id.main_editText);
        mainEditSpeed = (EditText) findViewById(R.id.main_editSpeed);
        usernameReverseView = (TextView) findViewById(R.id.main_reversename);
        usernameReverseView.setText(usernameReverse);

        Button goButtonScreen2 = (Button) findViewById(R.id.main_button_scr2);
        goButtonScreen2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                toScreen2();
            }
        });

        Button goButtonScreen3 = (Button) findViewById(R.id.main_button_scr3);
        goButtonScreen3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                toScreen3();
            }
        });

        Button goButtonScreen4 = (Button) findViewById(R.id.main_button_scr4);
        goButtonScreen4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                toScreen4();
            }
        });

        Button quitButton = (Button) findViewById(R.id.quit_button);
        quitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                saveOnQuit();   //this method creates and displays an AlertDialog that asks the user if they would like their username to be saved and loaded the next time they run the app.
            }
        });

        welcomeAlert();     //this method creates and displays the AlertDialog to the user.

        SharedPreferences prefs = getSharedPreferences("myDataStorage", MODE_PRIVATE);
        username = prefs.getString("username", "");     //this line checks the shared preferences for a previously saved username, if none is found then the username defaults to ""
        mainEditText.setText(username);
    }

    /**
     * This method, saveOnQuit, creates the AlertDialog that asks the user if they want their username saved.
     * If the user selects 'Yes' then a SharedPreferences object is created, the username is saved and the app exits.
     * If the user selects 'No' then the app just exits without saving.
     */
    private void saveOnQuit() {
        AlertDialog dialog = new AlertDialog.Builder(this).create();

        dialog.setMessage(getString(R.string.save_on_exit));    //the text that populates the dialog is retrieved from the strings.xml file
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.yes),
                new DialogInterface.OnClickListener() {
            public void onClick (DialogInterface dialog, int which) {
                SharedPreferences prefs = getSharedPreferences("myDataStorage", MODE_PRIVATE);

                SharedPreferences.Editor mEditor = prefs.edit();
                username = mainEditText.getText().toString();
                mEditor.putString("username", username);        //the username is saved in a key/value fashion. this key is used to retrieve the username string later on
                mEditor.commit();
                finish();
            }
        });
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.no), new DialogInterface.OnClickListener() {
            public void onClick (DialogInterface dialog, int which) {
                finish();
            }
        });
        dialog.show();
    }

    /**
     * This method is responsible for creating the AlertDialog that welcomes the user the first time they start up the app.
     * It contains one button that the user can press to dismiss the dialog after they have read it.
     */
    private void welcomeAlert() {
        AlertDialog dialog = new AlertDialog.Builder(this).create();

        dialog.setMessage(getString(R.string.welcome_msg));     //the text that populates the dialog is retrieved from the strings.xml file
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.cont),
                new DialogInterface.OnClickListener() {
            public void  onClick (DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /**
     * This method is responsible for launching the ScreenTwo activity when the user clicks the first button.
     * Once the button is pressed this method is invoked, the username and speed values are taken from the EditText fields
     * and are sent to the ScreenTwo activity to be displayed.
     */
    private void toScreen2() {
        Intent launchScreen = new Intent(this, ScreenTwo.class);
        // Retrieve the username and speed values from the EditText fields.
        username = mainEditText.getText().toString();
        speedIn = Double.parseDouble(mainEditSpeed.getText().toString());
        // Passing info to launched Activity
        launchScreen.putExtra("speedIn", speedIn);
        launchScreen.putExtra("username", username);

        startActivity(launchScreen);
    }

    /**
     * This method has the same responsibilities as the method above, toScreen2, it is responsible for launching the
     * ScreenThree activity when the user clicks the first button.
     * Once the button is pressed this method is invoked, the username and speed values are taken from the EditText fields
     * and are sent to the ScreenThree activity to be displayed.
     * The invocation of the ScreenThree is different to that of ScreenTwo.
     * HomeScreen starts the activity and then waits for a result to be passed back. The REQ_CODE uniquely identifies the
     * bundle that contains the values that the HomeScreen activity has asked for.
     */
    private void toScreen3() {
        Intent launchScreen = new Intent(this, ScreenThree.class);
        // Retrieve the username and speed values from the EditText fields.
        username = mainEditText.getText().toString();
        speedIn = Double.parseDouble(mainEditSpeed.getText().toString());
        // Passing info to launched Activity
        launchScreen.putExtra("username", username);
        launchScreen.putExtra("speedIn", speedIn);

        startActivityForResult(launchScreen, REQ_CODE);
    }

    /**
     * This method is responsible for populating an array of strings with values defined in the arrays.xml file and sending
     * this array of strings to next activity, ScreenFourList.
     * Each of these values correspond to a list item that will be displayed in the ScreenFourList activity.
     */
    private void toScreen4() {
        // Retrieve the values to populate the array from the arrays.xml file
        String[] activity_choices = this.getResources().getStringArray(R.array.list_array);

        Intent launchScreen = new Intent(this, ScreenFourList.class);
        // Put the array into the bundle to be sent to the next activity, ScreenFourList
        launchScreen.putExtra("list_array", activity_choices);
        startActivity(launchScreen);
    }

    /**
     * This method is used to confirm the bundle returned is the right bundle and that we received a positive result,
     * i.e the value we were looking for was found.
     *
     * @param requestCode
     * This is the code that uniquely identifies the Bundle
     *
     * @param resultCode
     * The result code tells the calling activity if the value that it was looking for was found or not
     *
     * @param data
     * This is sent up to the super class. We have no nee to interact with it here
     */
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_CODE && resultCode == RESULT_OK) {
            usernameReverse = data.getExtras().getString("usernamereverse");    //the value is restored to the variable using the key/value method.
            usernameReverseView.setText(usernameReverse);                       //once retrieved it is put straight back into the TextView
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * This method saves the reversed username variable into the Bundle in the event that the Activity is terminated normally or abnormally.
     *
     * @param savedInstanceState
     * This Bundle contains all the necessary variables and information to be saved.
     * It is passed up to the super class.
     */
    public void onSaveInstanceState(Bundle savedInstanceState) {

        savedInstanceState.putString("reversedUsername", usernameReverse);  //the value of the variable is saved using the key/value method.

        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * This method restores the reversed username variable from the Bundle in the event that the Activity was terminated normally or abnormally.
     *
     * @param savedInstanceState
     * This Bundle contains all the necessary variables and information to be saved.
     * It is passed up to the super class.
     */
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        usernameReverse = savedInstanceState.getString("reversedUsername");     //the value is restored to the variable using the key/value method.
        usernameReverseView.setText(usernameReverse);
    }
}
