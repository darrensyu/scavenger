package com.example.darren.scavenger;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.PointTarget;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DecimalFormat;

public class Scavenge extends Activity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{
    protected static final String TAG = "Scavenge";

    static GoogleMap map; // Might be null if Google Play services APK is not available.
    Location currLocation;
    GoogleApiClient googleApiClient;
    LocationRequest locationRequest;
    LatLng currCoordinates;

    public static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;
    public static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS
            = UPDATE_INTERVAL_IN_MILLISECONDS/2;

    protected static final float ZOOM_LEVEL = 19;
    double lat;
    double lng;
    Context context;
    TextView marker_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scavenge);
        context = getApplicationContext();
        marker_text = (TextView) findViewById(R.id.scv_chest_box);
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        map.setMyLocationEnabled(true);
        final ShowcaseView showcaseView = new ShowcaseView.Builder(this,true)
                    .setStyle(R.style.CustomShowcaseTheme)
                    .setTarget(new PointTarget(0, 1000000))
                            //.setTarget(new ViewTarget(chestList));
                    .setContentTitle("Ready to Scavenge?")
                    .setContentText("Use this map to look for dropped treasures. " +
                            "These items are free to snatch if you get to them in time! " +
                            "Once the item is found, scan the QR code to redeem. " +
                            "Treasures will be added to the Chest! \n" +
                            "\n" +
                            "Start scavenging!")
                    .build();
            showcaseView.show();
            showcaseView.overrideButtonClick(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showcaseView.hide();
                }
            });


        buildGoogleApiClient();
        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.showInfoWindow();
                double markerDistance =
                        69.00 * Math.sqrt(
                                Math.pow((marker.getPosition().latitude -
                                        currCoordinates.latitude), 2) +
                                        Math.pow((marker.getPosition().longitude -
                                                currCoordinates.longitude), 2));
                //Log.d("Scav_distance",String.valueOf(markerDistance));
                DecimalFormat df = new DecimalFormat("#.##");
                marker_text.setText(df.format(markerDistance) + " miles away");
                if (marker_text.getText().equals("0 miles away")) {
                    marker_text.setText("It's close!");
                }
                return true;
            }
        });
    }

    @Override
    public void onConnectionSuspended(int cause) {
        // The connection to Google Play services was lost for some reason. We call connect() to
        // attempt to re-establish the connection.
        Log.i(TAG, "Connection suspended");
        googleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        // Refer to the javadoc for ConnectionResult to see what error codes might be returned in
        // onConnectionFailed.
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
    }

    @Override
    public void onStart(){
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    public void onStop(){
        super.onStop();
        if(googleApiClient.isConnected()){
            googleApiClient.disconnect();
        }
    }
    @Override
    public void onConnected(Bundle connectionHint){
        currLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if(currLocation == null){
            Toast.makeText(context,"No location detected",Toast.LENGTH_SHORT).show();
        }
        else {
            setToLocation(currLocation);
        }
    }

    public synchronized void buildGoogleApiClient(){
        googleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        createLocationRequest();
    }

    public void createLocationRequest(){
        locationRequest = new LocationRequest();
        locationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        locationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    private void setToLocation(Location loc){
        map.setMyLocationEnabled(true);
        map.clear();
        lat =  loc.getLatitude();
        lng = loc.getLongitude();
        currCoordinates = new LatLng(lat,lng);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(currCoordinates, ZOOM_LEVEL));
        map.addMarker(new MarkerOptions().title("Jamba Juice")
                .snippet("Status: unreleased")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.scavengers_gift))
                .position(new LatLng(currCoordinates.latitude + 0.00003, currCoordinates.longitude + 0.00004)));
        map.addMarker(new MarkerOptions().title("Starbucks")
                .snippet("Status: unreleased")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.scavengers_gift))
                .position(new LatLng(currCoordinates.latitude + 0.0008, currCoordinates.longitude + 0.0005)));
        map.addMarker(new MarkerOptions().title("Best Buy")
                .snippet("Status: unreleased")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.scavengers_gift))
                .position(new LatLng(32.878163, -117.234716)));
    }
}
