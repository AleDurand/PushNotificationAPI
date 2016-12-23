package project.models;

import java.util.List;

public class MulticastMessageModel extends MessageModel {

	private List<String> registrationIds;

	public MulticastMessageModel() {
		super();
	}

	public List<String> getRegistrationIds() {
		return registrationIds;
	}

	public void setRegistrationIds(List<String> registrationIds) {
		this.registrationIds = registrationIds;
	}
}
