package com.example.myzone.selected_channels;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myzone.R;
import com.example.myzone.selected_channels.util.SimpleItemTouchHelperCallback;
import com.example.myzone.selected_channels.util.listener.OnCustomerListChangedListener;
import com.example.myzone.selected_channels.util.listener.OnStartDragListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import static com.example.myzone.selected_channels.util.SharedPreference.LIST_OF_SORTED_DATA_ID;

public class guidelist extends AppCompatActivity implements OnCustomerListChangedListener,
        OnStartDragListener {

    private RecyclerView mRecyclerView;
    private channelsListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ItemTouchHelper mItemTouchHelper;
    private List<channel> mCustomers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guidelist);
        setupRecyclerView();
    }

    private void setupRecyclerView(){
        mRecyclerView = (RecyclerView) findViewById(R.id.note_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mCustomers = SampleData.addSampleCustomers();

        //setup the adapter with empty list
        mAdapter = new channelsListAdapter(mCustomers, this, this, this);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                .colorResId(R.color.colorPrimaryDark)
                .size(2)
                .build());
        mRecyclerView.setAdapter(mAdapter);
    }


    private List<channel> getSampleData(){

        //Get the sample data
        List<channel> customerList = SampleData.addSampleCustomers();

        //create an empty array to hold the list of sorted Customers
        List<channel> sortedCustomers = new ArrayList<channel>();

        //get the JSON array of the ordered of sorted customers
        String jsonListOfSortedCustomerId = mSharedPreferences.getString(LIST_OF_SORTED_DATA_ID, "");

        //check for null
        if (!jsonListOfSortedCustomerId.isEmpty()){

            //convert JSON array into a List<Long>
            Gson gson = new Gson();
            List<Long> listOfSortedCustomersId = gson.fromJson
                    (jsonListOfSortedCustomerId, new TypeToken<List<Long>>(){}.getType());

            //build sorted list
            if (listOfSortedCustomersId != null && listOfSortedCustomersId.size() > 0){
                for (Long id: listOfSortedCustomersId){
                    for (channel channel: customerList){
                        if (channel.getId().equals(id)){
                            sortedCustomers.add(channel);
                            customerList.remove(channel);
                            break;
                        }
                    }
                }
            }

            //if there are still customers that were not in the sorted list
            //maybe they were added after the last drag and drop
            //add them to the sorted list
            if (customerList.size() > 0){
                sortedCustomers.addAll(customerList);
            }

            return sortedCustomers;
        }else {
            return customerList;
        }
    }

    @Override
    public void onNoteListChanged(List<channel> customers) {

    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {

    }
}
