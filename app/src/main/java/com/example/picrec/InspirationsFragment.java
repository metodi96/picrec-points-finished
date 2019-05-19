package com.example.picrec;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.tooltip.Tooltip;
import java.util.HashMap;
import java.util.Map;

public class InspirationsFragment extends Fragment {
    private int pointsLimit = 10;
    private Tooltip tooltip;
    private HashMap<String, Integer> rolesToPoints = new HashMap<String, Integer>() {{
        put("active1", 0);
        put("drifter1", 0);
        put("escapist1", 0);
        put("sun2",0);
        put("sun1",0);
        put("arch1",0);
        put("arch2",0);
        put("classy1",0);
        put("drifter2", 0);
    }};
    ProfileFragment profileFragment = MainActivity.profileFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_inspirations, container, false);
        if (savedInstanceState==null) {
            pointsLimit = 10;
        }
        else {
            pointsLimit = savedInstanceState.getInt("pointsLimit", 10);
            rolesToPoints = (HashMap<String, Integer>) savedInstanceState.getSerializable("rolesToPoints");
        }
            GridLayout gridLayout = (GridLayout) v.findViewById(R.id.fragment_inspirations_layout);
            for (String role : rolesToPoints.keySet()) {
                addInspiration(role, gridLayout);
            }
            TextView tv = v.findViewById(R.id.points_left);
            tv.setText(String.valueOf(pointsLimit) + " points left");
        return v;
    }


    private void addInspiration(final String role, GridLayout layout) {
        //add a new button
        View item = getLayoutInflater().inflate(R.layout.pictures_inspirations, null);
        int roleId = getResources().getIdentifier(role, "drawable", getActivity().getPackageName());
        ImageView iw = item.findViewById(R.id.picture_id);
        iw.setImageResource(roleId);
        iw.setClipToOutline(true);
        final TextView points = item.findViewById(R.id.points);
        layout.addView(item);
        final ImageButton addPoints = item.findViewById(R.id.plus_sign);
        addPoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(Map.Entry<String, Integer> entry : rolesToPoints.entrySet()) {
                    String key = entry.getKey();
                    Integer value = entry.getValue();
                    if (key.equals(role) && value < 10 && pointsLimit > 0 ){
                        rolesToPoints.put(key, rolesToPoints.get(key)+1);
                        pointsLimit--;
                        points.setText(String.valueOf(value+1));
                        TextView pointsLimitField = getView().findViewById(R.id.points_left);
                        if (pointsLimit != 1) {
                           pointsLimitField.setText(pointsLimit + " points left");
                        }
                        else {
                           pointsLimitField.setText(pointsLimit + " point left");
                        }
                        resetPoints();
                        savePoints();
                    }
                }
            }
        });
        final ImageButton removePoints = item.findViewById(R.id.minus_sign);
        removePoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(Map.Entry<String, Integer> entry : rolesToPoints.entrySet()) {
                    String key = entry.getKey();
                    Integer value = entry.getValue();
                    if (key.equals(role) && value > 0){
                        rolesToPoints.put(key, rolesToPoints.get(key)-1);
                        points.setText(String.valueOf(value-1));
                        pointsLimit++;
                        TextView pointsLimitField = getView().findViewById(R.id.points_left);

                        if (pointsLimit != 1) {
                            pointsLimitField.setText(pointsLimit + " points left");
                        }
                        else {
                            pointsLimitField.setText(pointsLimit + " point left");
                        }
                        resetPoints();
                        savePoints();
                    }
                }
            }
        });

    }

    public void resetPoints() {
        Button resetPoints = getView().findViewById(R.id.reset);
        if (pointsLimit < 10) {
        resetPoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                   for (Map.Entry<String, Integer> entry : rolesToPoints.entrySet()) {
                        String key = entry.getKey();
                        rolesToPoints.put(key, 0);
                    }
                pointsLimit = 10;
                TextView pointsLimitField = getView().findViewById(R.id.points_left);
                pointsLimitField.setText(pointsLimit + " points left");
                    InspirationsFragment fragment = (InspirationsFragment)
                            getFragmentManager().findFragmentById(R.id.fragment_container);

                    getFragmentManager().beginTransaction()
                            .detach(fragment)
                            .attach(fragment)
                            .commit();
                }
            });
        }
    }

    public void savePoints() {
        final Button savePoints = getView().findViewById(R.id.save_preferences);
        savePoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pointsLimit == 0) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("rolesToPoints",rolesToPoints);
                    profileFragment.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, profileFragment).commit();

                } else {
                    tooltip = new Tooltip.Builder(savePoints)
                            .setText("Assign all points")
                            .setCancelable(true)
                            .setGravity(Gravity.START)
                            .setCornerRadius(25f)
                            .setTextColor(Color.WHITE)
                            .setTextStyle(R.font.unicodearialr)
                            .setTextSize(15f)
                            .setPadding(20)
                            .setBackgroundColor(getResources().getColor(R.color.colorPrimary))
                            .show();
                }
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("rolesToPoints", rolesToPoints);
        outState.putInt("pointsLimit", pointsLimit);
    }

}
