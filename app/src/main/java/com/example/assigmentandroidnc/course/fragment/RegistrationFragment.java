package com.example.assigmentandroidnc.course.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.assigmentandroidnc.R;
import com.example.assigmentandroidnc.course.CourseActivity;
import com.example.assigmentandroidnc.course.RegistrationActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RegistrationFragment extends Fragment {

    ArrayAdapter<String> adapter;
    ArrayList<String> listKiHoc;
    Spinner sp_kihoc;
    Button btn_dangki;





    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_registration,container,false);
        sp_kihoc = view.findViewById(R.id.spn_kihoc);
        btn_dangki=view.findViewById(R.id.btn_res);




        listKiHoc = new ArrayList<>();
        listKiHoc.add("Fall 2021");
        listKiHoc.add("Summer 2021");
        listKiHoc.add("Fall 2022");
        listKiHoc.add("Summer 2022");
        listKiHoc.add("Fall 2023");
        listKiHoc.add("Summer 2024");
        adapter = new ArrayAdapter<String>(this.getActivity(), R.layout.spiner_list,listKiHoc);
        adapter.setDropDownViewResource(R.layout.spiner_list);
        sp_kihoc.setAdapter(adapter);
        sp_kihoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_dangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chuyenactivity();
            }
        });
        return view;
    }

    private void chuyenactivity() {
        Intent intent= new Intent(this.getContext(), RegistrationActivity.class);
        String spinner_house_data = sp_kihoc.getSelectedItem().toString();
        intent.putExtra("Dataspinner_kihoc", spinner_house_data);  // Truyền một String
        startActivity(intent);






    }



}
