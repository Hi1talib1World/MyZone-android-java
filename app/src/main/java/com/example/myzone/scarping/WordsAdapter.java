package com.example.myzone.scarping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myzone.R;

import java.util.HashMap;

public class WordsAdapter extends RecyclerView.Adapter<WordsAdapter.WordsHolder> {

    HashMap<String, Integer> modelList;
    Context mContext;
    private String[] mKeys;

    class WordsHolder extends RecyclerView.ViewHolder {


        TextView txtWord;
        TextView txtCount;

        public WordsHolder(View itemView) {
            super(itemView);


            txtWord = itemView.findViewById(R.id.txtWord);
            txtCount = itemView.findViewById(R.id.txtCount);
        }
    }

    public WordsAdapter(Context context, HashMap<String, Integer> modelList) {
        this.modelList = modelList;
        mContext = context;
        mKeys = modelList.keySet().toArray(new String[modelList.size()]);
    }

    @NonNull
    @Override
    public WordsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_words, parent, false);
        return new WordsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordsHolder holder, int position) {
        holder.txtWord.setText(mKeys[position]);
        holder.txtCount.setText(String.valueOf(modelList.get(mKeys[position])));
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }


}
