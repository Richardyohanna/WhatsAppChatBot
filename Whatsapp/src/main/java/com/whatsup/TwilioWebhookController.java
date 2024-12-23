package com.whatsup;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.rest.api.v2010.account.Message;

@RestController
public class TwilioWebhookController {

    @SuppressWarnings("unchecked")
	@PostMapping("/whatsapp-webhook")
    public ResponseEntity<String> handleIncomingMessage(@RequestBody Message message) {
        String from = message.getFrom().toString();
        String body = message.getBody();

        // Process incoming message
        System.out.println("Received message from " + from + ": " + body);

        // Respond to the message (optional)
        // return ResponseEntity.ok("Your response message");

        return (ResponseEntity<String>) ResponseEntity.ok();
    }
}
