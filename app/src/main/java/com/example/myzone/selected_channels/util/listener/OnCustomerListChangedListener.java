package com.example.myzone.selected_channels.util.listener;

import com.example.myzone.selected_channels.channel;

import java.util.List;

public interface OnCustomerListChangedListener {
    void onNoteListChanged(List<channel> customers);
}