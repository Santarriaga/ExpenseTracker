package com.example.expensetracker;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Locale;

public class MainFragment extends Fragment implements InvoiceAdapter.DeleteInvoice{

    @Override
    public void onDeletingResult(int invoiceId) {
        Log.d(TAG,"MainFragment: removing invoice");
        try {
            databaseHelper.delete(database, invoiceId);
            DatabaseAsyncTask databaseTask = new DatabaseAsyncTask();
            databaseTask.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    private static final String TAG = "MainFragment";

    private TextView txtIncome;
    private TextView txtExpense;
    private TextView txtCurrentBalance;
    private RecyclerView recyclerView;
    private InvoiceAdapter adapter;

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;
    private Cursor cursor;
    private ArrayList<Invoice> invoices;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        initViews(view);

        //Recycler view
//        adapter = new InvoiceAdapter(getActivity());
        adapter = new InvoiceAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter.setInvoice(invoices);

        DatabaseAsyncTask  databaseTask = new DatabaseAsyncTask();
        databaseTask.execute();

        return view;
    }

    //handle the database stuff
    private class DatabaseAsyncTask extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            adapter.clearAdapter();
            databaseHelper = new DatabaseHelper(getActivity());
            database = databaseHelper.getReadableDatabase();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                cursor = database.query("invoices",null,null,null,null,null,null,null);
                if(cursor.moveToFirst()){
                    for(int i = 0; i < cursor.getCount(); i++){
                        Invoice invoice = new Invoice();
                        for(int j = 0; j < cursor.getColumnCount();j++){
                            switch (cursor.getColumnName(j)){
                                case "id":
                                    invoice.setId(cursor.getInt(j));
                                    break;
                                case "title":
                                    invoice.setTitle(cursor.getString(j));
                                    break;
                                case "date":
                                    invoice.setDate(cursor.getString(j));
                                    break;
                                case "value":
                                    invoice.setValue(cursor.getString(j));
                                    break;
                                case "iconName":
                                    invoice.setIconName(cursor.getString(j));
                                    break;
                                case "isExpense":
                                    int isExpense = cursor.getInt(j);
                                    if(isExpense == 1){
                                        invoice.setExpense(true);
                                    }
                                    else{
                                        invoice.setExpense(false);
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                        invoices.add(invoice);
                        cursor.moveToNext();
                    }
                }
            }catch (SQLException e){
                e.printStackTrace();
            }

            //display income and balance
            Utils.initTotals();
            for(Invoice i: invoices){
                if(i.isExpense()){
                    addExpenseToCategory(i);
                    Utils.addTotal(Double.parseDouble(i.getValue()));
                    Utils.addTotalExpense(Double.parseDouble(i.getValue()));
                    Utils.addTotalBalance(-1 *  Double.parseDouble(i.getValue()));
                }
                else{
                    addExpenseToCategory(i);
                    Utils.addTotal(Double.parseDouble(i.getValue()));
                    Utils.addTotalIncome(Double.parseDouble(i.getValue()));
                    Utils.addTotalBalance(Double.parseDouble(i.getValue()));
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.d(TAG,"MainFragment: finished async task");
            adapter.setInvoice(invoices);
            txtIncome.setText(String.format(Locale.getDefault(),"%.2f",Utils.getTotalIncome()));
            txtExpense.setText(String.format(Locale.getDefault(),"%.2f", Utils.getTotalExpense()));
            txtCurrentBalance.setText(String.format(Locale.getDefault(),"%.2f", Utils.getTotalBalance()));
        }
    }

    private void addExpenseToCategory(Invoice invoice){
        switch (invoice.getIconName()){
            case "ic_food":
                Utils.addFoodTotal(Double.parseDouble(invoice.getValue()));
                break;
            case "ic_increase":
                Utils.addIncomeTotal(Double.parseDouble(invoice.getValue()));
                break;
            case "ic_travel":
                Utils.addTravelTotal(Double.parseDouble(invoice.getValue()));
                break;
            case "ic_pet":
                Utils.addPetTotal(Double.parseDouble(invoice.getValue()));
                break;
            case "ic_utility":
                Utils.addUtilityTotal(Double.parseDouble(invoice.getValue()));
                break;
            case "ic_entertainment":
                Utils.addEntertainmentTotal(Double.parseDouble(invoice.getValue()));
                break;
            case "ic_gym":
                Utils.addGymTotal(Double.parseDouble(invoice.getValue()));
                break;
            case "ic_rent":
                Utils.addRent(Double.parseDouble(invoice.getValue()));
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cursor.close();
        database.close();
    }

    private void initViews(View view){
        recyclerView = view.findViewById(R.id.recViewItems);
        txtExpense = view.findViewById(R.id.Expense);
        txtIncome = view.findViewById(R.id.income);
        txtCurrentBalance = view.findViewById((R.id.txtBalance));

        invoices = new ArrayList<>();
    }



}
