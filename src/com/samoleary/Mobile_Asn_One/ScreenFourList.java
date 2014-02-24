package com.samoleary.Mobile_Asn_One;

import android.R;
import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * Author: Sam O'Leary
 * Email: somhairle.olaoire@mycit.ie
 * Created: 16/10/13
 * Revision: 3
 * Revision History:
 *      1: 16/10/13
 *      2: 17/10/13
 *      3: 1/11/13
 *
 *
 * Description:
 *      ScreenFourList is a ListActivity. It displays a list of 3 items to the user.
 *      These items are as follows:
 *          A link to the RSA's Website
 *          An item that takes the user to a new activity that displays an animation showing some app info
 *          An item that takes the user back to the HomeScreen activity.
 */

public class ScreenFourList extends ListActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    /**
     * This initialize method creates an array of strings from an array passed from the HomeScreen activity.
     * Each string in the array corresponds to what is displayed on the face of each item in the list.
     */

    private void init() {
        Intent i = getIntent();
        String[] activityChoices = i.getStringArrayExtra("list_array");     //the array of strings

        setListAdapter(new ArrayAdapter<String>(this,
                R.layout.simple_list_item_1, activityChoices));             //applying the array of strings to the list
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);           //the user may only select one list item at a time
        getListView().setTextFilterEnabled(true);                           //if multiple occurrences of a string exist then this list will only display 1 occurrence

        getListView().setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                switch(arg2) {
                    case 0: //opens web browser and navigates to given website
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("http://www.rsa.ie/")));
                        break;
                    case 1: //launches a new activity that displays an animation containing some app info to the user
                        Intent launchScreen = new Intent(ScreenFourList.this, DisplayGraphic.class);
                        startActivity(launchScreen);
                        break;
                    case 2: //returns the user back to the main screen
                        finish();
                        break;
                    default: break;
                }
            }
        });
    }
}
