package com.abhi41.sortingfilter.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.abhi41.sortingfilter.R;
import com.abhi41.sortingfilter.abstrct.Preferences;
import com.abhi41.sortingfilter.model.Filter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FilterValueAdapter extends RecyclerView.Adapter<FilterValueAdapter.ViewHolder> {
    private Context context;
    private Integer filterIndex;

    public FilterValueAdapter(Context context, int position) {
        this.context = context;
        this.filterIndex = position;
    }

    @NonNull
    @NotNull
    @Override
    public FilterValueAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.filter_value_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull FilterValueAdapter.ViewHolder holder, int position) {

        final Filter tempFilter = Preferences.filters.get(filterIndex);



        holder.value.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> selected = tempFilter.getSelected();
                if (holder.value.isChecked()){
                    selected.add(tempFilter.getValues().get(position));
                    tempFilter.setSelected(selected);
                }else {
                    selected.remove(tempFilter.getValues().get(position));
                    tempFilter.setSelected(selected);
                }
                Preferences.filters.put(filterIndex, tempFilter);
            }
        });


        holder.value.setText(tempFilter.getValues().get(position));

        if (tempFilter.getSelected().contains(tempFilter.getValues().get(position))){
            holder.value.setChecked(true);
        }
    }

    @Override
    public int getItemCount() {

        return Preferences.filters.get(filterIndex).getValues().size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        View container;
        CheckBox value;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            container = itemView;
            value = itemView.findViewById(R.id.value);
        }


    }
}
