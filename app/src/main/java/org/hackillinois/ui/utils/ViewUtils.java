package org.hackillinois.ui.utils;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by Stephen on 2/18/2015.
 */
public class ViewUtils {

    /**
     * This method allows the user to tap outside of the soft keyboard to close it
     * (default behavior is soft keyboard is closed with back button press)
     * @param view The view to setup (usually the root view for a fragment)
     * @param activity The activity containing the view
     */
    public static void setupUI(View view, final Activity activity) {

        //Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(activity);
                    return false;
                }

            });
        }

        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView, activity);
            }
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View focus = activity.getCurrentFocus();
        try {
            inputMethodManager.hideSoftInputFromWindow(focus.getWindowToken(), 0);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }
}
