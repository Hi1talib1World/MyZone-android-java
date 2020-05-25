package com.example.myzone.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myzone.DayAfterTomorrow;
import com.example.myzone.R;
import com.example.myzone.Tomorrow;
import com.example.myzone.Yesterday;
import com.example.myzone.scarping.ApiService;
import com.example.myzone.scarping.WordsAdapter;

import org.jsoup.helper.StringUtil;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    RecyclerView recyclerView;
    Spinner spinner;


    Yesterday ytr;
    Tomorrow tmr;
    DayAfterTomorrow dayAfterTomorrow;

    HashMap<String, Integer> occurrences = new HashMap<>();
    WordsAdapter wordsAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);



        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl("https://www.journaldev.com/")
                .client(okHttpClient).build();


        final ApiService apiService = retrofit.create(ApiService.class);
        spinner = root.findViewById(R.id.spinner);

        ytr = new Yesterday();
        tmr = new Tomorrow();

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),R.layout.custom_spinner,getResources().getStringArray(R.array.fragments));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                switch (i){
                    case 0:
                        setFragment(ytr);
                        break;
                    case 1:
                        setFragment(tmr);
                        break;
                    case 2:
                        setFragment(dayAfterTomorrow);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        return root;
    }
    private void createHashMap(String responseString) {


        responseString = responseString.replaceAll("[^a-zA-Z0-9]", " ");

        String[] splitWords = responseString.split(" +");

        for (String word : splitWords) {

            if (StringUtil.isNumeric(word)) {
                continue;
            }

            Integer oldCount = occurrences.get(word);
            if (oldCount == null) {
                oldCount = 0;
            }
            occurrences.put(word, oldCount + 1);
        }

        wordsAdapter = new WordsAdapter(getActivity(), occurrences);
        recyclerView.setAdapter(wordsAdapter);
    }

    public static HashMap<String, Integer> sortByValueDesc(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        HashMap<String, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public void setFragment (Fragment fragment){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame , fragment);
        fragmentTransaction.commit();
    }
}