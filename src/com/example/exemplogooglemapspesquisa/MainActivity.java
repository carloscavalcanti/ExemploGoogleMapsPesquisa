package com.example.exemplogooglemapspesquisa;

import java.io.IOException;
import java.util.List;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

public class MainActivity extends MapActivity {

	private MapView mapView;
	private EditText edtPesquisa;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        edtPesquisa = (EditText) findViewById(R.id.edtEndereco);
        mapView = (MapView) findViewById(R.id.map_view);
        
        mapView.setTraffic(true);
        mapView.setClickable(true);
    }
	
	public void onPesquisar(View v) {
		  Geocoder geocoder = new Geocoder(this);
	        try {
				List<Address> addresses = geocoder.getFromLocationName(edtPesquisa.getText().toString(), 1);
				int latitude = calcular1E6(addresses.get(0).getLatitude());
				int longitude = calcular1E6(addresses.get(0).getLongitude());
				GeoPoint geoPoint = new GeoPoint(latitude, longitude);
				mapView.getOverlays().add(new CirculoOverlay(geoPoint));
				mapView.getController().setCenter(geoPoint);
				mapView.getController().setZoom(18);
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	private int calcular1E6(double valor) {
		return (int) (valor * 1E6);
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
    
}
