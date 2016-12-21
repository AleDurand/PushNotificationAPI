package project.models;

public class PushModel {

	private NotificationModel data;
	private String to;

	public PushModel() {
		super();
	}

	public PushModel(NotificationModel data, String to) {
		super();
		this.data = data;
		this.to = to;
	}

	public NotificationModel getData() {
		return data;
	}

	public void setData(NotificationModel data) {
		this.data = data;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}
	
}
