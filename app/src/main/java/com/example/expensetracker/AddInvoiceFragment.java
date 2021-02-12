package com.example.expensetracker;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddInvoiceFragment extends Fragment {
    private static final String TAG ="AddInvoiceFragment";


    private EditText edtExpenseTitle, edtAmount;
    private TextView txtWarning;
    private Button btnAddInvoice;
    private RadioGroup rgIsExpense;
    private ImageView food, increase, travel, pet, utility, entertainment, gym, rent;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_invoice_fragment, container, false);

        initViews(view);
        Invoice invoice = new Invoice();
        //set default icon
        invoice.setIconName("ic_entertainment");
        invoice.setExpense(true);

        rgIsExpense.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rbIncome:
                        invoice.setExpense(false);
                        break;
                    case R.id.rbExpense:
                        invoice.setExpense(true);
                        break;
                    default:
                        break;
                }
            }
        });

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Food Category Selected", Toast.LENGTH_SHORT).show();
                invoice.setIconName("ic_food");
            }
        });

        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Income Category Selected", Toast.LENGTH_SHORT).show();
                invoice.setIconName("ic_increase");
            }
        });

        travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Travel Category Selected", Toast.LENGTH_SHORT).show();
                invoice.setIconName("ic_travel");
            }
        });

        pet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Pets Category Selected", Toast.LENGTH_SHORT).show();
                invoice.setIconName("ic_pet");
            }
        });

        utility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Utilty Category Selected", Toast.LENGTH_SHORT).show();
                invoice.setIconName("ic_utility");
            }
        });

        entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "entertainment Category Selected", Toast.LENGTH_SHORT).show();
                invoice.setIconName("ic_entertainment");
            }
        });

        gym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Gym Category Selected", Toast.LENGTH_SHORT).show();
                invoice.setIconName("ic_gym");
            }
        });

        rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Rent Category Selected", Toast.LENGTH_SHORT).show();
                invoice.setIconName("ic_rent");
            }
        });

        btnAddInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateData()){

                    invoice.setTitle(edtExpenseTitle.getText().toString());
                    invoice.setValue(edtAmount.getText().toString());

                    //get date
                    SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d", Locale.getDefault());
                    String date = sdf.format(new Date());
                    invoice.setDate(date);

                    addNewInvoice(invoice);

                    //navigate back to home fragment
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.container, new MainFragment());
                    transaction.commit();
                }
            }
        });

        return view;
    }

    private void addNewInvoice(Invoice invoice){
        Log.d(TAG,"AddInvoiceFragment: attempting to add new invoice");
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        SQLiteDatabase database;
        database = databaseHelper.getWritableDatabase();

        try{
            databaseHelper.insert(database, invoice);
        }catch (SQLException e){
            e.printStackTrace();
        }

        database.close();
    }

    //Check that all field are filed out
    private boolean validateData(){
       if(edtAmount.getText().toString().equals("") || edtExpenseTitle.getText().toString().equals("")){
           txtWarning.setVisibility(View.VISIBLE);
           return false;
       }
       return true;
    }

    private void initViews(View view){
        edtExpenseTitle = view.findViewById(R.id.edtExpenseTitle);
        edtAmount = view.findViewById(R.id.edtAmount);
        txtWarning = view.findViewById(R.id.txtWarning);
        btnAddInvoice = view.findViewById(R.id.btnAddInvoice);
        rgIsExpense = view.findViewById(R.id.rgIsExpense);
        rgIsExpense.check(R.id.rbExpense);

        food = view.findViewById(R.id.food);
        increase = view.findViewById(R.id.increase);
        travel = view.findViewById(R.id.travel);
        pet = view.findViewById(R.id.pet);
        utility = view.findViewById(R.id.utility);
        entertainment = view.findViewById(R.id.entertainment);
        gym = view.findViewById(R.id.gym);
        rent = view.findViewById(R.id.rent);
    }


}
