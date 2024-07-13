package com.example.myfirstproj;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ActivityTop10 extends AppCompatActivity {
    private static final String TAG = "ActivityTop10";
    private HighScoreFrag fragmentScore;
    private FragmentMap fragmentMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        fragmentScore = new HighScoreFrag();
        fragmentScore.setActivity(this);
        fragmentScore.setCallBackList(callBack_list);

        getSupportFragmentManager().beginTransaction().add(R.id.frameScores, fragmentScore)
                .commit();
        fragmentMap = new FragmentMap();
        getSupportFragmentManager().beginTransaction().add(R.id.frameMap, fragmentMap).commit();
    }

    CallBackList callBack_list = new CallBackList() {
        @Override
        public void zoom(double lat, double lon) {
            try {
                GoogleMap gm = fragmentMap.getmMap();
                if (gm != null) {
                    LatLng point = new LatLng(lon, lat);
                    gm.addMarker(new MarkerOptions()
                            .position(point)
                            .title("    "));
                    gm.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 13.0f));
                } else {
                    Log.e(TAG, "GoogleMap object is null");
                }
            } catch (Exception e) {
                Log.e(TAG, "Error in zoom method: ", e);
            }
        }
    };
}
