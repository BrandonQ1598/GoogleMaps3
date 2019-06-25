package com.example.googlemaps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, AdapterView.OnItemSelectedListener,
        GoogleMap.OnMarkerClickListener,GoogleMap.OnMarkerDragListener, GoogleMap.OnInfoWindowClickListener, GoogleMap.OnMapClickListener,GoogleMap.OnMapLongClickListener{
    private FirstMapFragment mFirstMapFragment;
    private static final int LOCATION_REQUEST_CODE = 1;
    private GoogleMap mMap;
    private Spinner mMapTypeSelector;
    private int mMapTypes[] = {
            GoogleMap.MAP_TYPE_NONE,
            GoogleMap.MAP_TYPE_NORMAL,
            GoogleMap.MAP_TYPE_SATELLITE,
            GoogleMap.MAP_TYPE_HYBRID,
            GoogleMap.MAP_TYPE_TERRAIN
    };
    private Marker markerPais;
    private Marker marker1;
    private Marker markerm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMapTypeSelector = (Spinner)
                findViewById(R.id.spinner);
        mMapTypeSelector.setOnItemSelectedListener(this);
        mFirstMapFragment = FirstMapFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.Layout_mapa, mFirstMapFragment)
                .commit();
        mFirstMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng latLng = new LatLng(19.431077, -100.349564);
        LatLng latLng2 = new LatLng(19.378273, -100.341010);
        LatLng latLng3 = new LatLng(35.858212, 138.759482);
        String posicion="";
        MarkerOptions markerOptions =
                new MarkerOptions()
                        .position(latLng)
                        .title("Casa de Aurelio")
                        .snippet("Ahi vive aurelio")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
        MarkerOptions markerOptions2 =
                new MarkerOptions()
                        .position(latLng2)
                        .title("Mi casa")
                        .snippet("Ahi vivo yo xd")
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_maps_marker));
        MarkerOptions markerOptions3 =
                new MarkerOptions()
                        .position(latLng3)
                        .title("Japon")
                        .snippet("")
                        .draggable(true)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        posicion=markerOptions3.getPosition().toString();
        markerOptions3.snippet(posicion);
        Marker marker = mMap.addMarker(markerOptions);
        marker1= mMap.addMarker(markerOptions2);
        Marker marker2= mMap.addMarker(markerOptions3);
        LatLng mexico = new LatLng(21.566836, -101.731550);
        markerPais = mMap.addMarker(new MarkerOptions()
                .position(mexico)
                .title("Mexico")
                .draggable(true)
        );
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(mexico)
                .zoom(7)
                .bearing(90)
                .tilt(90)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mexico));

        PolylineOptions sudamericaRect = new PolylineOptions()
                .add(new LatLng(12.897489, -82.441406)) // P1
                .add(new LatLng(12.897489, -32.167969)) // P2
                .add(new LatLng(-55.37911, -32.167969)) // P3
                .add(new LatLng(-55.37911, -82.441406)) // P4
                .add(new LatLng(12.897489, -82.441406)) // P1
                .color(Color.parseColor("#f44336"));
        Polyline polyline=mMap.addPolyline(sudamericaRect);
        mMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(-20.96144, -61.347656)));

        LatLng p1 = new LatLng(21.88661065803621, -85.01541511562505);
        LatLng p2 = new LatLng(22.927294359193038, -83.76297370937505);
        LatLng p3 = new LatLng(23.26620799401109, -82.35672370937505);
        LatLng p4 = new LatLng(23.387267854439315, -80.79666511562505);
        LatLng p5 = new LatLng(22.496957602618004, -77.98416511562505);
        LatLng p6 = new LatLng(20.20512046753661, -74.16092292812505);
        LatLng p7 = new LatLng(19.70944706110937, -77.65457527187505);

        Polygon cubaPolygon = mMap.addPolygon(new PolygonOptions()
                .add(p1, p2, p3, p4, p5, p6, p7, p1)
                .strokeColor(Color.parseColor("#AB47BC"))
                .fillColor(Color.parseColor("#7B1FA2")));

        LatLng center = new LatLng(39.749117, -101.413162);
        int radio = 1000000000;
        CircleOptions circleOptions = new CircleOptions()
                .center(center)
                .radius(radio)
                .strokeColor(Color.parseColor("#0D47A1"))
                .strokeWidth(4)
                .fillColor(Color.argb(32, 33, 150, 243));
        Circle circle=mMap.addCircle(circleOptions);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
            } else {
                ActivityCompat.requestPermissions(
                        this,
                        new
                                String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
            }
        }
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.addMarker(new MarkerOptions().title("El centro del mundo").position(new LatLng(0, 0)));
        mMap.setOnMarkerClickListener(this);
        mMap.setOnMarkerDragListener(this);
        mMap.setOnInfoWindowClickListener(this);
        mMap.setOnMapClickListener(this);
        mMap.setOnMapLongClickListener(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull
            String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (permissions.length > 0 &&
                    permissions[0].equals(Manifest.permission.ACCESS_FINE_LOCATION) &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    Activity#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.
                    return;
                }
                mMap.setMyLocationEnabled(true);
            } else {
                Toast.makeText(this, "Error de permisos",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        mMap.setMapType(mMapTypes[i]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (marker.equals(markerPais)) {
            Intent intent = new Intent(this, MarkerDetailActivity.class);
            intent.putExtra("latitud", ""+marker.getPosition().latitude);
            intent.putExtra("longitud", ""+marker.getPosition().longitude);
            startActivity(intent);
        }
        return false;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        if (marker.equals(markerPais)) {
            Toast.makeText(this, "START", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onMarkerDrag(Marker marker) {
        if (marker.equals(markerPais)) {
            String newTitle = "("+ marker.getPosition().latitude+","+marker.getPosition().longitude+")";
            setTitle(newTitle);
        }
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        if (marker.equals(markerPais)) {
            Toast.makeText(this, "END", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        if (marker.equals(marker1)) {
            MicasaDialogFragment.newInstance(marker.getTitle(),
                    getString(R.string.mi_casa_full_snippet))
                    .show(getSupportFragmentManager(), null);
        }
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.add("Inicio").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        CharSequence title = item.getTitle();
        if (title != null && title.equals("Inicio")) {
            LatLng españa = new LatLng(39.138891, -3.680167);
            MarkerOptions markerOptionse= new MarkerOptions().position(españa).title("España");
            Marker markere= mMap.addMarker(markerOptionse);
            mMap.animateCamera(
                    CameraUpdateFactory.newLatLng(españa), // update
                    2000, // durationMs
                    new GoogleMap.CancelableCallback() { // callback
                        @Override
                        public void onFinish() {
                            Toast.makeText(MainActivity.this, "Animación finalizada",
                                    Toast.LENGTH_LONG).show();
                        }
                        @Override
                        public void onCancel() {
                        }
                    });
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        String format = String.format(Locale.getDefault(),
                "Lat/Lng = (%f,%f)", latLng.latitude, latLng.longitude);
        Toast.makeText(this, format, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        Marker marker = mMap.addMarker(new MarkerOptions().position(latLng));
        Point point = mMap.getProjection().toScreenLocation(latLng);
        DisplayMetrics display = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display);
        int width = display.widthPixels;
        float hue;
        if (point.x <= width / 2) {
            hue = BitmapDescriptorFactory.HUE_GREEN;

        } else {
            hue = BitmapDescriptorFactory.HUE_VIOLET;
        }
        marker.setIcon(BitmapDescriptorFactory.defaultMarker(hue));
    }
}
