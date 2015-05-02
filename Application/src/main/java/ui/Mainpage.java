package ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import com.example.android.actionbarcompat.styled.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.text.NumberFormat;

public class Mainpage extends FragmentActivity {
    private GoogleMap mMap;
    private Marker marker;
    private PolylineOptions poly;
    private Bitmap bitmap;
    private int state = 0;
    private Button startBtn;
    public static final int RUNSTATE = 1;
    public static final int STOPSTATE = 0;
    public final static String SER_KEY = "ui";
    public Location location;
    public Location mylocation;
    public Location startPoint;
    public Location endPoint;
    public double startTime;
    public double endTime;

    public final static String EXTRA_MESSAGE = "com.mycompany.MortgageCalculation.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_mainpage);
        setUpMapIfNeeded();
        //start button
        startBtn = (Button)findViewById(R.id.googlemaps_start);
        LocationManager locationManager;
        String svcName= Context.LOCATION_SERVICE;
        locationManager = (LocationManager)getSystemService(svcName);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setSpeedRequired(false);
        criteria.setCostAllowed(true);
        String provider = locationManager.getBestProvider(criteria, true);
        location = null;
        if(location != null) {
            location = locationManager.getLastKnownLocation(provider);
            mylocation = location;
            LatLng latlng = fromLocationToLatLng(location);
            marker = mMap.addMarker(new MarkerOptions().position(latlng).icon(BitmapDescriptorFactory.defaultMarker(
                    BitmapDescriptorFactory.HUE_GREEN)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng,17));
        }

        //draw the path of running
        poly = new PolylineOptions();
        updateWithNewLocation(location);
        //update the location in a certain interval
        locationManager.requestLocationUpdates(provider, 1000, 10, locationListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    public static LatLng fromLocationToLatLng(Location location){
        return new LatLng(location.getLatitude(), location.getLongitude());
    }

    //update the new location
    private void updateWithNewLocation(Location location) {

        if (location != null) {
            mylocation = location;
            LatLng latlng = fromLocationToLatLng(location);
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng,17));
            if(marker != null)
                marker.remove();
            marker = mMap.addMarker(new MarkerOptions().position(latlng).icon(BitmapDescriptorFactory.defaultMarker(
                    BitmapDescriptorFactory.HUE_GREEN)).title("Running map"));
            double lat = location.getLatitude();
            double lng = location.getLongitude();

            poly.add(new LatLng(lat,lng));
            poly.color(Color.BLUE);
            poly.width(5);
            mMap.addPolyline(poly);
        }
    }



    private final LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            updateWithNewLocation(location);
        }
        public void onProviderDisabled(String provider) {}
        public void onProviderEnabled(String provider) {}
        public void onStatusChanged(String provider, int status, Bundle extras) {}
    };

    private void setUpMapIfNeeded() {
        if (mMap == null) {
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            if (mMap != null) {
                setUpMap();
            }
        }
    }


    private void setUpMap() {
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    //when the button is clicked
    public void buttonClicked(View view){
        startBtn = (Button) findViewById(R.id.googlemaps_start);
        state++;
        //try to change the state, if the user is running, set the text of the button to stop
        if(state == RUNSTATE) {
            startBtn.setText("Stop");

            startPoint = mylocation;
            //update the last location of logged in account
            StringBuilder sb = new StringBuilder();
            sb.append(startPoint.getLongitude());
            sb.append(",");
            sb.append(startPoint.getLatitude());
            //update last location
            LogIn.loginAccount.getBasicAccount().getHistoryRecord().setLastLocation(sb.toString());
            //start to count the running time
            startTime = SystemClock.elapsedRealtime();
        }else{
            //if the user stops running
            state = STOPSTATE;
            try {
                //get the end point
                endPoint = mylocation;
                float results[]=new float[1];

                //calculate the distance between the starting point and endpoint
                Location.distanceBetween(startPoint.getLatitude(), startPoint.getLongitude(),
                        endPoint.getLatitude(), endPoint.getLongitude(), results);

                //Bundle mBundle = new Bundle();
                //Drawable drawable = CaptureMapScreen();
                // mBundle.putSerializable(SER_KEY, drawable);
                Intent intent = new Intent(this, Resultdisplay.class);
                String distance = NumberFormat.getInstance().format(results[0]);
                endTime = SystemClock.elapsedRealtime();
                //calculate the running time
                double totalTime = endTime - startTime;
                String pastTime = String.valueOf(totalTime);
                StringBuilder sb = new StringBuilder();
                sb.append(distance);
                sb.append("werun");
                sb.append(pastTime);
                intent.putExtra(EXTRA_MESSAGE, sb.toString());

                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public Drawable CaptureMapScreen()
    {
        GoogleMap.SnapshotReadyCallback callback = new GoogleMap.SnapshotReadyCallback() {

            @Override
            public void onSnapshotReady(Bitmap snapshot) {
                bitmap = snapshot;
            }
        };
        mMap.snapshot(callback);
        return new BitmapDrawable(getResources(),bitmap);
    }
}
