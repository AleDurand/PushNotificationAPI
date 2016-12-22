package project.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.models.MessageModel;
import project.models.ResponseModel;
import project.services.MessageService;
import project.validators.MessageValidator;

@RestController
@RequestMapping(value = "/messages")
public class MessageController {

	@Autowired
	private MessageService messageService;

	@Autowired
	private MessageValidator messageValidator;

	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<ResponseModel> create(@Validated @RequestBody MessageModel message) {
		ResponseModel toReturn = messageService.sendPush(message);
		return new ResponseEntity<>(toReturn, HttpStatus.CREATED);
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(messageValidator);
	}
}
