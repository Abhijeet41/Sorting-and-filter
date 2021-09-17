package com.abhi41.sortingfilter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.abhi41.sortingfilter.abstrct.Preferences;
import com.abhi41.sortingfilter.adapters.FilterAdapter;

import com.abhi41.sortingfilter.databinding.ActivityFilterBinding;
import com.abhi41.sortingfilter.model.Filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterActivity extends AppCompatActivity {

    private static final String TAG = "FilterActivity";
    ActivityFilterBinding binding;
    FilterAdapter filterAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_filter);

        intitControls();

    }

    private void intitControls() {

        List<String> colors = Arrays.asList(new String[]{"Red", "Green", "Blue", "White"});

        if (!Preferences.filters.containsKey(Filter.INDEX_COLOR)) {
            Preferences.filters.put(Filter.INDEX_COLOR, new Filter("Color", colors, new ArrayList<>()));
        }

        List<String> sizes = Arrays.asList(new String[]{"10", "12", "14", "16", "18", "20"});

        if (!Preferences.filters.containsKey(Filter.INDEX_SIZE)) {
            Preferences.filters.put(Filter.INDEX_SIZE, new Filter("Size", sizes, new ArrayList<>()));
        }

        List<String> prices = Arrays.asList(new String[]{"0-100", "101-200", "201-300"});

        if (!Preferences.filters.containsKey(Filter.INDEX_PRICE)) {
            Preferences.filters.put(Filter.INDEX_PRICE, new Filter("Price", prices, new ArrayList<>()));
        }

        filterAdapter = new FilterAdapter(getApplicationContext(),Preferences.filters,binding.filtervalueRv);
        binding.filterKeyRv.setAdapter(filterAdapter);

        binding.btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FilterActivity.this, MainActivity.class));
            }
        });
        binding.btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Preferences.filters.clear();
                //recreate();
                finish();
                startActivity(getIntent());
                overridePendingTransition(0, 0);

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Preferences.filters.clear();
        finish();

    }
}