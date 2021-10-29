package com.example.assigmentandroidnc.course;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.assigmentandroidnc.R;
import com.example.assigmentandroidnc.course.fragment.CourseFragment;
import com.example.assigmentandroidnc.database.KhoaHocDatabase;
import com.example.assigmentandroidnc.model.KhoaHoc;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class UpdateKHActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText edt_kihoc, edt_monhoc, edt_ngaythi, edt_lophoc;
    private Spinner sp_licHoc;
    private Button btn_res;
    private  KhoaHoc kh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        initView();
        setSpinerLicHoc();
        setData();
    }

    private void setData() {
        Bundle bundle =getIntent().getExtras();

        if (bundle == null){
            return;
        }
        kh= (KhoaHoc) getIntent().getExtras().get("Object_KH");
        edt_kihoc.setText(kh.getKihoc());
        edt_lophoc.setText(kh.getLopHoc());
        edt_monhoc.setText(kh.getMonhoc());
        edt_ngaythi.setText(kh.getNgaythi());
        sp_licHoc.setSelection(0);



    }


    private void initView() {
        edt_kihoc = findViewById(R.id.edt_KiHoc);
        edt_monhoc = findViewById(R.id.edt_MonHoc);
        edt_ngaythi = findViewById(R.id.edt_ngaythi);
        edt_lophoc=findViewById(R.id.edt_class);
        btn_res= findViewById(R.id.btn_res);



        btn_res.setOnClickListener(this);
        edt_ngaythi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogTimer(edt_ngaythi);
            }
        });

    }

    private  void showDialogTimer(EditText date_in){
        Calendar calendar= Calendar.getInstance();
        DatePickerDialog.OnDateSetListener onDateSetListener= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd-MM-yyyy");
                date_in.setText(simpleDateFormat.format(calendar.getTime()));


            }
        };

        new DatePickerDialog(UpdateKHActivity.this,onDateSetListener,calendar.get(Calendar.YEAR)
                ,calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();





    }

    @Override
    public void onClick(View v) {
        updateItem();





    }

    private void updateItem() {

        String monhoc=edt_monhoc.getText().toString().trim();
        String lichHoc = sp_licHoc.getSelectedItem().toString();
        String ngaythi=edt_ngaythi.getText().toString().trim();
        String lopHoc=edt_lophoc.getText().toString().trim();
        String kihoc=edt_kihoc.getText().toString().trim();

        if(TextUtils.isEmpty(monhoc)||TextUtils.isEmpty(lichHoc)||TextUtils.isEmpty(ngaythi)||TextUtils.isEmpty(lopHoc)||TextUtils.isEmpty(kihoc)){
            Toast.makeText(UpdateKHActivity.this,"Vui Lòng Điền Đủ Thong Tin ",Toast.LENGTH_LONG).show();
            return;

        }
        kh.setKihoc(kihoc);
        kh.setMonhoc(monhoc);
        kh.setLichhoc(lichHoc);
        kh.setLopHoc(lopHoc);
        kh.setNgaythi(ngaythi);

        KhoaHocDatabase.getInstance(this).KhoaHocDAO().update(kh);

        Toast.makeText(this, "Cập Nhật Thành Công ", Toast.LENGTH_LONG).show();
        loadActivity();


    }

    private void setSpinerLicHoc() {
        sp_licHoc = findViewById(R.id.spn_lichHoc);
        ArrayList<String> optionLH = new ArrayList<>();
        optionLH.add("Thứ 3 ,Thứ 5 ,Thứ 7 Hàng Tuần");
        optionLH.add("Thứ 2 ,Thứ 4 ,Thứ 6 Hàng Tuần");
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.spiner_list,optionLH);
        adapter.setDropDownViewResource(R.layout.spiner_list);
        sp_licHoc.setAdapter(adapter);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        loadActivity();

    }

    private void loadActivity(){
        Intent intent = new Intent(this, CourseActivity.class);
        startActivity(intent);


    }
}