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

import java.nio.channels.Channel;
import java.util.Collections;
import java.util.List;

public class channelsListAdapter extends
        RecyclerView.Adapter<channelsListAdapter.ItemViewHolder>
        implements ItemTouchHelperAdapter {

    private List<channel> mchannel;
    private Context mContext;
    private OnStartDragListener mDragStartListener;
    private OnCustomerListChangedListener mListChangedListener;

    public channelsListAdapter(List<channel> channel, Context context,
                               OnStartDragListener dragLlistener,
                               OnCustomerListChangedListener listChangedListener){
        mchannel = channel;
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

        final Channel selectedChannel = (Channel) mchannel.get(position);

        holder.channelName.setText(selectedChannel.getName());
        holder.channelEmail.setText(selectedChannel.getEmailAddress());
        Picasso.with(mContext)
                .load(selectedChannel.getImagePath())
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
        return mchannel.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mchannel, fromPosition, toPosition);
        mListChangedListener.onNoteListChanged(mchannel);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {

    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder implements
            ItemTouchHelperViewHolder {
        public final TextView channelName, channelEmail;
        public final ImageView handleView, profileImage;


        public ItemViewHolder(View itemView) {
            super(itemView);
            channelName = (TextView)itemView.findViewById(R.id.text_view_customer_name);
            channelEmail = (TextView)itemView.findViewById(R.id.text_view_customer_email);
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
