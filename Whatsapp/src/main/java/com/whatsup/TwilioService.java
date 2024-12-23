package com.whatsup;

import com.firebase.firebaseCrud;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {

    private final TwilioConfig twilioConfig;
    
   
	@Autowired
    public TwilioService(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
	 
        Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
    }

    public void sendMessage() {
        try {
            Message message = Message.creator(
                new com.twilio.type.PhoneNumber(twilioConfig.getPhoneNumbers().getTo()),
                new com.twilio.type.PhoneNumber(twilioConfig.getPhoneNumbers().getFrom()),
                twilioConfig.getMessageTemplate().getBody()
            ).create();
            System.out.println("Message SID: " + message.getSid());
        } catch (Exception e) {
            System.out.println("Twilio Exception: " + e.getMessage());
            
            //get from phone number
            String fromPhoneNumber = twilioConfig.getPhoneNumbers().getFrom();
            System.out.println("From Phone Number: " + fromPhoneNumber);
            
         
        }
    }
    
    public String handleWhatsAppQuery(String body) throws ExecutionException, InterruptedException {
        // Get Firestore instance
        Firestore dbFirestore = FirestoreClient.getFirestore();

        // Create document reference
        DocumentReference documentReference = dbFirestore.collection("whatsapp_replies").document(body);

        // Get document snapshot
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        // Get response from document
        String response = document.getString("trigger");

        return response;
    }
    
    
   //You can use as replies from chatbot instead of getting replies directly from Firebase
	/*
	 * public String processMessage(String from, String body) throws
	 * InterruptedException, ExecutionException {
	 * 
	 * // Simple chatbot logic if (body == null) { throw new
	 * IllegalArgumentException("Received message body is null"); } else if
	 * (body.equalsIgnoreCase("hey")) { return
	 * "Hi there! How can I assist you today?"; } else if
	 * (body.equalsIgnoreCase("My Name is Richard")) { return
	 * "Hi Richard! How can I assist you today?"; } else if
	 * (body.equalsIgnoreCase("Mr. Sunny")) { return
	 * "Hi Mr. Sunny! can you lend me Money?"; } else if
	 * (body.equalsIgnoreCase("help")) { return
	 * "Here are some commands you can use: 'hello', 'status', 'bye'."; } else if
	 * (body.equalsIgnoreCase("bye")) { return "Goodbye! Have a great day!"; } else
	 * { return
	 * "Sorry, I didn't understand that. Try 'help' for available commands."; } }
	 */
}
