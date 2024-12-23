package com.whatsup;
 

import java.util.concurrent.ExecutionException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class TwilioController {

    private final TwilioService twilioService;
    private  String response;

    @Autowired
    public TwilioController(TwilioService twilioService) {
        this.twilioService = twilioService;
    }

	/*
	 * @PostMapping("/incoming-message") public String handleIncomingMessage(
	 * HttpServletRequest request) throws InterruptedException, ExecutionException {
	 * 
	 * // Extract message and sender info from Twilio's request String from =
	 * request.getParameter("From"); // Sender's number String body =
	 * request.getParameter("Body"); // Message content
	 * 
	 * if (body == null) { return "No body received"; }
	 * 
	 * // Process the message (e.g., act as a chatbot) String responseMessage =
	 * twilioService.processMessage(from, body);
	 * 
	 * // Return Twilio's XML response return String.format(
	 * "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "<Response>\n" +
	 * "<Message>%s</Message>\n" + "</Response>", responseMessage ); }
	 */
    
    @PostMapping("/send-message")
    public ResponseEntity<String> handleWhatsAppQuery(@RequestParam("Body") String body) {
        try {
            String response = twilioService.handleWhatsAppQuery(body);
            
            if(response == null) {
            	return ResponseEntity.ok("type :'help' to find available word");
            } 
            
            if (response.equals("hey") ) {
            	return ResponseEntity.ok("Hello!, how may i help you today");
            }
            System.out.println(response.equals("hey"));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error processing query");
        }
    }
    
 

	//use this after uncommenting processMessage from twilioService
	 // @PostMapping("/send-messages")
		/*
		 * public ResponseEntity<String> handleIncomingMessage(@RequestParam("Body")
		 * String body,
		 * 
		 * @RequestParam("From") String from) throws JAXBException,
		 * InterruptedException, ExecutionException {
		 * 
		 * // Log incoming message details System.out.println("Received message: " +
		 * body); System.out.println("From: " + from);
		 * 
		 * String responseMessage = twilioService.processMessage(from, body);
		 * 
		 * 
		 * Firestore dbFirestore = FirestoreClient.getFirestore(); DocumentReference
		 * documentReference =
		 * dbFirestore.collection("whatsapp_replies").document(body);
		 * ApiFuture<DocumentSnapshot> future = documentReference.get();
		 * DocumentSnapshot document = future.get();
		 * 
		 * setResponse(document.getString("trigger")); System.out.println(response);
		 * System.out.println(getResponse());
		 * 
		 * // Create TwiML response (XML format) //TwilioResponse response = new
		 * TwilioResponse("Hi there! How can I assist you today?");
		 * 
		 * String twilioResponse =
		 * String.format("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
		 * "<Response><Message>%s</Message></Response>", responseMessage );
		 * 
		 * 
		 * return ResponseEntity.ok().contentType(MediaType.APPLICATION_XML).body(
		 * twilioResponse); }
		 * 
		 */	  
	  
	  @GetMapping("/send-message")
	public String getMessage()
	{
		  twilioService.sendMessage();
		  return "Message sent!"; 
	}
	 

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	 
}
