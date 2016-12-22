package project.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import project.models.request.NotificationModel;

@Component
public class NotificationValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return NotificationModel.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		NotificationModel notification = (NotificationModel) target;
		
		if (notification == null) {
			errors.rejectValue("notification", "notification.not_null", "{notification.not_null}");
		} else {
			if (notification.getTitle() == null) {
				errors.rejectValue("title", "notification.title.not_null", "{notification..not_null}");
			}
			
			if (notification.getBody() == null) {
				errors.rejectValue("body", "notification.body.not_null", "{notification.body.not_null}");
			}
		}
	}

}
