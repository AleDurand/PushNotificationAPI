package project.models;

public class MessageModel {

	private NotificationModel notification;
	private String to;
	private String [] registrationIds;
	private String condition;

	public MessageModel() {
		super();
	}

	public NotificationModel getNotification() {
		return notification;
	}

	public void setData(NotificationModel data) {
		this.notification = data;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}
	
	public String[] getRegistrationIds() {
		return registrationIds;
	}

	public void setRegistrationIds(String[] registrationIds) {
		this.registrationIds = registrationIds;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}
	
}
