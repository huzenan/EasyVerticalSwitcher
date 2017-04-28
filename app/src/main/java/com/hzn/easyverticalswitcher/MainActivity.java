package com.hzn.easyverticalswitcher;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private CustomTextSwitcher switcher;
    private boolean auto = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // switcher
        switcher = (CustomTextSwitcher) findViewById(R.id.switcher);
        switcher.setView(R.layout.layout_switcher);
        switcher.setDataList(getData(5));
        switcher.setAutoSwitchDelay(2000);

        // btns
        Button btnRefresh = (Button) findViewById(R.id.btn_refresh);
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "refresh", Toast.LENGTH_SHORT).show();
                switcher.setDataList(getData((int) (Math.random() * 10)));
                switcher.startAutoSwitch();
            }
        });
        Button btnAuto = (Button) findViewById(R.id.btn_auto);
        btnAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!auto)
                    switcher.startAutoSwitch();
                else
                    switcher.stopAutoSwitch();
                auto = !auto;
            }
        });
        Button btnNext = (Button) findViewById(R.id.btn_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switcher.showNextView();
            }
        });
    }

    private ArrayList<Object> getData(int num) {
        ArrayList<Object> dataList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            MyEntity entity = new MyEntity();
            entity.text1 = "text: " + i;
            entity.text2 = "kaka: " + i;
            dataList.add(entity);
        }
        return dataList;
    }
}
