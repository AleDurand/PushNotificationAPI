package project.services;

import project.models.NotificationModel;

public interface NotificationService {
	
	public void sendPush(NotificationModel notification);
	
}
