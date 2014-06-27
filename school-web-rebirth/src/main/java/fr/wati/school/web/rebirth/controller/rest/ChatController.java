package fr.wati.school.web.rebirth.controller.rest;

import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/chat/")
public class ChatController {

	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(3000); // simulated delay
        return new Greeting("["+new Date()+"]Hello, " + message.getName() + "!");
    }
	
	@RequestMapping(value="/send/{message}",method=RequestMethod.GET)
	public @ResponseBody String sendMessage(@PathVariable(value="message") String message,Principal principal){
		messagingTemplate.convertAndSend("/topic/greetings", message);
		return "Message sent @"+new Date();
	}
	
	@MessageExceptionHandler
	@SendToUser("/queue/errors")
	public String handleException(Throwable exception) {
 		return exception.getMessage();
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
