package fr.wati.school.web.rebirth.controller.rest;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat/")
public class ChatController {

	
	@MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(3000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }
	
	public static class Greeting {

	    private String content;

	    public Greeting(String content) {
	        this.content = content;
	    }

	    public String getContent() {
	        return content;
	    }
	}
	
	public static class HelloMessage {

	    private String name;

	    
	    public HelloMessage() {
			super();
		}


		public String getName() {
	        return name;
	    }

	}
}
