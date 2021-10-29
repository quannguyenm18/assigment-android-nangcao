package com.example.assigmentandroidnc.course.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import com.example.assigmentandroidnc.R;
import com.example.assigmentandroidnc.adapter.KhoaHocAdapter;
import com.example.assigmentandroidnc.course.UpdateKHActivity;
import com.example.assigmentandroidnc.database.KhoaHocDatabase;
import com.example.assigmentandroidnc.interfaces.IClickButton;
import com.example.assigmentandroidnc.model.KhoaHoc;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CourseFragment extends Fragment {
    private RecyclerView rv_list;
    private ArrayList<KhoaHoc> lisKH;
    private KhoaHocAdapter khoaHocAdapter;


    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course, container, false);
        rv_list = view.findViewById(R.id.rv_listCourse);
        setRecycleView();
        loadData();
        return view;

    }



    private void setRecycleView() {
        lisKH = new ArrayList<>();
        lisKH= (ArrayList<KhoaHoc>) KhoaHocDatabase.getInstance(getContext()).KhoaHocDAO().getListKhoaHoc();

        RecyclerView.ItemDecoration itemDecoration= new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        rv_list.addItemDecoration(itemDecoration);
        khoaHocAdapter = new KhoaHocAdapter(new IClickButton() {
            @Override
            public void deleteItem(KhoaHoc khoaHoc) {

                clickDeleteItem(khoaHoc);
            }

            @Override
            public void clickupdateItem(KhoaHoc khoaHoc) {
                clickUpdateKhoaHoc(khoaHoc);
            }
        });
        khoaHocAdapter.setData(lisKH);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this.getActivity());
        rv_list.setLayoutManager(linearLayout);
        rv_list.setAdapter(khoaHocAdapter);

    }

    private void clickUpdateKhoaHoc(KhoaHoc khoaHoc) {
        Intent intent = new Intent(getContext(), UpdateKHActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Object_KH",khoaHoc);
        intent.putExtras(bundle);
        startActivity(intent);


    }

    private void clickDeleteItem(KhoaHoc khoaHoc){

        new AlertDialog.Builder(getContext())
                .setTitle("Xóa Khóa Học Này ?")
                .setMessage("Bạn Chắc Chắn chứ  ?")
                .setPositiveButton("Đồng Ý ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        KhoaHocDatabase.getInstance(getContext()).KhoaHocDAO().deleteItem(khoaHoc);

                        Toast.makeText(getContext(), "Xóa Thành Công", Toast.LENGTH_LONG).show();

                       loadData();

                    }
                }).setNegativeButton("Không", null).show();


    }

    public void loadData() {
        lisKH = (ArrayList<KhoaHoc>) KhoaHocDatabase.getInstance(getContext()).KhoaHocDAO().getListKhoaHoc();
        khoaHocAdapter.setData(lisKH);
        rv_list.setAdapter(khoaHocAdapter);

    }

}
