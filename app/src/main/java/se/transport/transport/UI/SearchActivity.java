package se.transport.transport.UI;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.view.ActionMode;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import se.transport.transport.R;
import se.transport.transport.Utils.Constants;
import se.transport.transport.Utils.JSONParser;

/**
 * Created by Patrik on 2015-06-08.
 */

public class SearchActivity extends ListActivity implements AppCompatCallback {
    private EditText searchEditText;


    private TextView tvIntro;
    private ProgressDialog pDialog;


    JSONParser jParser = new JSONParser();

    ArrayList<HashMap<String, String>> avtalList;


    private static String url_search = "http://brimir.eu/search.php";


    protected JSONArray avtal = null;

    public String companysearch;
    public String bransch;
    TextView emptyText;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        Intent myIntent = getIntent();
        tvIntro = (TextView) findViewById(R.id.tvIntro);
        bransch = myIntent.getStringExtra(Constants.KEY_BRANSCH);


        searchEditText = (EditText) findViewById(R.id.searchEditText);


        avtalList = new ArrayList<HashMap<String, String>>();


        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    tvIntro.setVisibility(View.GONE);
                    InputMethodManager inputManager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    companysearch = searchEditText.getText().toString();
                    avtalList.clear();
                    if (isNetworkAvailable()) {

                        new LoadKollektivAvtal().execute();
                    }

                    return true;
                }
                return false;
            }
        });


        searchEditText.setOnTouchListener(new View.OnTouchListener() {

            final int DRAWABLE_RIGHT = 2;


            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    int leftEdgeOfRightDrawable = searchEditText.getRight()
                            - searchEditText.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width();

                    leftEdgeOfRightDrawable -= getResources().getDimension(R.dimen.image_padding);
                    if (event.getRawX() >= leftEdgeOfRightDrawable) {
                        tvIntro.setVisibility(View.GONE);
                        InputMethodManager inputManager = (InputMethodManager)
                                getSystemService(Context.INPUT_METHOD_SERVICE);

                        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
                        companysearch = searchEditText.getText().toString();
                        avtalList.clear();

                        if (isNetworkAvailable()) {

                            new LoadKollektivAvtal().execute();
                        }
                        searchEditText.setText("");
                        return true;
                    }
                }
                return false;
            }
        });


        ListView lv = getListView();


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String address = ((TextView) view.findViewById(R.id.address)).getText()
                        .toString();
                String companyName = ((TextView) view.findViewById(R.id.company)).getText()
                        .toString();

                Intent intent = new Intent(SearchActivity.this, MapsActivity.class);
                intent.putExtra(Constants.SEARCH_TAG_ADDRESS, address);
                intent.putExtra(Constants.SEARCH_TAG_COMPANY, companyName);


                startActivity(intent);


            }
        });


    }


    public void ibBack(View view) {
        this.finish();
    }


    @Override
    public void onSupportActionModeStarted(ActionMode mode) {

    }

    @Override
    public void onSupportActionModeFinished(ActionMode mode) {

    }

    @Nullable
    @Override
    public ActionMode onWindowStartingSupportActionMode(ActionMode.Callback callback) {
        return null;
    }


    class LoadKollektivAvtal extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(SearchActivity.this);
            pDialog.setMessage(SearchActivity.this.getString(R.string.search_dialog));
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }


        protected String doInBackground(String... args) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair(Constants.SEARCH_TAG_COMPANY, companysearch));
            params.add(new BasicNameValuePair(Constants.KEY_BRANSCH, bransch));

            JSONObject json = jParser.makeHttpRequest(url_search, "GET", params);


            Log.d("Search agreements: ", json.toString());

            try {

                int success = json.getInt(Constants.SEARCH_TAG_SUCCESS);

                if (success == 1) {

                    avtal = json.getJSONArray(Constants.SEARCH_TAG_AVTAL);


                    for (int i = 0; i < avtal.length(); i++) {
                        JSONObject c = avtal.getJSONObject(i);


                        String id = c.getString(Constants.SEARCH_TAG_ID);
                        String company = c.getString(Constants.SEARCH_TAG_COMPANY);
                        String address = c.getString(Constants.SEARCH_TAG_ADDRESS);


                        HashMap<String, String> map = new HashMap<String, String>();


                        map.put(Constants.SEARCH_TAG_ID, id);
                        map.put(Constants.SEARCH_TAG_COMPANY, company);
                        map.put(Constants.SEARCH_TAG_ADDRESS, address);

                        avtalList.add(map);
                    }
                } else {
                    runOnUiThread(new Runnable() {
                        public void run() {
/**
 * Updating parsed JSON data into ListView
 * */


                            ListView lv = (ListView) findViewById(android.R.id.list);
                            TextView emptyText = (TextView) findViewById(R.id.empty_listview);
                            lv.setEmptyView(emptyText);


                        }


                    });
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }


        protected void onPostExecute(String file_url) {

            pDialog.dismiss();

            runOnUiThread(new Runnable() {
                public void run() {
/**
 * Updating parsed JSON data into ListView
 * */

                    ListAdapter adapter = new SimpleAdapter(
                            SearchActivity.this, avtalList,
                            R.layout.list_view, new String[]{Constants.SEARCH_TAG_ID, Constants.SEARCH_TAG_COMPANY, Constants.SEARCH_TAG_ADDRESS},
                            new int[]{R.id.id, R.id.company, R.id.address});
                    {

                    }
                    ListView lv = (ListView) findViewById(android.R.id.list);
                    TextView emptyText = (TextView) findViewById(R.id.empty_listview);
                    lv.setEmptyView(emptyText);

                    setListAdapter(adapter);

                }


            });

        }

    }


    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        } else {
            Toast.makeText(this, R.string.toast_no_internet, Toast.LENGTH_LONG).show();
        }
        return isAvailable;
    }

}