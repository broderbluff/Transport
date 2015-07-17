package se.transport.transport;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;



public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    public void infoClick(View view) {
        Intent myIntent = new Intent(MainActivity.this, Info.class);
      //  myIntent.putExtra("key", value); //Optional parameters
        MainActivity.this.startActivity(myIntent);
    }

    public void searchClick(View view) {
        Intent myIntent = new Intent(MainActivity.this, Category.class);
        //  myIntent.putExtra("key", value); //Optional parameters
        MainActivity.this.startActivity(myIntent);
    }
    public void logoClick(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.transport.se"));
        MainActivity.this.startActivity(browserIntent);
    }

}
