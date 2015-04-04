package ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.actionbarcompat.styled.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Mainpage extends FragmentActivity {

    private GoogleMap mMap;
    public Marker marker;
    private PolylineOptions poly;
    Bitmap bitmap;
    public static final int RUNSTATE = 1;
    public static final int STOPSTATE = 0;

    public  final static String SER_KEY = "ui";
    private int state = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        setUpMapIfNeeded();

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
        Location location = null;
        if(location != null) {
            location = locationManager.getLastKnownLocation(provider);
            LatLng latlng = fromLocationToLatLng(location);
            marker = mMap.addMarker(new MarkerOptions().position(latlng).icon(BitmapDescriptorFactory.defaultMarker(
                    BitmapDescriptorFactory.HUE_GREEN)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng,
                    17));
        }

        poly = new PolylineOptions();
        updateWithNewLocation(location);
        locationManager.requestLocationUpdates(provider, 1000, 10,
                locationListener);
    }
    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    public static LatLng fromLocationToLatLng(Location location){
        return new LatLng(location.getLatitude(), location.getLongitude());
    }

    private void updateWithNewLocation(Location location) {

        if (location != null) {
            // Update the map location.

            LatLng latlng=fromLocationToLatLng(location);

            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng,
                    17));


            if(marker !=null)
                marker.remove();

            marker =mMap.addMarker(new MarkerOptions().position(latlng).icon(BitmapDescriptorFactory.defaultMarker(
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
        public void onStatusChanged(String provider, int status,
                                    Bundle extras) {}
    };

    private void setUpMapIfNeeded() {
        if (mMap == null) {
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            if (mMap != null) {
                setUpMap();
            }
        }
    }


    private void setUpMap() {
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    public void buttonClicked(View view){
        Button bt = (Button) findViewById(R.id.googlemaps_start);
        state++;
        if(state == RUNSTATE) {
            bt.setText("Stop");
        }else{
            state = STOPSTATE;
            try {

                Bundle mBundle = new Bundle();
                Drawable drawable = CaptureMapScreen();
                // mBundle.putSerializable(SER_KEY, drawable);
                Intent intent = new Intent(this, Resultdisplay.class);
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
