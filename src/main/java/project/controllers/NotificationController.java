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

import project.models.NotificationModel;
import project.services.NotificationService;
import project.validators.NotificationValidator;

@RestController
@RequestMapping(value = "/notifications")
public class NotificationController {

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private NotificationValidator notificationValidator;

	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Void> create(@Validated @RequestBody NotificationModel notification) {
		notificationService.sendPush(notification);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(notificationValidator);
	}
}
