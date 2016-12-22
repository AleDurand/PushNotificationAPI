package project.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import project.models.NotificationModel;

@Component
public class NotificationValidator implements Validator {

	@Autowired
	private DataValidator dataValidator;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return NotificationModel.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		NotificationModel notification = (NotificationModel) target;
		if(notification == null){
			errors.rejectValue("notifications", "data.not_null", "{data.not_null}");
		} else {
			if(notification.getData() == null){
				errors.rejectValue("data", "data.not_null", "{data.not_null}");
			} else {
				errors.pushNestedPath("data");
				ValidationUtils.invokeValidator(dataValidator, notification.getData(), errors);
			}			
		}		
	}

}
