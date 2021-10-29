package com.example.assigmentandroidnc.course;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.assigmentandroidnc.R;
import com.example.assigmentandroidnc.course.fragment.CourseFragment;
import com.example.assigmentandroidnc.course.fragment.RegistrationFragment;
import com.example.assigmentandroidnc.database.KhoaHocDatabase;
import com.example.assigmentandroidnc.model.KhoaHoc;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {
    private TextInputEditText edt_kihoc, edt_monhoc, edt_ngaythi, edt_lophoc;
    private Spinner sp_licHoc;
    private Button btn_res;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Log.d("OnCreate : ", "activity_registration");

        setSpinerLicHoc();
        initView();
        setDataKiHoc();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("OnStart: ", "activity_registration");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("OnDestroys: ", "activity_registration");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("OnPause: ", "activity_registration");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
       loadActiviy();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("OnStop: ", "activity_registration");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("onRestart ", "activity_registration");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setDataKiHoc() {
        Intent intent = getIntent();
        String kihoc = intent.getStringExtra("Dataspinner_kihoc");
        edt_kihoc.setText(kihoc);
    }

    @SuppressLint("WrongViewCast")
    private void setSpinerLicHoc() {
        sp_licHoc = findViewById(R.id.spn_lichHoc);
        ArrayList<String> optionLH = new ArrayList<>();
        optionLH.add("Thứ 3 ,Thứ 5 ,Thứ 7 Hàng Tuần");
        optionLH.add("Thứ 2 ,Thứ 4 ,Thứ 6 Hàng Tuần");
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.spiner_list,optionLH);
        adapter.setDropDownViewResource(R.layout.spiner_list);
        sp_licHoc.setAdapter(adapter);


    }



    private void initView() {
        edt_kihoc = findViewById(R.id.edt_KiHoc);
        edt_monhoc = findViewById(R.id.edt_MonHoc);
        edt_ngaythi = findViewById(R.id.edt_ngaythi);
        edt_lophoc=findViewById(R.id.edt_class);
        btn_res= findViewById(R.id.btn_res);
        edt_ngaythi.setInputType(InputType.TYPE_NULL);

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

        new DatePickerDialog(RegistrationActivity.this,onDateSetListener,calendar.get(Calendar.YEAR)
                ,calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();





    }

     //setData vao db
    @Override
    public void onClick(View v) {
        addKhoaHoa();
    }

    private void addKhoaHoa() {
        String monhoc=edt_monhoc.getText().toString().trim();
        String lichHoc = sp_licHoc.getSelectedItem().toString();
        String ngaythi=edt_ngaythi.getText().toString().trim();
        String lopHoc=edt_lophoc.getText().toString().trim();
        Intent intent = getIntent();
        String kihoc = intent.getStringExtra("Dataspinner_kihoc");

        if(TextUtils.isEmpty(monhoc)||TextUtils.isEmpty(lichHoc)||TextUtils.isEmpty(ngaythi)||TextUtils.isEmpty(lopHoc)||TextUtils.isEmpty(kihoc)){
            Toast.makeText(RegistrationActivity.this,"Vui Lòng Điền Đủ Thong Tin ",Toast.LENGTH_LONG).show();
            return;

        }
        KhoaHoc khoaHoc= new KhoaHoc(kihoc,lichHoc,ngaythi,lopHoc,monhoc);
        KhoaHocDatabase.getInstance(this).KhoaHocDAO().insertKhoaHoc(khoaHoc);
        Toast.makeText(this,"Đăng Kí Thành Công",Toast.LENGTH_LONG).show();
        loadActiviy();
    }
    public void loadActiviy(){
        Intent intent= new Intent(RegistrationActivity.this,CourseActivity.class);
        startActivity(intent);
        finish();
    }



}