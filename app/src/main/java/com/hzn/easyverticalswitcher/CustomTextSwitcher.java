package com.hzn.easyverticalswitcher;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 自定义的switcher
 * Created by huzn on 2017/4/28.
 */
public class CustomTextSwitcher extends EasyVerticalSwitcher {

    public CustomTextSwitcher(Context context) {
        super(context);
    }

    public CustomTextSwitcher(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void updateView(Object data, View view) {
        final MyEntity entity = (MyEntity) data;
        TextView tvText1 = (TextView) view.findViewById(R.id.tv_text1);
        TextView tvText2 = (TextView) view.findViewById(R.id.tv_text2);
        tvText1.setText(entity.text1);
        tvText2.setText(entity.text2);
        tvText1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), entity.text1, Toast.LENGTH_SHORT).show();
            }
        });
        tvText2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), entity.text2, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
