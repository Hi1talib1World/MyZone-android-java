package com.example.myzone.selected_channels.util;

import android.content.SharedPreferences;
import android.os.Bundle;

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

    }
}
