package se.transport.transport.UI;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import se.transport.transport.R;
import se.transport.transport.Utils.Constants;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void infoClick(View view) {
        Intent myIntent = new Intent(MainActivity.this, InfoActivity.class);
        MainActivity.this.startActivity(myIntent);
    }

    public void searchClick(View view) {
        Intent myIntent = new Intent(MainActivity.this, CategoryActivity.class);
        MainActivity.this.startActivity(myIntent);
    }
    public void logoClick(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.WEB_URL));
        MainActivity.this.startActivity(browserIntent);
    }

}
