package se.transport.transport.UI;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import se.transport.transport.R;

/**
 * Created by Patrik on 2015-06-04.
 */
public class InfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.infolayout);
    }

    public void ibBack(View view) {
        this.finish();
    }
}
