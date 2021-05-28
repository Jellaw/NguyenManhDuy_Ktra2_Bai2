package com.example.nguyenmanhduy_ktra2_bai2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText ten, ngaythi,giothi;
    RadioGroup kieuthi;
    RadioButton viet, khongviet;
    Button them,sua,xoa,listMonthiViet,getAll,tim;
    private RecyclerView recyclerView;
    private Adapter adapter;
    private SQLiteController sqLite;
    String kieuthiString;
    public int tongsomon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sqLite = new SQLiteController(this);
        ArrayList<Lichthi> list = sqLite.getAllLichthi();
        adapter = new Adapter(list,MainActivity.this);
        recyclerView.setAdapter(adapter);

        them.setOnClickListener(v -> {
            String name = ten.getText().toString();
            String day = ngaythi.getText().toString();
            String hour = giothi.getText().toString();
            getInforFromRadioButton();

            try {
                Lichthi lichthi = new Lichthi(name, day, hour, kieuthiString);
                sqLite.addlichthi(lichthi);
                Toast.makeText(getApplicationContext(),"Them thanh cong lich thi!",Toast.LENGTH_LONG).show();
            } catch (NumberFormatException e){
                System.out.println(e);
            }
            ArrayList<Lichthi> arrLichthi = sqLite.getAllLichthi();
            adapter = new Adapter(arrLichthi,MainActivity.this);
            recyclerView.setAdapter(adapter);
        });


        sua.setOnClickListener(v -> {
            String name = ten.getText().toString();
            String day = ngaythi.getText().toString();
            String hour = giothi.getText().toString();
            getInforFromRadioButton();
            try {

                Lichthi lichthi = new Lichthi(name, day, hour, kieuthiString);
                sqLite.updateLichthi(lichthi);
                Toast.makeText(getApplicationContext(),"Da update lich thi!",Toast.LENGTH_LONG).show();
            } catch (NumberFormatException e){
                System.out.println(e);
            }
            ArrayList<Lichthi> list1 = sqLite.getAllLichthi();
            adapter = new Adapter(list1,MainActivity.this);
            recyclerView.setAdapter(adapter);
        });

        getAll.setOnClickListener(v -> {
            ArrayList<Lichthi> list3 = sqLite.getAllLichthi();
            adapter = new Adapter(list3,MainActivity.this);
            recyclerView.setAdapter(adapter);
        });

//

        tim.setOnClickListener(v -> {
            String name = ten.getText().toString();
            Lichthi lichthi = sqLite.searchLichthiByName(name);
            if (lichthi == null) {
                Toast.makeText(getApplicationContext(), "Nhap chinhh xac ten cua mon thi!", Toast.LENGTH_LONG).show();
            } else {
                ten.setText(lichthi.getTenmonhoc());
                ngaythi.setText(lichthi.getNgaythi() + "");
                giothi.setText(lichthi.getGiothi() + lichthi.getKieuthi());
            }
            ArrayList<Lichthi> list12 = new ArrayList<>();
            list12.add(sqLite.searchLichthiByName(name));
            adapter = new Adapter(list12, MainActivity.this);
            recyclerView.setAdapter(adapter);
        });
        listMonthiViet.setOnClickListener(view -> {
            ArrayList<Lichthi> list5 = sqLite.getAllLichthi();
            adapter = new Adapter(list5,MainActivity.this);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(), "Tổng số môn thi viết: "+tongsomon, Toast.LENGTH_LONG).show();
        });
    }


    @Override
    protected void onDestroy() {
        sqLite.close();
        super.onDestroy();
    }
    private void getInforFromRadioButton(){
        int genid=kieuthi.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(genid);
        kieuthiString=radioButton.getText().toString();

    }
    private void init(){
        ten=findViewById(R.id.editTenmon);
        ngaythi=findViewById(R.id.editNgaythi);
        giothi=findViewById(R.id.editGiothi);
        kieuthi=findViewById(R.id.kieuthi);
        viet=findViewById(R.id.viet);
        khongviet=findViewById(R.id.khongviet);
        them=findViewById(R.id.add);
        sua=findViewById(R.id.update);
        xoa=findViewById(R.id.delete);
        listMonthiViet=findViewById(R.id.list);
        getAll=findViewById(R.id.getAll);
        tim=findViewById(R.id.find);
        recyclerView=findViewById(R.id.rv_lichthi);
    }
}