package com.example.expensetracker;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


//Adapter for recycler view of invoice items
public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.ViewHolder>{
    private static final String TAG = "InvoiceAdapter";

    //handle deletion of item
    public interface DeleteInvoice{
        void onDeletingResult(int invoiceId);
    }

    private DeleteInvoice deleteInvoice;
    private Fragment fragment;

    private ArrayList<Invoice> invoices = new ArrayList<>();
    private Context context;
    
    //constructor
    public InvoiceAdapter(Context context){
        this.context = context;
    }

    public InvoiceAdapter(Fragment fragment){
        this.fragment = fragment;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.invoice_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(invoices.get(position).getTitle());

        if(invoices.get(position).isExpense()){
            holder.value.setText(" - " + invoices.get(position).getValue());
            holder.value.setTextColor(Color.parseColor("#E60A0A"));
        }
        else{
            holder.value.setText(" + " + invoices.get(position).getValue());
            holder.value.setTextColor(Color.parseColor("#FF0AE615"));
        }
        holder.date.setText(invoices.get(position).getDate());

        Context context = fragment.getActivity();
        Glide.with(context)
            .load(context.getResources().getIdentifier(invoices.get(position).getIconName(),"drawable", context.getPackageName() ))
            .into(holder.icon);

        holder.parent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder  = new AlertDialog.Builder(context)
                        .setTitle("Deleting Item")
                        .setMessage("Are you sure you want to delete " + invoices.get(position).getTitle() + "?" )
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                try {
                                   deleteInvoice = (DeleteInvoice) fragment;
                                   deleteInvoice.onDeletingResult(invoices.get(position).getId());
                                }catch (ClassCastException e){
                                    e.printStackTrace();
                                }

                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                builder.create().show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return invoices.size();
    }

    public void setInvoice(ArrayList<Invoice> invoices){
        this.invoices = invoices;
        notifyDataSetChanged();
    }

    public void clearAdapter(){
        this.invoices.clear();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView date;
        private TextView value;
        private ImageView icon;
        private CardView parent;
        
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.invoiceTitle);
            date = itemView.findViewById(R.id.txtDate);
            value = itemView.findViewById(R.id.txtValue);
            icon = itemView.findViewById(R.id.icon);
            parent = itemView.findViewById(R.id.parent);
        }
    }
}
