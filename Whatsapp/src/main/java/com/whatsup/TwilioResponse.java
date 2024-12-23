package com.whatsup;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Response")
public class TwilioResponse {

    private String message;

    public TwilioResponse(String message) {
        this.message = message;
    }

    @XmlElement
    public String getMessage() {
        return message;
    }

    // No need for setter since it's initialized via constructor
}
