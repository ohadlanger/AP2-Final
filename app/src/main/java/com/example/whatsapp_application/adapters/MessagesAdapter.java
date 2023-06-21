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
import com.example.whatsapp_application.entities.Message;

import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessagesViewHolder> {

    public MessagesAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }
    public class MessagesViewHolder extends RecyclerView.ViewHolder {
        private final TextView messageText;
        private final TextView messageTime;



        public MessagesViewHolder(@NonNull View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.messageText);
            messageTime = itemView.findViewById(R.id.messageTime);
        }
    }
    private final LayoutInflater layoutInflater;

    private List<Message> messages;

    @NonNull
    @Override
    public MessagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate the item view - message_fragment
        View itemView = layoutInflater.inflate(R.layout.message_fragment, parent, false);
        return new MessagesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesViewHolder holder, int position) {
        if (messages != null) {
            final Message current = messages.get(position);
            holder.messageText.setText(current.getContent());
            holder.messageTime.setText(current.getCreated());
        } else {
            // Covers the case of data not being ready yet.
            holder.messageText.setText("No Message");
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
