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
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.List;

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

    @Override
    public void onNoteListChanged(List<channel> customers) {

    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {

    }
}
