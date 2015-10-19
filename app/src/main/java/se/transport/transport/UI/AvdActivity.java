package se.transport.transport.UI;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;


import se.transport.transport.R;
import se.transport.transport.Utils.AvdMarkers;
import se.transport.transport.Utils.Constants;


public class AvdActivity extends Activity
{
    private GoogleMap mMap;
    private ArrayList<AvdMarkers> mMyMarkersArray = new ArrayList<AvdMarkers>();
    private HashMap<Marker, AvdMarkers> mMarkersHashMap;
    String hej;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avd);

        // Initialize the HashMap for Markers and MyMarker object
        mMarkersHashMap = new HashMap<Marker, AvdMarkers>();

        mMyMarkersArray.add(new AvdMarkers("1", "Sätrahöjden 16", "80633 Gävle", "-" ,"Hamn - Norrland/Mellansverige" ,Double.parseDouble("60.699901"), Double.parseDouble("17.264901")));
        mMyMarkersArray.add(new AvdMarkers("2", "-", "-", "-" , "Hamn - Syd- och Västsverige" ,Double.parseDouble("56.556879"), Double.parseDouble("12.271732")));
        mMyMarkersArray.add(new AvdMarkers("3", "Fjärde Långgatan 48", "40231 Göteborg", "08.00-16.40" , "Göteborg med Västa Götaland", Double.parseDouble("57.698145"), Double.parseDouble("11.944046")));
        mMyMarkersArray.add(new AvdMarkers("4", "Industrigatan 11", "58277 Linköping", "08.00-15.00" , "Östergötland",Double.parseDouble("58.420211"), Double.parseDouble("15.609012")));
        mMyMarkersArray.add(new AvdMarkers("5", "Skytteholmsvägen 2", "17144 Solna", "08.00-16.30" , "Stockholm",Double.parseDouble("59.356137"), Double.parseDouble("17.999808")));
        mMyMarkersArray.add(new AvdMarkers("6", "Regnvindsgatan 8", "65221 Karlstad", "08.00-16.40" , "Värmland",Double.parseDouble("59.366106"), Double.parseDouble("13.558127")));
        mMyMarkersArray.add(new AvdMarkers("7", "Tingshusplatsen 2", "61132 Nyköping", "08.00-16.40" , "Södermanland",Double.parseDouble("58.755161"), Double.parseDouble("17.001033")));
        mMyMarkersArray.add(new AvdMarkers("9", "Elementvägen 5", "70227 Örebro", "08.00-16.40" , "Örebro",Double.parseDouble("59.262299"), Double.parseDouble("15.180605")));
        mMyMarkersArray.add(new AvdMarkers("11", "Sätrahöjden 16", "80633 Gävle", "08.00-16.40" , "Gästrikland/Norduppland",Double.parseDouble("60.691081"), Double.parseDouble("17.125336")));
        mMyMarkersArray.add(new AvdMarkers("12", "Kosterögatan 5", "21124 Malmö", "08.00-16.40" , "Malmö", Double.parseDouble("55.616984"), Double.parseDouble("13.032807")));
        mMyMarkersArray.add(new AvdMarkers("14", "Florettgatan 12", "25467 Helsingborg", "08.00-16.00" , "Helsingborg", Double.parseDouble("56.077313"), Double.parseDouble("12.722274")));
        mMyMarkersArray.add(new AvdMarkers("16", "Stenhuggarvägen 6", "62153 Visby", "08.00-16.40" , "Gotland", Double.parseDouble("57.623454"), Double.parseDouble("18.320970")));
        mMyMarkersArray.add(new AvdMarkers("17", "Norrby Tvärgata 3", "50437 Borås", "08.00-16.00" , "Skövde-Borås", Double.parseDouble("57.722868"), Double.parseDouble("12.93361")));
        mMyMarkersArray.add(new AvdMarkers("17", "Besök: Skåningstorpsvägen 5 \n Post: Box 262", "54126 Skövde", "08.00-16.00" , "Skövde-Borås", Double.parseDouble("58.426967"), Double.parseDouble("13.861514")));
        mMyMarkersArray.add(new AvdMarkers("18", "Kungsgatan 75", "82637 Söderhamn", "Mån 08.00-20.00 \n Tis-Fre: 08.00-16.40" , "Hälsingland", Double.parseDouble("61.311741"), Double.parseDouble("17.076644")));
        mMyMarkersArray.add(new AvdMarkers("19", "Ängsgärdsgatan 13", "72130 Västerås", "08.00-16.40" , "Norra Mälardalen", Double.parseDouble("59.614733"), Double.parseDouble("16.564528")));
        mMyMarkersArray.add(new AvdMarkers("20", "Kalkstensgatan 10", "55303 Jönköping", "08.00-16.40" , "Norra Småland", Double.parseDouble("57.766034"), Double.parseDouble("14.183831")));
        mMyMarkersArray.add(new AvdMarkers("25", "Kungsgatan 10", "30245 Halmstad", "08.00-16.40" , "Halmstad", Double.parseDouble("56.671171"), Double.parseDouble("12.862726")));
        mMyMarkersArray.add(new AvdMarkers("26", "Kyrkogatan 2", "97232 Luleå", "08.00-16.40" , "Norrbotten", Double.parseDouble("65.581155"), Double.parseDouble("22.148881")));
        mMyMarkersArray.add(new AvdMarkers("28", "Strömvägen 8A", "90132 Umeå", "08.00-16.40" , "I Norr", Double.parseDouble("63.846805"), Double.parseDouble("20.217973")));
        mMyMarkersArray.add(new AvdMarkers("32", "Sälstensgränd 5", "87133 Härnösand", "08.00-16.40" , "Mellersta Norrland", Double.parseDouble("62.642048"), Double.parseDouble("17.955914")));
        mMyMarkersArray.add(new AvdMarkers("41", "Portalgatan 32", "75423 Uppsala", "08.00-16.40" , "Uppsala", Double.parseDouble("59.868405"), Double.parseDouble("17.636898")));
        mMyMarkersArray.add(new AvdMarkers("46", "Stockholmsvägen 32", "19534 Märsta", "08.00-16.40" , "Flygavdelningen", Double.parseDouble("59.623844"), Double.parseDouble("17.850148")));
        mMyMarkersArray.add(new AvdMarkers("51", "Bryggerigatan 7", "29159 Kristianstad", "08.00-15.00" , "Sydöstra Sverige", Double.parseDouble("56.023349"), Double.parseDouble("14.148778")));
        mMyMarkersArray.add(new AvdMarkers("55", "Kurödsvägen 9", "45155 Uddevalla", "08.00-16.40" , "Bohusläng/Dalsland", Double.parseDouble("58.356153"), Double.parseDouble("11.972165")));
        mMyMarkersArray.add(new AvdMarkers("88", "Tunagatan 20", "78434 Borlänge", "08.00-16.40" , "Dalarna", Double.parseDouble("60.483599"), Double.parseDouble("15.433707")));






      //
      //


        setUpMap();

        plotMarkers(mMyMarkersArray);
    }

    private void plotMarkers(ArrayList<AvdMarkers> markers)
    {
        if(markers.size() > 0)
        {
            final LatLngBounds.Builder builder = new LatLngBounds.Builder();
            for (AvdMarkers myMarker : markers)
            {
                builder.include(new LatLng(myMarker.getLatitude(), myMarker.getLongitude()));
                // Create user marker with custom icon and other options
                MarkerOptions markerOption = new MarkerOptions().position(new LatLng(myMarker.getLatitude(), myMarker.getLongitude()));
                markerOption.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher));

                Marker currentMarker = mMap.addMarker(markerOption);
                mMarkersHashMap.put(currentMarker, myMarker);


            }

        }
    }



    private void setUpMap()
    {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null)
        {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

            // Check if we were successful in obtaining the map.

            if (mMap != null)
            {

                mMap.setOnInfoWindowClickListener(
                        new GoogleMap.OnInfoWindowClickListener(){
                            public void onInfoWindowClick(Marker marker){
                                Intent nextScreen = new Intent(AvdActivity.this,MainActivity.class);


                                startActivity(nextScreen);
                            }
                        }
                );

                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
                {
                    @Override
                    public boolean onMarkerClick(com.google.android.gms.maps.model.Marker marker)
                    {






                        onMarkerClickDialog(marker);

                        return true;
                    }
                });
            }
            else
                Toast.makeText(getApplicationContext(), "Unable to create Maps", Toast.LENGTH_SHORT).show();
        }
    }


    public void moveToLocation(double lati, double longi){


    LatLng latLng = new LatLng(lati, longi);



    CameraPosition newCamPos = new CameraPosition(new LatLng(lati, longi),
            7.0f,
            mMap.getCameraPosition().tilt, //use old tilt
            mMap.getCameraPosition().bearing); //use old bearing

    mMap.moveCamera(CameraUpdateFactory.newCameraPosition(newCamPos));
}
public void onMarkerClickDialog(Marker marker){

    AvdMarkers myMarker = mMarkersHashMap.get(marker);
    moveToLocation(myMarker.getLatitude(), myMarker.getLongitude());
    final Dialog dialog = new Dialog(this,
            R.style.Base_Theme_AppCompat_Light_Dialog_Alert);
    Window window = dialog.getWindow();
    window.setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT);
    dialog.setCancelable(true);

    dialog.setCanceledOnTouchOutside(true);
    dialog.setContentView(R.layout.info_layout_avd2);





    TextView avdLabel = (TextView)dialog.findViewById(R.id.avdLabel);
    TextView avdRegionLabel = (TextView)dialog.findViewById(R.id.avdRegionLabel);
    final TextView telLabel = (TextView)dialog.findViewById(R.id.telnumberLabel);
    final TextView emailLabel = (TextView)dialog.findViewById(R.id.emailAddressLabel);
    TextView postAddressLabel = (TextView)dialog.findViewById(R.id.postAdressLabel);
    TextView postAvdLabel = (TextView)dialog.findViewById(R.id.postAvdLabel);
    TextView postnrLabel = (TextView)dialog.findViewById(R.id.postNrLabel);
    TextView openHoursLabel = (TextView)dialog.findViewById(R.id.openHoursLabel);
    TextView contactTitleLabel = (TextView)dialog.findViewById(R.id.contactTitleLabel);
    ImageView closeDialog = (ImageView)dialog.findViewById(R.id.closeDialog);




    avdLabel.setText("Avdelning" + " " + myMarker.getAvdLabel());
    avdRegionLabel.setText(myMarker.getAvdRegion());
    contactTitleLabel.setText("Kontakta Avd." + " "+myMarker.getAvdLabel() );
    telLabel.setText("010-48030" + myMarker.getAvdLabel());
    emailLabel.setText("transport."+myMarker.getAvdLabel() + "@transport.se");
    postAddressLabel.setText(myMarker.getAddress());
    postnrLabel.setText(myMarker.getPostnr());
    postAvdLabel.setText("Avd." + " "+myMarker.getAvdLabel());
    openHoursLabel.setText(myMarker.getOpenHours());




    emailLabel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("mailto:"+emailLabel.getText().toString()));
            dialog.dismiss();
            startActivity(intent);
            finish();
        }
    });
    telLabel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:"+telLabel.getText().toString()));
            dialog.dismiss();
            startActivity(intent);
            finish();
        }
    });

   closeDialog.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           dialog.dismiss();
       }
   });


    dialog.show();
}



    public void OnClickClose(View view){
        finish();
    }

    public void goToTransport(View view){
          Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.WEB_URL));

        AvdActivity.this.startActivity(browserIntent);
        finish();
    }

    public void ibBack(View view){
        finish();
    }
}