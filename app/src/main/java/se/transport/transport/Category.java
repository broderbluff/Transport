package se.transport.transport;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Patrik on 2015-06-13.
 */
public class Category extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_layout);

    }

    public void ibBack(View view) {
       this.finish();
    }

    public void bemanningButton(View view) {
        Intent myIntent = new Intent(Category.this, Search.class);
        myIntent.putExtra("bransch", "bemanning");
        Category.this.startActivity(myIntent);
    }

    public void bensinstationButton(View view) {
        Intent myIntent = new Intent(Category.this, Search.class);
        myIntent.putExtra("bransch", "bensinstation");
        Category.this.startActivity(myIntent);
    }

    public void bevakningButton(View view) {
        Intent myIntent = new Intent(Category.this, Search.class);
        myIntent.putExtra("bransch", "bevakning");
        Category.this.startActivity(myIntent);
    }

    public void branslehanteringButton(View view) {
        Intent myIntent = new Intent(Category.this, Search.class);
        myIntent.putExtra("bransch", "branslehantering");
        Category.this.startActivity(myIntent);
    }

    public void dackbargningButton(View view) {
        Intent myIntent = new Intent(Category.this, Search.class);
        myIntent.putExtra("bransch", "dackbargning");
        Category.this.startActivity(myIntent);
    }

    public void flygbranschenButton(View view) {
        Intent myIntent = new Intent(Category.this, Search.class);
        myIntent.putExtra("bransch", "flygbranschen");
        Category.this.startActivity(myIntent);
    }

    public void godsButton(View view) {
        Intent myIntent = new Intent(Category.this, Search.class);
        myIntent.putExtra("bransch", "godstransport");
        Category.this.startActivity(myIntent);
    }

    public void hamnarbeteButton(View view) {
        Intent myIntent = new Intent(Category.this, Search.class);
        myIntent.putExtra("bransch", "hamnarbete");
        Category.this.startActivity(myIntent);
    }

    public void persontransButton(View view) {
        Intent myIntent = new Intent(Category.this, Search.class);
        myIntent.putExtra("bransch", "persontransport");
        Category.this.startActivity(myIntent);
    }

    public void renhallningButton(View view) {
        Intent myIntent = new Intent(Category.this, Search.class);
        myIntent.putExtra("bransch", "renhallning");
        Category.this.startActivity(myIntent);
    }

    public void terminalButton(View view) {
        Intent myIntent = new Intent(Category.this, Search.class);
        myIntent.putExtra("bransch", "terminal");
        Category.this.startActivity(myIntent);
    }

    public void tidningButton(View view) {
        Intent myIntent = new Intent(Category.this, Search.class);
        myIntent.putExtra("bransch", "tidning");
        Category.this.startActivity(myIntent);
    }
}
