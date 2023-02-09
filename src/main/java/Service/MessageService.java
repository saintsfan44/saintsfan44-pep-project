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

    public void updateMessageTxtById(int message_id, String newtxt){
        messageDAO.updateMessageTxtById(message_id, newtxt);
    }
}
