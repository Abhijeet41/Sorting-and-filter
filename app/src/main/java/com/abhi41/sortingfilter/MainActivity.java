package com.abhi41.sortingfilter;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.abhi41.sortingfilter.abstrct.Preferences;
import com.abhi41.sortingfilter.adapters.ItemAdapter;
import com.abhi41.sortingfilter.databinding.ActivityMainBinding;
import com.abhi41.sortingfilter.model.Filter;
import com.abhi41.sortingfilter.model.Item;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;
    private List<Item> items = new ArrayList<>();
    private ItemAdapter itemAdapter;

    boolean isCheckedname = false;
    boolean isCheckedSize = false;
    boolean isCheckedPrice = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FilterActivity.class));
            }
        });

        binding.btnSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this,
                        R.style.BottomSheetDialogThme);
                bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog_sort_items);
                bottomSheetDialog.onSaveInstanceState();


                CheckBox chkname = bottomSheetDialog.findViewById(R.id.chkname);
                CheckBox chkSize = bottomSheetDialog.findViewById(R.id.chkSize);
                CheckBox chkprice = bottomSheetDialog.findViewById(R.id.chkprice);

                if (isCheckedname) {
                    chkname.setChecked(true);
                } else {
                    chkname.setChecked(false);
                }

                if (isCheckedSize) {
                    chkSize.setChecked(true);
                } else {
                    chkSize.setChecked(false);
                }

                if (isCheckedPrice) {
                    chkprice.setChecked(true);
                } else {
                    chkprice.setChecked(false);
                }

                chkname.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onClick(View v) {
                        if (isCheckedname) {
                            isCheckedname = false;
                        } else {
                            isCheckedname = true;
                        }

                        if (isCheckedname) {
                            items.sort(Item.nameComparator);
                            itemAdapter.notifyDataSetChanged();
                        } else {
                            items.clear();
                            loadFilterItem();
                        }
                    }
                });

                chkSize.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onClick(View v) {
                        if (isCheckedSize) {
                            isCheckedSize = false;
                        } else {
                            isCheckedSize = true;
                        }

                        if (isCheckedSize) {
                            items.sort(Item.sizeComparator);
                            itemAdapter.notifyDataSetChanged();
                        } else {
                            items.clear();
                            loadFilterItem();
                        }

                    }
                });

                chkprice.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onClick(View v) {
                        if (isCheckedPrice) {
                            isCheckedPrice = false;
                        } else {
                            isCheckedPrice = true;
                        }

                        if (isCheckedPrice) {
                            items.sort(Item.priceComparator);
                            itemAdapter.notifyDataSetChanged();
                        } else {
                            items.clear();
                            loadFilterItem();
                        }

                    }
                });

                bottomSheetDialog.show();
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        loadFilterItem();
    }

    private void loadFilterItem() {

        items.clear();
        items.add(new Item("Item 1", "Red", 10, 100.00));
        items.add(new Item("Item 2", "Red", 12, 100.00));
        items.add(new Item("Item 3", "Red", 14, 100.00));
        items.add(new Item("Item 4", "Red", 16, 150.00));
        items.add(new Item("Item 5", "Red", 18, 170.00));
        items.add(new Item("Item 6", "Green", 20, 190.00));
        items.add(new Item("Item 7", "Green", 10, 100.00));
        items.add(new Item("Item 8", "Green", 12, 200.00));
        items.add(new Item("Item 9", "Green", 14, 210.00));
        items.add(new Item("Item 10", "Green", 16, 240.00));
        items.add(new Item("Item 11", "Blue", 18, 250.00));
        items.add(new Item("Item 12", "Blue", 20, 280.00));
        items.add(new Item("Item 13", "Blue", 10, 300.00));
        items.add(new Item("Item 14", "Blue", 12, 150.00));
        items.add(new Item("Item 15", "White", 10, 170.00));


        if (!Preferences.filters.isEmpty()) {
            ArrayList<Item> filteredItems = new ArrayList<Item>();
            List<String> colors = Preferences.filters.get(Filter.INDEX_COLOR).getSelected();
            List<String> sizes = Preferences.filters.get(Filter.INDEX_SIZE).getSelected();
            List<String> prices = Preferences.filters.get(Filter.INDEX_PRICE).getSelected();

            for (Item item : items) {
                boolean colorMatched = true;
                boolean sizeMatched = true;
                boolean priceMatched = true;

                if (colors.size() > 0 && !colors.contains(item.getColor())) {
                    colorMatched = false;
                } else if (sizes.size() > 0 && !sizes.contains(String.valueOf(item.getSize()))) {
                    sizeMatched = false;
                } else if (prices.size() > 0 && !priceContains(prices, item.getPrize())) {
                    priceMatched = false;
                }

                if (colorMatched && sizeMatched && priceMatched) {

                    filteredItems.add(item);
                }

            }
            items.clear();
            items.addAll(filteredItems);
            filteredItems.clear();
        }


        itemAdapter = new ItemAdapter(getApplicationContext(), items);
        binding.recyclerMain.setAdapter(itemAdapter);
        itemAdapter.notifyDataSetChanged();
    }

    private boolean priceContains(List<String> prices, Double prize) {
        boolean flag = false;

        for (String s : prices) {
            String[] tempPrices = s.split("-");

            if (prize >= Double.valueOf(tempPrices[0]) && prize <= Double.valueOf(tempPrices[1])) {

                Log.d(TAG, "priceContains: " + Double.valueOf(tempPrices[0]));
                Log.d(TAG, "priceContains2: " + Double.valueOf(tempPrices[1]));
                Log.d(TAG, "prize: " + prize);
                flag = true;
                break;
            }
        }
        return flag;

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        items.clear();
        Preferences.filters.clear();
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}