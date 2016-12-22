package project.services;

import project.models.request.MessageModel;
import project.models.response.ResponseModel;

public interface MessageService {

	public ResponseModel sendPush(MessageModel notification);

}
