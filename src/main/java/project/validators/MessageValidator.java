package project.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import project.models.request.MessageModel;

@Component
public class MessageValidator implements Validator {

	@Autowired
	private NotificationValidator notificationValidator;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return MessageModel.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		MessageModel message = (MessageModel) target;
		
		if (message == null) {
			errors.rejectValue("message", "message.not_null", "{message.not_null}");
		} else {
			if (message.getNotification() == null) {
				errors.rejectValue("notification", "notification.not_null", "{notification.not_null}");
			} else {
				errors.pushNestedPath("notification");
				ValidationUtils.invokeValidator(notificationValidator, message.getNotification(), errors);
			}			
		}		
	}

}
