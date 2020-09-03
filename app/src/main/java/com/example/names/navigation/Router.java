package com.example.names.navigation;

import android.app.Activity;
import android.content.Intent;

import com.example.names.presentation.view.EditorActivity;

public class Router {
    private Activity activity;
    public Router(Activity activity) {
        this.activity = activity;
    }
    public void openEditItemScreen() {
        Intent intent = new Intent(activity, EditorActivity.class);
        activity.startActivity(intent);
    }
}
