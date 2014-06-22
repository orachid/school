package fr.wati.school.web.rebirth.controller.rest;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/rest/documents")
public class DocumentRestController {

	public DocumentRestController() {
	}

	
	@RequestMapping(value = "/private", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<String> getPrivateDocuments() throws Exception {
		FileUtils.readFileToString(new File("c:\\documents.txt"));
		return new ResponseEntity<String>(FileUtils.readFileToString(new File("c:\\documents.txt")),
				HttpStatus.OK);
	}
	
	@RequestMapping(value = "/public", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<String> getPublicsDocuments() throws Exception {
		FileUtils.readFileToString(new File("c:\\documents.txt"));
		return new ResponseEntity<String>(FileUtils.readFileToString(new File("c:\\documents.txt")),
				HttpStatus.OK);
	}
}
