package com.example.iukong.clocker;
//要使用v4.app.Fragment这个拓展包
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 和iukong、 on 2017/11/13.
 */

public class QingdanFragment extends Fragment {
    float num[] = {33.3f, 12.3f, 44.3f, 35.1f, 22.2f};
    String sample[] = {"s1","s2","s3","s4","s5"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.qingdan_main, container, false);
        List<PieEntry> pieEntries = new ArrayList<>();
        for(int i = 0 ; i < num.length; i++){
            pieEntries.add(new PieEntry(num[i],sample[i]));
        }

        PieDataSet dataSet = new PieDataSet(pieEntries,"SAMPLE FOR PIECHART");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(dataSet);
        PieChart chart = (PieChart) view.findViewById(R.id.qingdan_chart);
        chart.setData(data);
        chart.animateY(1000);
        chart.invalidate();
        return view;
    }

}
