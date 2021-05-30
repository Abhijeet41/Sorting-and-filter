package com.abhi41.sortingfilter.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abhi41.sortingfilter.R;
import com.abhi41.sortingfilter.model.Item;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MainViewHolder> {

    private Context context;
    private List<Item> itemList;

    public ItemAdapter(Context ctx, List<Item> items) {
        this.context = ctx;
        this.itemList = items;
    }

    @NonNull
    @NotNull
    @Override
    public ItemAdapter.MainViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ItemAdapter.MainViewHolder holder, int position) {

        final Item item = itemList.get(position);

        holder.name.setText(item.getName());
        holder.color.setText("Color: " + item.getColor());
        holder.size.setText("Size: " + (item.getSize()));
        holder.price.setText("Price: " + (item.getPrize()));

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        View container;
        TextView name;
        TextView color;
        TextView size;
        TextView price;

        public MainViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            container = itemView;
            name = itemView.findViewById(R.id.name);
            color = itemView.findViewById(R.id.color);
            size = itemView.findViewById(R.id.size);
            price = itemView.findViewById(R.id.price);
        }
    }
}
