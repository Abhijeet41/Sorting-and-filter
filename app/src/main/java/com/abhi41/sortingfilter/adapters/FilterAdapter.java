package com.abhi41.sortingfilter.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abhi41.sortingfilter.R;
import com.abhi41.sortingfilter.model.Filter;

import java.util.HashMap;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.ViewHolder> {
    private Context context;
    private HashMap<Integer, Filter> filters;
    private RecyclerView filterValueRv;

    private int selectedPostion = 0;

    public FilterAdapter(Context context, HashMap<Integer, Filter> filters, RecyclerView filterValueRv) {
        this.context = context;
        this.filters = filters;
        this.filterValueRv = filterValueRv;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public FilterAdapter.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.filter_item, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FilterAdapter.ViewHolder holder, int position) {
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterValueRv.setAdapter(new FilterValueAdapter(context,position));
                selectedPostion = position;
                notifyDataSetChanged();
            }
        });

        filterValueRv.setAdapter(new FilterValueAdapter(context, selectedPostion));
        holder.container.setBackgroundColor(selectedPostion == position ? Color.WHITE : Color.TRANSPARENT);
        holder.title.setText(filters.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return filters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View container;
        TextView title;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            container = itemView;
            title = itemView.findViewById(R.id.title);
        }
    }
}
