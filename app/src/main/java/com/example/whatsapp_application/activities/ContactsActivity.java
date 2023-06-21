package com.example.whatsapp_application.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsapp_application.R;
import com.example.whatsapp_application.adapters.ContactsAdapter;
import com.example.whatsapp_application.entities.Chat;
import com.example.whatsapp_application.entities.Message;
import com.example.whatsapp_application.entities.User;
import com.example.whatsapp_application.viewmodels.ContactsViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ContactsActivity extends AppCompatActivity {
    private TextView UsernameView;
    private TextView displayNameView;
    private ImageView profilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts_screen);
        // get the token from the activity that started this activity
        String token = getIntent().getStringExtra("token");
        String username = getIntent().getStringExtra("username");
        String displayName = getIntent().getStringExtra("displayname");

//        String image = getIntent().getStringExtra("picture");
//        UsernameView = findViewById(R.id.username);
//        displayNameView = findViewById(R.id.displayName);
        if (username == null || displayName == null || token == null) {
            Toast.makeText(getApplicationContext(), "Error loading user", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(getApplicationContext(), "Password must be 8-24 characters", Toast.LENGTH_SHORT).show();
//        UsernameView.setText(username);
//        displayNameView.setText(displayName);
        // set the profile pic

//        if (image != null) {
//            byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
//            Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//            profilePic.setImageBitmap(bitmap);
//        }
        ContactsViewModel contactsViewModel = new ViewModelProvider(this).get(ContactsViewModel.class);

        RecyclerView lstContacts = findViewById(R.id.lstContacts);

        // connect the recycler view to the adapter
        final ContactsAdapter adapter = new ContactsAdapter(this);
        // set the adapter
        lstContacts.setAdapter(adapter);
        // set the layout manager
        lstContacts.setLayoutManager(new LinearLayoutManager(this));

        // set the observer
        contactsViewModel.getChats(token).observe(this, chats -> {
            // update the cached copy of the words in the adapter.
            adapter.setChats(chats);
        });
    }

}
