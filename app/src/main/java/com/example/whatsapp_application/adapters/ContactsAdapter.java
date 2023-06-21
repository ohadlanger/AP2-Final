package com.example.whatsapp_application.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsapp_application.R;
import com.example.whatsapp_application.entities.Chat;
import android.util.Base64;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.util.List;

public class ContactsAdapter  extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>{


    public ContactsAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }



    public class ContactsViewHolder extends RecyclerView.ViewHolder {
        private final TextView contactName;
        private final ImageView contactImage;

        private final TextView lastMessage;

        private final TextView lastMessageTime;

        public ContactsViewHolder(@NonNull View itemView) {
            super(itemView);
            contactName = itemView.findViewById(R.id.contactName);
            contactImage = itemView.findViewById(R.id.contactImage);
            lastMessage = itemView.findViewById(R.id.lastMessage);
            lastMessageTime = itemView.findViewById(R.id.lastMessageTime);

        }
    }

    private final LayoutInflater layoutInflater;
    private List<Chat> chats;

    @NonNull
    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate the item view - contact_fragment
        View itemView = layoutInflater.inflate(R.layout.contact_fragment, parent, false);
        return new ContactsViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder holder, int position) {
        if (chats != null) {
            final Chat current = chats.get(position);
            holder.contactName.setText(current.getUser().getUsername());
            // set the image from a string to a bitmap convert to bitmap
            String image = current.getUser().getProfilePic();
            // convert the string to a bitmap
            if (image != null) {
                byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                holder.contactImage.setImageBitmap(bitmap);
            }

            holder.lastMessage.setText(current.getLastMessage().getContent());
            holder.lastMessageTime.setText(current.getLastMessage().getCreated());
        } else {
            // Covers the case of data not being ready yet.
            holder.contactName.setText("No User");
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public List<Chat> getChats() {
        return chats;
    }
    public void setChats(List<Chat> chats) {
        this.chats = chats;
        notifyDataSetChanged();
    }



}
