package se.transport.transport;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Patrik on 2015-06-04.
 */
public class Info extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Intent intent = getIntent();
        //String value = intent.getStringExtra("key"); //if it's a string you stored.
        super.onCreate(savedInstanceState);

        setContentView(R.layout.infolayout);
    }

    public void ibBack(View view) {
        this.finish();
    }
}
