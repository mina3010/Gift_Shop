package com.example.giftstore.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.giftstore.UI.AddCategories;
import com.example.giftstore.Data.Database;
import com.example.giftstore.UI.MainActivity;
import com.example.giftstore.R;
import com.example.giftstore.Model.Store;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterStore extends RecyclerView.Adapter<AdapterStore.ViewHolder> {
    private Context context;
    private Database db;
    int _id;
    double total_all;
    private List<Store> storeList=new ArrayList<>();

    public AdapterStore(List<Store> storeList) {
        this.storeList = storeList;
    }

    public AdapterStore(Context context, List<Store> storeList) {
        this.context = context;
        this.storeList = storeList;
    }

    @Override
    public AdapterStore.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_item, parent, false);
        return new AdapterStore.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterStore.ViewHolder holder, int position) {
        holder.id.setText(String.valueOf(position+1));
        holder.name.setText(storeList.get(position).getCategoryName());
        holder.unit.setText(String.valueOf(storeList.get(position).getUnit()));
        holder.group.setText(String.valueOf(storeList.get(position).getGroup()));
        holder.unitPrice.setText(String.valueOf(storeList.get(position).getUnitPrice()));
        holder.totalPrice.setText(String.valueOf( storeList.get(position).getTotalPrice()));
        holder.Time.setText(String.valueOf( storeList.get(position).getTimesTamp()));

        holder.setData(position);

    }

    @Override
    public int getItemCount() {
        return storeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id , name , unit , group , unitPrice, totalPrice ,Time;
        private Dialog editDialog;
        private Button mol7zat,edit , delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.storeID);
            name = itemView.findViewById(R.id.nameCategory);
            unit = itemView.findViewById(R.id.unit);
            group = itemView.findViewById(R.id.group);
            unitPrice = itemView.findViewById(R.id.unitPrice);
            totalPrice = itemView.findViewById(R.id.totalPrice);
            Time = itemView.findViewById(R.id.time);

            editDialog=new Dialog(itemView.getContext());
            editDialog.setContentView(R.layout.edit_category);
            editDialog.setCancelable(true);
            editDialog.getWindow().setBackgroundDrawableResource(R.drawable.progress_background);
            editDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);

            edit=editDialog.findViewById(R.id.edit);
            delete=editDialog.findViewById(R.id.delete);


        }

        public void setData(final int position) {
            db = new Database(context);

            total_all+=storeList.get(position).getTotalPrice();
            MainActivity.totalAll_tv.setText(String.valueOf(total_all));

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    editDialog.show();
                    edit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent in = new Intent(context, AddCategories.class);
                            in.putExtra(MainActivity.CATEGORY_KEY, storeList.get(position).getId());
                            in.putExtra("id", storeList.get(position).getId());
                            in.putExtra("name", storeList.get(position).getCategoryName());
                            in.putExtra("unit", storeList.get(position).getUnit());
                            in.putExtra("group", storeList.get(position).getGroup());
                            in.putExtra("unitPrice", storeList.get(position).getUnitPrice());
                            context.startActivity(in);
                            notifyDataSetChanged();
                            editDialog.dismiss();

                        }
                    });

                    return false;
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    _id =storeList.get(position).getId();
                    Store store = new Store(_id);
                    db.delete(store);
                    //to refresh data dynamic
                    storeList.remove(position);
                    notifyItemRemoved(position);
                    MainActivity.totalAll_tv.setText(String.valueOf(total_all));
                    Toast.makeText(context, "تم مسح العنصر بنجاح", Toast.LENGTH_SHORT).show();
                    editDialog.dismiss();

                }
            });
        }
    }
}
