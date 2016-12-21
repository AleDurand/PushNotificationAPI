package project.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import project.models.NotificationModel;

@Component
public class NotificationValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return NotificationModel.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		NotificationModel notification = (NotificationModel) target;
		
		if (notification.getTitle() == null) {
			errors.rejectValue("title", "notification.title.not_null", "{notification.title.not_null}");
		}
		
		if (notification.getMessage() == null) {
			errors.rejectValue("message", "notification.message.not_null", "{notification.message.not_null}");
		}
	}

}
