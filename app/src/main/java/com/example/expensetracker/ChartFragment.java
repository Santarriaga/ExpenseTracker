package com.example.expensetracker;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.Locale;


public class ChartFragment extends Fragment {

    private PieChart pieChart;
    private TextView food,income, travel, pet, utility, entertainment, gym, rent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chart_fragment, container, false);

        initViews(view);
        pieChart = (PieChart) view.findViewById(R.id.piechart);
        setUpPieChart();

        return view;
    }

    public void setUpPieChart(){

        int percent1 = (int) ((Utils.getFoodTotal() / Utils.getTotal()) *100);
        int percent2 = (int) ((Utils.getTotalIncome() / Utils.getTotal()) *100);
        int percent3 = (int) ((Utils.getTravelTotal() / Utils.getTotal()) *100);
        int percent4 = (int) ((Utils.getPetTotal() / Utils.getTotal()) *100);
        int percent5 = (int) ((Utils.getUtilityTotal() / Utils.getTotal()) *100);
        int percent6 = (int) ((Utils.getEntertainmentTotal() / Utils.getTotal()) *100);
        int percent7 = (int) ((Utils.getGymTotal() / Utils.getTotal()) *100);
        int percent8 = (int) ((Utils.getRent() / Utils.getTotal()) *100);

        food.setText(Integer.toString(percent1) + "%");
        income.setText(Integer.toString(percent2) + "%");
        travel.setText(Integer.toString(percent3) + "%");
        pet.setText(Integer.toString(percent4) + "%");
        utility.setText(Integer.toString(percent5) + "%");
        entertainment.setText(Integer.toString(percent6) + "%");
        gym.setText(Integer.toString(percent7) + "%");
        rent.setText(Integer.toString(percent8) + "%");


        pieChart.addPieSlice(new PieModel("Food", percent1, Color.parseColor("#FF14D4D4")));
        pieChart.addPieSlice(new PieModel("Income", percent2, Color.parseColor("#32d919")));
        pieChart.addPieSlice(new PieModel("Travel", percent3, Color.parseColor("#FFEB0414")));
        pieChart.addPieSlice(new PieModel("Pet",percent4, Color.parseColor("#FF1717D6")));
        pieChart.addPieSlice(new PieModel("Utility",percent5, Color.parseColor("#FFE2F908")));
        pieChart.addPieSlice(new PieModel("Entertainment",percent6, Color.parseColor("#FFF67D04")));
        pieChart.addPieSlice(new PieModel("gym",percent7, Color.parseColor("#FF6504F6")));
        pieChart.addPieSlice(new PieModel("rent",percent8, Color.parseColor("#FFFF3AF9")));

        pieChart.startAnimation();
    }

    private void initViews(View view){

        food = view.findViewById(R.id.txtFood);
        income = view.findViewById(R.id.txtIncrease);
        travel = view.findViewById(R.id.txtTravel);
        pet = view.findViewById(R.id.txtPet);
        utility = view.findViewById(R.id.txtUtility);
        entertainment = view.findViewById(R.id.txtEntertainment);
        gym = view.findViewById(R.id.txtGym);
        rent = view.findViewById(R.id.txtRent);
    }

}
