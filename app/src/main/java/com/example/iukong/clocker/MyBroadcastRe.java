package com.example.iukong.clocker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by 和iukong、 on 2017/11/21.
 */

public class MyBroadcastRe extends BroadcastReceiver {
    @Override
    public void onReceive (Context context, Intent intent){
        Toast.makeText(context,"receive in mybroadcastRe",Toast.LENGTH_LONG).show();
    }
}
