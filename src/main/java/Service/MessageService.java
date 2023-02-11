package Service;

import java.util.List;

import DAO.MessageDAO;
import Model.Message;

public class MessageService {
    MessageDAO messageDAO;

    public MessageService(){
        messageDAO = new MessageDAO();
    }

    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }



    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    public Message getMessageById(int message_id){
        return messageDAO.getMessageById(message_id);
    }

    public void deleteMessageById(int message_id){
        messageDAO.deleteMessageById(message_id);
    }

    public Message updateMessageTxtById(int message_id, Message message){
        if(messageDAO.getMessageById(message_id) == null){
            return null;
        }else{
           return messageDAO.getMessageById(message_id);
        }
        
    }

    public List<Message> getMessagesFromUser(int posted_by){
        return messageDAO.getMessagesFromUser(posted_by);
    }
}
