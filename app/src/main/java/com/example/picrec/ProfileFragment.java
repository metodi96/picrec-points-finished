package com.example.picrec;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ProfileFragment extends Fragment {
    private String[] roles;
    private Integer[] points;
    private String[] arrayInList;
    private HashMap<String, Integer> rolesToPoints;
    private ListView listView;
    private String[] touristRoles;
    private String[] explanations;
    private Integer[] imageIds;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
            Bundle bundle = getArguments();
            if (bundle != null) {
                if (bundle.getSerializable("rolesToPoints") != null)
                    rolesToPoints = (HashMap<String, Integer>) bundle.getSerializable("rolesToPoints");
                //remove all entries which have a value == 0
                rolesToPoints.values().removeAll(Collections.singleton(0));
                HashMap<String, Integer> rolesToPointsSorted = sortByValue(rolesToPoints);
                roles = rolesToPointsSorted.keySet().toArray(new String[rolesToPointsSorted.size()]);
                explanations = mapExplanationsToKeyName(roles);
                touristRoles = mapFullNamesToKeyName(roles);
                imageIds = mapRolesToImageIds(roles);
                points = rolesToPointsSorted.values().toArray(new Integer[rolesToPointsSorted.size()]);
                arrayInList = modifyHashMapParts(explanations, points);
                listView = (ListView) view.findViewById(R.id.list_preferences);
                MyListAdapter adapter = new MyListAdapter(getActivity(), touristRoles, arrayInList, imageIds);
                listView.setAdapter(adapter);
                TextView descriptionPreferences = view.findViewById(R.id.preferences_description);
                descriptionPreferences.setText("");
                Button addModifyPreferences = view.findViewById(R.id.add_modify_preferences);
                addModifyPreferences.setText("Modify preferences");
            }
        return view;
    }

    public String[] modifyHashMapParts(String[] stringArray, Integer[] integerArray) {
        String[] modifiedArray = new String[stringArray.length];
        for (int i = 0; i<stringArray.length; i++) {
            modifiedArray[i] = stringArray[i];
        }
        for (int i = 0; i<integerArray.length; i++) {
            modifiedArray[i] += " (" + (integerArray[i]*10)+"%)";
        }
        return modifiedArray;
    }

    public Integer[] mapRolesToImageIds(String[] array) {
        Integer[] arrayToReturn = new Integer[array.length];
        for (int i = 0; i<array.length; i++) {
            switch (array[i]){
                case "active": arrayToReturn[i] = R.drawable.snowboarder;
                    break;
                case "archaeologist": arrayToReturn[i] = R.drawable.pyramid;
                    break;
                case "classy": arrayToReturn[i] = R.drawable.wine;
                    break;
                case "actionseek": arrayToReturn[i] = R.drawable.confetti;
                    break;
                case "drifter": arrayToReturn[i] = R.drawable.backpack;
                    break;
                case "anthropologist": arrayToReturn[i] = R.drawable.handshake;
                    break;
                case "educational": arrayToReturn[i] = R.drawable.presentation;
                    break;
                case "escapist": arrayToReturn[i] = R.drawable.camping_tent;
                    break;
                case "thrill": arrayToReturn[i] = R.drawable.bungee_jumping;
                    break;
                case "sun": arrayToReturn[i] = R.drawable.sun_umbrella;
                    break;
                case "seeker": arrayToReturn[i] = R.drawable.meditation;
                    break;
                case "independent": arrayToReturn[i] = R.drawable.brochure;
                    break;
                case "independenttwo": arrayToReturn[i] = R.drawable.tourist;
                    break;
                case "explorer": arrayToReturn[i] = R.drawable.cave;
                    break;
                case "escapisttwo": arrayToReturn[i] = R.drawable.hills;
                    break;
                case "organized": arrayToReturn[i] = R.drawable.touristic;
                    break;
                case "jetsetter": arrayToReturn[i] = R.drawable.burj_al_arab;
                    break;
                default: arrayToReturn[i] = R.drawable.tourist;
                    break;
            }
        }
        return arrayToReturn;
    }

    public String[] mapFullNamesToKeyName(String[] array) {
        String[] arrayToReturn = new String[array.length];
        for (int i = 0; i<array.length; i++) {
            switch (array[i]){
                case "active": arrayToReturn[i] = "Active Sport Tourist";
                    break;
                case "archaeologist": arrayToReturn[i] = "Archaeologist";
                    break;
                case "classy": arrayToReturn[i] = "High Class Tourist";
                    break;
                case "actionseek": arrayToReturn[i] = "Action Seeker";
                    break;
                case "drifter": arrayToReturn[i] =  "Drifter";
                    break;
                case "anthropologist": arrayToReturn[i] = "Anthropologist";
                    break;
                case "educational": arrayToReturn[i] = "Educational Tourist";
                    break;
                case "escapist": arrayToReturn[i] = "Escapist I";
                    break;
                case "thrill": arrayToReturn[i] = "Thrill Seeker";
                    break;
                case "sun": arrayToReturn[i] = "Sun Lover";
                    break;
                case "seeker": arrayToReturn[i] = "Seeker";
                    break;
                case "independent": arrayToReturn[i] = "Independent Mass Tourist I";
                    break;
                case "independenttwo": arrayToReturn[i] = "Independent Mass Tourist II";
                    break;
                case "explorer": arrayToReturn[i] = "Explorer";
                    break;
                case "escapisttwo": arrayToReturn[i] = "Escapist II";
                    break;
                case "organized": arrayToReturn[i] = "Organized Mass Tourist";
                    break;
                case "jetsetter": arrayToReturn[i] = "Jet Setter";
                    break;
                default: arrayToReturn[i] = "";
                    break;
            }
        }
        return arrayToReturn;
    }

    public String[] mapExplanationsToKeyName(String[] array) {
        String[] arrayToReturn = new String[array.length];
        for (int i = 0; i<array.length; i++) {
            switch (array[i]){
                case "active": arrayToReturn[i] = "You enjoy staying active during your holidays.";
                    break;
                case "archaeologist": arrayToReturn[i] = "You are interested in exploring archeological sites.";
                    break;
                case "classy": arrayToReturn[i] = "You like it classy - fine dinners, 5-star hotels are your thing.";
                    break;
                case "actionseek": arrayToReturn[i] = "You are what people describe 'a party animal'.";
                    break;
                case "drifter": arrayToReturn[i] =  "You enjoy moving from one place to another leading a hippie lifestyle.";
                    break;
                case "anthropologist": arrayToReturn[i] = "You like meeting the locals and trying new types of food.";
                    break;
                case "educational": arrayToReturn[i] = "You enjoy participating in seminars and planned study tours.";
                    break;
                case "escapist": arrayToReturn[i] = "Stress is your enemy, you enjoy peacefulness and quietness";
                    break;
                case "thrill": arrayToReturn[i] = "Risky activities with high adrenaline is what you seek.";
                    break;
                case "sun": arrayToReturn[i] = "There's nothing better than a warm vacation at the beach.";
                    break;
                case "seeker": arrayToReturn[i] = "Seeking spiritual knowledge is what drives your spirit.";
                    break;
                case "independent": arrayToReturn[i] = "You like to visit regular destinations.";
                    break;
                case "independenttwo": arrayToReturn[i] = "You plan your own trips.";
                    break;
                case "explorer": arrayToReturn[i] = "Adventure travels and exploring places that are hard to reach is your thing.";
                    break;
                case "escapisttwo": arrayToReturn[i] = "You rely on peaceful and deserted places to overcome the stress.";
                    break;
                case "organized": arrayToReturn[i] = "You do not like traveling alone but in an organized group.";
                    break;
                default: arrayToReturn[i] = "You want to try from everything.";
                    break;
            }
        }
        return arrayToReturn;
    }

    // function to sort hash map by values
    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hashMap)
    {
        List<Map.Entry<String, Integer>> list =
                new LinkedList<>(hashMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> firstObject,
                               Map.Entry<String, Integer> secondObject)
            {
                return (secondObject.getValue()).compareTo(firstObject.getValue());
            }
        });

        HashMap<String, Integer> temp = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            temp.put(entry.getKey(), entry.getValue());
        }
        return temp;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
