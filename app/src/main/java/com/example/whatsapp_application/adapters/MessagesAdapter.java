import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsapp_application.R;
import com.example.whatsapp_application.entities.Message;

import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessagesViewHolder> {

    private static final int VIEW_TYPE_RECEIVED = 1;
    private static final int VIEW_TYPE_SENT = 2;

    private Context context;
    private List<Message> messages;

    public MessagesAdapter(Context context) {
        this.context = context;
    }

    public class MessagesViewHolder extends RecyclerView.ViewHolder {
        private TextView messageText;
        private TextView messageTime;

        public MessagesViewHolder(@NonNull View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.messageText);
            messageTime = itemView.findViewById(R.id.messageTime);
        }
    }

    @NonNull
    @Override
    public MessagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        if (viewType == VIEW_TYPE_RECEIVED) {
            View itemView = layoutInflater.inflate(R.layout.received_message_lay, parent, false);
            return new MessagesViewHolder(itemView);
        } else {
            View itemView = layoutInflater.inflate(R.layout.sent_message_lay, parent, false);
            return new MessagesViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesViewHolder holder, int position) {
        if (messages != null) {
            final Message current = messages.get(position);
            holder.messageText.setText(current.getContent());
            holder.messageTime.setText(current.getCreated());
        } else {
            holder.messageText.setText("No Message");
        }
    }

    @Override
    public int getItemCount() {
        return messages != null ? messages.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        Message message = messages.get(position);
        if (message.isReceived()) {
            return VIEW_TYPE_RECEIVED;
        } else {
            return VIEW_TYPE_SENT;
        }
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
        notifyDataSetChanged();
    }
}
