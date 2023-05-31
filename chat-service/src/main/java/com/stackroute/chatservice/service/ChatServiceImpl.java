package com.stackroute.chatservice.service;

import com.stackroute.chatservice.domain.Chat;
import com.stackroute.chatservice.domain.ChatDTO;
import com.stackroute.chatservice.domain.Message;
import com.stackroute.chatservice.domain.User;
import com.stackroute.chatservice.execption.ChatNotFoundException;
import com.stackroute.chatservice.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public Optional<Chat> getChat(String id1, String id2) {

        List<Chat> chats = chatRepository.findByParticipants(id1, id2);
        Optional<Chat> matchingChat = chats.stream()
                .filter(chat -> chat.getParticipants().contains(id1) && chat.getParticipants().contains(id2))
                .findFirst();

        return matchingChat;
    }

    @Override
    public Chat saveChat(ChatDTO chatDTO) {
        List<String> participants = new ArrayList<>();
        participants.add(chatDTO.getParticipant1());
        participants.add(chatDTO.getParticipant2());

        Chat chat = new Chat();
        chat.setParticipants(participants);
        chat.setMessages(new ArrayList<>());

        chatRepository.save(chat);

        return chat;
    }

    @Override
    public Chat addMessage(Message message) throws ChatNotFoundException {

        String sender = message.getSenderId();
        String receiver = message.getReceiverId();

        List<Chat> chats = chatRepository.findByParticipants(sender, receiver);
        Optional<Chat> matchingChat = chats.stream()
                .filter(chat -> chat.getParticipants().contains(sender) && chat.getParticipants().contains(receiver))
                .findFirst();

        if(matchingChat.isPresent()) {

            Chat chatFromDb = matchingChat.get();
            List<Message> messages = chatFromDb.getMessages();

            Message newMessage = new Message();
            newMessage.setContent(message.getContent());
            newMessage.setSenderId(sender);
            newMessage.setReceiverId(receiver);
            newMessage.setTimestamp(message.getTimestamp());

            messages.add(newMessage);

            chatFromDb.setMessages(messages);

            return chatRepository.save(chatFromDb);
        }
        else {
            throw new ChatNotFoundException("Chat not found between the participants: " + sender + " and " + receiver);
        }
    }

    // return list of users in logged user inbox
    @Override
    public List<User> getUsers(String id) {
        // id -> id of logged user

        List<Chat> chats = chatRepository.findByParticipants(id);
        List<User> users = new ArrayList<>();

        for (Chat chat : chats) {
            List<String> participants = chat.getParticipants();
            List<Message> messages = chat.getMessages();
            Message lastMessage = messages.isEmpty() ? new Message() : messages.get(messages.size() - 1);

            User user = new User();
            for (String participant : participants) {
                if (!participant.equals(id)) {
                    user.setUserId(participant);
                }
            }
            user.setMessage(lastMessage.getContent());
            user.setTimestamp(lastMessage.getTimestamp());

            users.add(user);
        }

        return users;
    }


}
