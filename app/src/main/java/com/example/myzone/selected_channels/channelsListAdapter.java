package com.example.myzone.selected_channels;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.view.MotionEventCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myzone.R;
import com.example.myzone.selected_channels.util.ItemTouchHelperAdapter;
import com.example.myzone.selected_channels.util.ItemTouchHelperViewHolder;
import com.example.myzone.selected_channels.util.listener.OnCustomerListChangedListener;
import com.example.myzone.selected_channels.util.listener.OnStartDragListener;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class channelsListAdapter extends
        RecyclerView.Adapter<channelsListAdapter.ItemViewHolder>
        implements ItemTouchHelperAdapter {

    private List<channel> mCustomers;
    private Context mContext;
    private OnStartDragListener mDragStartListener;
    private OnCustomerListChangedListener mListChangedListener;

    public CustomerListAdapter(List<channel> customers, Context context,
                               OnStartDragListener dragLlistener,
                               OnCustomerListChangedListener listChangedListener){
        mCustomers = customers;
        mContext = context;
        mDragStartListener = dragLlistener;
        mListChangedListener = listChangedListener;
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from
                (parent.getContext()).inflate(R.layout.row_customer_list, parent, false);
        ItemViewHolder viewHolder = new ItemViewHolder(rowView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {

        final Customer selectedCustomer = mCustomers.get(position);

        holder.customerName.setText(selectedCustomer.getName());
        holder.customerEmail.setText(selectedCustomer.getEmailAddress());
        Picasso.with(mContext)
                .load(selectedCustomer.getImagePath())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.profileImage);



        holder.handleView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCustomers.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mCustomers, fromPosition, toPosition);
        mListChangedListener.onNoteListChanged(mCustomers);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {

    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder implements
            ItemTouchHelperViewHolder {
        public final TextView customerName, customerEmail;
        public final ImageView handleView, profileImage;


        public ItemViewHolder(View itemView) {
            super(itemView);
            customerName = (TextView)itemView.findViewById(R.id.text_view_customer_name);
            customerEmail = (TextView)itemView.findViewById(R.id.text_view_customer_email);
            handleView = (ImageView)itemView.findViewById(R.id.handle);
            profileImage = (ImageView)itemView.findViewById(R.id.image_view_customer_head_shot);
        }

        @Override
        public void onItemSelected() {
            itemView.setBahttp://valokafor.com/wp-admin/post.php?post=1804&action=edit#ckgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }
}
