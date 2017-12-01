package com.example.zero.pokedex;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MemoryList extends ArrayAdapter<MemoryItem> {
    private Activity context;
    List<MemoryItem> memories;
    Typeface fontType;



    public MemoryList(Activity context, List<MemoryItem> memories) {
        super(context, R.layout.layout_memory_list, memories);
        this.context = context;
        this.memories = memories;
    }

    public MemoryList(Activity context, List<MemoryItem> memories, String font) {
        super(context, R.layout.layout_memory_list, memories);
        this.context = context;
        this.memories = memories;
        this.fontType = Typeface.createFromAsset(context.getAssets(), font);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_memory_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewTitle);
        TextView textViewMemory = (TextView) listViewItem.findViewById(R.id.textViewMemory);

        MemoryItem memory = memories.get(position);
        textViewName.setText(memory.getTitle());
        textViewMemory.setText(memory.getDateCreated().toString());

        textViewName.setTypeface(fontType);
        textViewMemory.setTypeface(fontType);


        return listViewItem;
    }



}
