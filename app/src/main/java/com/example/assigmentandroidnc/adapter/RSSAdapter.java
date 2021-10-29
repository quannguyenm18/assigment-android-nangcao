package com.example.assigmentandroidnc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.assigmentandroidnc.R;
import com.example.assigmentandroidnc.model.RSSModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RSSAdapter extends ArrayAdapter<RSSModel> {

    public RSSAdapter(@NonNull Context context, int resource, @NonNull List<RSSModel> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(R.layout.item_listview, null);

        }
        RSSModel p=getItem(position);

        if(p!=null){
            TextView textView= view.findViewById(R.id.title_item);
            textView.setText(p.getTitle());

        }
        return view;
}

                }
