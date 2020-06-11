package com.example.myzone.selected_channels.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.myzone.selected_channels.channel;
import com.google.gson.Gson;

import java.nio.channels.Channel;
import java.util.ArrayList;
import java.util.List;

/*
At this point, your drag and drop list should be working and we now want to
 remember the position of the list items after they have been re-organized.
 Like I mentioned at the beginning of the post, this is accomplished by saving
  the ids of the list items to SharedPreference so go ahead and add
  the following class members to the top of the file.


 */
public class SharedPreference  {
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    public static final String LIST_OF_SORTED_DATA_ID = "json_list_sorted_data_id";
    public final static String PREFERENCE_FILE = "preference_file";

    public void onCreat(Bundle savedInstanceState){
        mSharedPreferences = this.getApplicationContext()
                .getSharedPreferences(PREFERENCE_FILE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }
    @Override
    public void onNoteListChanged(List<channel> customers) {
        //after drag and drop operation, the new list of Customers is passed in here

        //create a List of Long to hold the Ids of the
        //Customers in the List
        List<Long> listOfSortedCustomerId = new ArrayList<Long>();

        for (Channel channel: customers){
            listOfSortedCustomerId.add(channel.getId());
        }

        //convert the List of Longs to a JSON string
        Gson gson = new Gson();
        String jsonListOfSortedCustomerIds = gson.toJson(listOfSortedCustomerId);


        //save to SharedPreference
        mEditor.putString(LIST_OF_SORTED_DATA_ID, jsonListOfSortedCustomerIds).commit();
        mEditor.commit();
    }
}
