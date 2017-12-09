package com.example.zero.pokedex;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MemoryItem {

    String title;
    String dateCreated;

    public MemoryItem() { }


    public MemoryItem(String title) {
        this.title = title;
        setDateCreated();
    }

    public String getTitle() {
        return title;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm a");
        Date currentDate = new Date();
        this.dateCreated = dateFormat.format(currentDate);
    }

}
