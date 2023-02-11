package Controller;



import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;
import javafx.application.Application;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    MessageService messageService;
    AccountService accountService;
    public SocialMediaController(){
        messageService = new MessageService();
        accountService = new AccountService();
    }
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        //Account Endpoints
        app.post("/register", this::insertAccountHandler);
        app.post("/login", this::getUserAccountHandler);

        //Message Endpoints
        app.get("example-endpoint", this::exampleHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageByIdHandler);
        app.delete("/messages/{message_id}", this::deleteMessageByIdHandler);
        app.patch("/messages/{message_id}", this::updateMessageTxtByIdHandler);
        //app.get("/accounts/{account_id}/messages", this::getMessagesFromUserHandler);


        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }

    //Account Handler
    private void insertAccountHandler(Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);
        Account addedAccount = accountService.insertAccount(account);
        if(addedAccount == null){
            context.status(400);
        }else{
            context.json(mapper.writeValueAsString(addedAccount));
            context.status(200);
        }
    }

    private void getUserAccountHandler(Context context){
        
    }
    
    //Message Handlers
    private void getAllMessagesHandler(Context context){
        List<Message>messages = messageService.getAllMessages();
        context.json(messages);
        context.status(200);
    }

    private void getMessageByIdHandler(Context context){
        int message_id = Integer.parseInt(context.pathParam("message_id"));
        context.json(messageService.getMessageById(message_id));
        context.status(200);
    }

    private void deleteMessageByIdHandler(Context context){
        int message_id = Integer.parseInt(context.pathParam("message_id"));
        Message messageToDelete = messageService.getMessageById(message_id);
        if(messageToDelete == null){
            context.status(200);
        }else{
            context.json(messageToDelete);
            context.status(200);
        }
    }

    private void updateMessageTxtByIdHandler(Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(), Message.class);
        int message_id = Integer.parseInt(context.pathParam("message_id"));
        Message messageToUpdate = messageService.updateMessageTxtById(message_id, message);

        if(messageToUpdate == null){
            context.status(400);
        }else{
            context.json(mapper.writeValueAsString(messageToUpdate));
        }

    }

    //private void getMessagesFromUserHandler(Context context){
        //context.json(messageService.getMessagesFromUser(context.pathParam("account_id"));
    //}
}