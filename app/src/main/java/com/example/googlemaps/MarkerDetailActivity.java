package com.example.googlemaps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MarkerDetailActivity extends AppCompatActivity {
    private TextView detalles;
    String EXTRA_LONGITUD;
    String EXTRA_LATITUD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marker_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        EXTRA_LONGITUD=getIntent().getStringExtra("longitud");
        EXTRA_LATITUD=getIntent().getStringExtra("latitud");
        String posicion = "("+EXTRA_LATITUD+","+EXTRA_LONGITUD+")";
        detalles=findViewById(R.id.detallesmarcador);
        detalles.setText(posicion);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
