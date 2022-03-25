package com.example.fragmentcommunication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

public class MasterFragment extends ListFragment {
    private OnMasterSelectedListener mOnMasterSelectedListener = null;

    public interface OnMasterSelectedListener{
        public void onItemSelected(String countryName);
    }

    public void setOnMasterSelectedListener(OnMasterSelectedListener listener){
        mOnMasterSelectedListener = listener;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String countries[]  = new String[]{"China", "India", "Russia", "Japan", "Germany", "France"};
        ListAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, countries);
        setListAdapter(adapter);
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        getListView().setOnItemClickListener((adapterView, view1, i, l) -> {
            if(mOnMasterSelectedListener!=null){
                mOnMasterSelectedListener.onItemSelected(((TextView)view1).getText().toString());
            }
        });

    }
}
