package se.transport.transport.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import se.transport.transport.R;
import se.transport.transport.Utils.Constants;

/**
 * Created by Patrik on 2015-06-13.
 */
public class CategoryActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_layout);

    }

    public void ibBack(View view) {
       this.finish();
    }

    public void bemanningButton(View view) {
        Intent myIntent = new Intent(CategoryActivity.this, SearchActivity.class);
        myIntent.putExtra(Constants.KEY_BRANSCH, Constants.KEY_BEMANNING);
        CategoryActivity.this.startActivity(myIntent);
    }

    public void bensinstationButton(View view) {
        Intent myIntent = new Intent(CategoryActivity.this, SearchActivity.class);
        myIntent.putExtra(Constants.KEY_BRANSCH, Constants.KEY_BENSIN);
        CategoryActivity.this.startActivity(myIntent);
    }

    public void bevakningButton(View view) {
        Intent myIntent = new Intent(CategoryActivity.this, SearchActivity.class);
        myIntent.putExtra(Constants.KEY_BRANSCH,Constants.KEY_BEVAKNING);
        CategoryActivity.this.startActivity(myIntent);
    }

    public void branslehanteringButton(View view) {
        Intent myIntent = new Intent(CategoryActivity.this, SearchActivity.class);
        myIntent.putExtra(Constants.KEY_BRANSCH, Constants.KEY_BRANSLE);
        CategoryActivity.this.startActivity(myIntent);
    }

    public void dackbargningButton(View view) {
        Intent myIntent = new Intent(CategoryActivity.this, SearchActivity.class);
        myIntent.putExtra(Constants.KEY_BRANSCH, Constants.KEY_BARGNING);
        CategoryActivity.this.startActivity(myIntent);
    }

    public void flygbranschenButton(View view) {
        Intent myIntent = new Intent(CategoryActivity.this, SearchActivity.class);
        myIntent.putExtra(Constants.KEY_BRANSCH, Constants.KEY_FLYG);
        CategoryActivity.this.startActivity(myIntent);
    }

    public void godsButton(View view) {
        Intent myIntent = new Intent(CategoryActivity.this, SearchActivity.class);
        myIntent.putExtra(Constants.KEY_BRANSCH, Constants.KEY_GODS);
        CategoryActivity.this.startActivity(myIntent);
    }

    public void hamnarbeteButton(View view) {
        Intent myIntent = new Intent(CategoryActivity.this, SearchActivity.class);
        myIntent.putExtra(Constants.KEY_BRANSCH, Constants.KEY_HAMN);
        CategoryActivity.this.startActivity(myIntent);
    }

    public void persontransButton(View view) {
        Intent myIntent = new Intent(CategoryActivity.this, SearchActivity.class);
        myIntent.putExtra(Constants.KEY_BRANSCH, Constants.KEY_PERSON);
        CategoryActivity.this.startActivity(myIntent);
    }

    public void renhallningButton(View view) {
        Intent myIntent = new Intent(CategoryActivity.this, SearchActivity.class);
        myIntent.putExtra(Constants.KEY_BRANSCH, Constants.KEY_RENHALLNING);
        CategoryActivity.this.startActivity(myIntent);
    }

    public void terminalButton(View view) {
        Intent myIntent = new Intent(CategoryActivity.this, SearchActivity.class);
        myIntent.putExtra(Constants.KEY_BRANSCH, Constants.KEY_TERMINAL);
        CategoryActivity.this.startActivity(myIntent);
    }

    public void tidningButton(View view) {
        Intent myIntent = new Intent(CategoryActivity.this, SearchActivity.class);
        myIntent.putExtra(Constants.KEY_BRANSCH, Constants.KEY_TIDNING);
        CategoryActivity.this.startActivity(myIntent);
    }
}
