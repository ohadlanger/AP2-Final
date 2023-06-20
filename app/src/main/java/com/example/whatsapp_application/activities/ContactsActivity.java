package com.example.whatsapp_application.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsapp_application.R;
import com.example.whatsapp_application.adapters.ContactsAdapter;
import com.example.whatsapp_application.api.ChatApi;
import com.example.whatsapp_application.entities.User;
import com.example.whatsapp_application.viewmodels.ContactsViewModel;

public class ContactsActivity extends AppCompatActivity {

    private ContactsViewModel contactsViewModel;

    private TextView username;
    private TextView displayName;
    private ImageView profilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts_screen);
        // get the token from the activity that started this activity
        String token = getIntent().getStringExtra("token");
        // get the user
        User user = (User) getIntent().getSerializableExtra("user");
//        username = findViewById(R.id.username);
//        displayName = findViewById(R.id.displayName);
        profilePic = findViewById(R.id.userProfilePic);

        username.setText(user.getUsername());
        displayName.setText(user.getDisplayName());
        profilePic.setImageBitmap(user.getProfilePic());

        contactsViewModel = new ViewModelProvider(this).get(ContactsViewModel.class);

        RecyclerView lstContacts = findViewById(R.id.lstContacts);
        // connect the recycler view to the adapter
        final ContactsAdapter adapter = new ContactsAdapter(this);
        // set the adapter
        lstContacts.setAdapter(adapter);
        // set the layout manager
        lstContacts.setLayoutManager(new LinearLayoutManager(this));
        // set the observer
        contactsViewModel.getChats(token).observe(this, adapter::setChats);
    }
}
