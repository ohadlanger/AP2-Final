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
import com.example.whatsapp_application.viewmodels.MessageViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private TextView senderDisplayNameView;
    private ImageView senderImageView;

    private TextView displayNameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts_screen);
    }
    String chatId = getIntent().getIntExtra("chatId");
    String token = getIntent().getStringExtra("token");

    // sender username
    String username = getIntent().getStringExtra("username");
    // sender display name
    String displayName = getIntent().getStringExtra("displayName");
    // sender profile picture
    String image = getIntent().getStringExtra("image");
    // receiver username
    String receiverUsername = getIntent().getStringExtra("receiverUsername");

    MessageViewModel messageViewModel = new ViewModelProvider(this).get(MessageViewModel.class);

    // set the sender's display name
    senderDisplayNameView.setText(displayName);


    // get the messages from the view model

    // todo : validate the data

    // set the sender's display name
//    senderDisplayNameView.setText(displayName);
    // set the sender's profile picture
//    senderImageView = findViewById(R.id.senderImage);
//    if (image != null) {
//        byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
//        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//        senderImageView.setImageBitmap(bitmap);
//    }
//
//    // set adapter for the recycler view
//    RecyclerView recyclerView = findViewById(R.id.recyclerView);
//    MessageAdapter messageAdapter = new MessageAdapter(this, new ArrayList<>(), username);
//    recyclerView.setAdapter(messageAdapter);
//    recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//    // get the messages from the view model
//    messageViewModel = new ViewModelProvider(this).get(MessageViewModel.class);
//    messageViewModel.getMessages(chatId, token).observe(this, messages -> {
//        if (messages == null) {
//            Toast.makeText(getApplicationContext(), "Error loading messages", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        messageAdapter.setMessages(messages);
//        recyclerView.scrollToPosition(messages.size() - 1);
//    });




    // set the title of the activity to the receiver's username

}