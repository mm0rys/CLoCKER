package com.example.iukong.clocker;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 和iukong、 on 2017/11/13.
 */

public class YingdiFragment extends Fragment {

    private int recLen = 0;
    private TextView txtView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View RootView = inflater.inflate(R.layout.yingdi_main, container, false);

        Button btn = (Button) RootView.findViewById(R.id.button_vibrate);
        txtView = (TextView) RootView.findViewById(R.id.txttime);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Test","震动被点击");
                Toast.makeText(getActivity(),"开始计时",Toast.LENGTH_SHORT).show();
                handler.postDelayed(runnable, 1000);
            }
        });


        return RootView;
    }
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            recLen++;
            txtView.setText(""+recLen);
            handler.postDelayed(this, 1000);
        }
    };
}
