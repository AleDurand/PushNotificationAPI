package project.services;

import project.models.MessageModel;
import project.models.ResponseModel;

public interface MessageService {

	public ResponseModel sendPush(MessageModel notification);

}
