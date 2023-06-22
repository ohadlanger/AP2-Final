package com.example.whatsapp_application.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.whatsapp_application.activities.MyApplication;
import com.example.whatsapp_application.entities.Message;
import com.example.whatsapp_application.repositories.MessageRepository.MessageRepository;

import java.util.List;

public class MessageViewModel extends ViewModel {

    MutableLiveData<List<Message>> messages;
    private final MessageRepository messageRepository;

    public MessageViewModel() {
        this.messageRepository = new MessageRepository(MyApplication.getContext());
        messages = new MutableLiveData<>();
    }
    public void createMessage(int chatId, String msg, String token) {
        messageRepository.createMessage(chatId, msg, token, messages);
    }
    public void ClearMessages() {
        messageRepository.ClearMessages();
    }
    public MessageRepository getMessageRepository() {
        return messageRepository;
    }
    public LiveData<List<Message>> getMessages(int chatId,String token) {
        messageRepository.getAllMessages(chatId,token, messages);
        return messages;
    }

}
