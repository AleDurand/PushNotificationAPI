package project.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import project.models.DataModel;

@Component
public class DataValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return DataModel.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		DataModel data = (DataModel) target;
		
		if(data == null){
			errors.rejectValue("data", "data.not_null", "{data.not_null}");
		} else {
			if (data.getTitle() == null) {
				errors.rejectValue("title", "data.title.not_null", "{data.title.not_null}");
			}
			
			if (data.getMessage() == null) {
				errors.rejectValue("message", "data.message.not_null", "{data.message.not_null}");
			}
		}
	}

}
