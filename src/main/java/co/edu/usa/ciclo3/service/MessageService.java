package co.edu.usa.ciclo3.service;

import co.edu.usa.ciclo3.entity.Message;
import co.edu.usa.ciclo3.repository.MessageRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Transactional(readOnly = true)
    public List<Message> getList() {
        return messageRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Message getById(Long id) {
        return messageRepository.findById(id).get();
    }

    @Transactional
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Transactional
    public Message update(Message message) {
        if (message.getIdMessage() != null) {
            Optional<Message> messageId = messageRepository.findById(message.getIdMessage());

            if (!messageId.isEmpty()) {
                Message messageBd = messageId.get();

                messageBd.setMessageText(message.getMessageText());
                messageBd.setCabin(message.getCabin());
                messageBd.setClient(message.getClient());

                return messageRepository.save(messageBd);

            } else {

                return message;
            }
        }
        return message;
    }

    @Transactional
    public void delete(Long id) {
        messageRepository.deleteById(id);
    }

}
