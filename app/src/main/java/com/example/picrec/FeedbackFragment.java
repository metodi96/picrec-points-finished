package com.example.picrec;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class FeedbackFragment extends Fragment {

    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private RadioButton gender;
    private String genderChoice;

    private RadioButton ageGroup;
    private String ageGroupChoice;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_feedback, container, false);
        final EditText editTextFeedback = v.findViewById(R.id.insert_feedback);
        editTextFeedback.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
        final EditText editTextName = v.findViewById(R.id.insert_name);
        editTextName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
        final RadioGroup genderGroup = v.findViewById(R.id.gender_group);
        genderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                gender = genderGroup.findViewById(checkedId);

                switch (checkedId) {
                    case R.id.gender_male:
                        genderChoice = gender.getText().toString();
                        break;
                    case R.id.gender_female:
                        genderChoice = gender.getText().toString();
                        break;
                    default: break;
                }
            }
        });

        final RadioGroup ageGroupGroup = v.findViewById(R.id.agegroup_group);
        ageGroupGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                ageGroup = ageGroupGroup.findViewById(checkedId);

                switch (checkedId) {
                    case R.id.first_agegroup:
                        ageGroupChoice = ageGroup.getText().toString();
                        break;
                    case R.id.second_agegroup:
                        ageGroupChoice = ageGroup.getText().toString();
                        break;
                    case R.id.third_agegroup:
                        ageGroupChoice = ageGroup.getText().toString();
                        break;
                    case R.id.fourth_agegroup:
                        ageGroupChoice = ageGroup.getText().toString();
                        break;
                    case R.id.fifth_agegroup:
                        ageGroupChoice = ageGroup.getText().toString();
                        break;
                    case R.id.sixth_agegroup:
                        ageGroupChoice = ageGroup.getText().toString();
                        break;
                    default: break;
                }
            }
        });

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("METO_FIREBASE");
        Button submit = v.findViewById(R.id.submit_feedback);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String feedback = editTextFeedback.getText().toString();
                String websitesUsed = "";
                String generatedProfileMatchChoices = "";
                String choiceImagesAppeal = "";
                String manipulatedPicturesEnjoy = "";
                String allowedLikeDislike = "";
                String happyWithGeneratedProfile = "";
                String attractiveLayout = "";
                String easyToTellWhatILikeDislike = "";
                String easyToModifyProfile = "";
                String familiarWithSystem = "";
                String inControl = "";
                String understoodProfile = "";
                String satisfaction = "";
                String trustworthiness = "";
                String useInTheFuture = "";
                if (genderGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getContext(), "Please select at least one of the options", Toast.LENGTH_SHORT).show();
                    genderGroup.getParent().requestChildFocus(genderGroup, genderGroup);
                }
                if (ageGroupGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getContext(), "Please select at least one of the options", Toast.LENGTH_SHORT).show();
                    ageGroupGroup.getParent().requestChildFocus(ageGroupGroup, ageGroupGroup);
                }
                else {
                    FeedbackObject feedbackObject = new FeedbackObject(name, feedback, genderChoice, ageGroupChoice,
                            websitesUsed, generatedProfileMatchChoices, choiceImagesAppeal, manipulatedPicturesEnjoy,
                            allowedLikeDislike, happyWithGeneratedProfile, attractiveLayout, easyToTellWhatILikeDislike,
                            easyToModifyProfile, familiarWithSystem, inControl, understoodProfile, satisfaction, trustworthiness, useInTheFuture);
                    databaseReference.push().setValue(feedbackObject);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, MainActivity.profileFragment).commit();
                }


            }
        });
        return v;
    }



    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
