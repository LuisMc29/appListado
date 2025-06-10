package com.example.listadotareas.home;

import android.content.ClipData;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;


public class AdapterItem extends BaseAdapter {

    private Context context;
    private List<item> tareas;
    public AdapterItem(Context _context, List<item> _tareas){
        this.context = _context;
        this.tareas = _tareas;
    }


    @Override
    public int getCount() {
        return this.tareas.size();
    }

    @Override
    public Object getItem(int position) {
        return this.tareas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
