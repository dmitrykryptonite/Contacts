package com.example.contacts.navigation;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.example.contacts.domain.entities.Contact;
import com.example.contacts.presentation.view.InfoActivity;

public class Router {
    private Activity activity;

    public Router(Activity activity) {
        this.activity = activity;
    }

    public void openInfoScreen() {
        Intent intent = new Intent(activity, InfoActivity.class);
        activity.startActivity(intent);
    }

    public void openCallScreen(Contact contact) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        String callNumber = contact.getCallNumber();
        intent.setData(Uri.parse("tel:" + callNumber));
        activity.startActivity(intent);
    }
}
