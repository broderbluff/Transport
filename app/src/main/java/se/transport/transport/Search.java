package se.transport.transport;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.view.ActionMode;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
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

/**
 * Created by Patrik on 2015-06-08.
 */

public class Search extends ListActivity implements OnClickListener, AppCompatCallback {
    private EditText txtkeyword;

    private ImageButton btnsearch;

    private TextView tvIntro;
    private ProgressDialog pDialog;


    JSONParser jParser = new JSONParser();

    ArrayList<HashMap<String, String>> avtalList;


    private static String url_search = "http://brimir.eu/search.php";


    private static final String TAG_SUCCESS = "success";
    private static final String TAG_AVTAL = "avtal";
    private static final String TAG_ID = "id";
    private static final String TAG_COMPANY = "company";
    private static final String TAG_ADDRESS = "address";

    JSONArray idioms = null;

    public String companysearch;
    public String bransch;
    TextView emptyText;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        Intent myIntent = getIntent();
        tvIntro = (TextView)findViewById(R.id.tvIntro);
        bransch = myIntent.getStringExtra("bransch");


        txtkeyword = (EditText) findViewById(R.id.txtkeyword);
        btnsearch = (ImageButton) findViewById(R.id.imageSearchButton);
        btnsearch.setOnClickListener(this);

        avtalList = new ArrayList<HashMap<String, String>>();

        //keyboard with searchbutton
        txtkeyword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    tvIntro.setVisibility(View.GONE);
                    InputMethodManager inputManager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    companysearch = txtkeyword.getText().toString();
                    avtalList.clear();
                    if (isNetworkAvailable()) {

                        new LoadKollektivAvtal().execute();
                    }

                    return true;
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

                Intent intent = new Intent(Search.this, MapsActivity.class);
                intent.putExtra("address", address);
                intent.putExtra("company", companyName);




                startActivity(intent);


            }
        });


    }


    public void ibBack(View view) {
        this.finish();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imageSearchButton) {
            tvIntro.setVisibility(View.GONE);
            InputMethodManager inputManager = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);

            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
            companysearch = txtkeyword.getText().toString();
            avtalList.clear();

            if (isNetworkAvailable()) {

                new LoadKollektivAvtal().execute();
            }

        }

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
            pDialog = new ProgressDialog(Search.this);
            pDialog.setMessage(Search.this.getString(R.string.search_dialog));
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }


        protected String doInBackground(String... args) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("company", companysearch));
            params.add(new BasicNameValuePair("bransch", bransch));

            JSONObject json = jParser.makeHttpRequest(url_search, "GET", params);


            Log.d("Search agreements: ", json.toString());

            try {

                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {

                    idioms = json.getJSONArray(TAG_AVTAL);


                    for (int i = 0; i < idioms.length(); i++) {
                        JSONObject c = idioms.getJSONObject(i);


                        String id = c.getString(TAG_ID);
                        String company = c.getString(TAG_COMPANY);
                        String address = c.getString(TAG_ADDRESS);


                        HashMap<String, String> map = new HashMap<String, String>();


                        map.put(TAG_ID, id);
                        map.put(TAG_COMPANY, company);
                        map.put(TAG_ADDRESS, address);

                        avtalList.add(map);
                    }
                } else {

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }


        protected void onPostExecute(String file_url) {
// dismiss the dialog after getting the related idioms
            pDialog.dismiss();
// updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
/**
 * Updating parsed JSON data into ListView
 * */

                    ListAdapter adapter = new SimpleAdapter(
                            Search.this, avtalList,
                            R.layout.list_view, new String[]{TAG_ID, TAG_COMPANY, TAG_ADDRESS},
                            new int[]{R.id.id, R.id.company, R.id.address});
                    {

                    }
                    ListView lv = (ListView) findViewById(android.R.id.list);
                    TextView emptyText = (TextView) findViewById(R.id.empty_listview);
                    lv.setEmptyView(emptyText);


// updating listview
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