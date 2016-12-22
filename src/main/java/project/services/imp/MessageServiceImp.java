package project.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import project.exceptions.CustomException;
import project.models.request.MessageModel;
import project.models.response.ResponseModel;
import project.services.MessageService;

@Service
@PropertySource("file:src/main/resources/application.properties")
public class MessageServiceImp implements MessageService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${application.fcm_token}")
	public String FCM_TOKEN;

	public ResponseModel sendPush(MessageModel message) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "key=" + FCM_TOKEN);
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Object> entity = new HttpEntity<Object>(message, headers);
			ResponseEntity<ResponseModel> response = restTemplate.exchange("https://fcm.googleapis.com/fcm/send", HttpMethod.POST, entity, ResponseModel.class);
			return response.getBody();
		} catch (RestClientException ex) {
			throw new CustomException(ex.getMessage(), null);
		}
	}

}
