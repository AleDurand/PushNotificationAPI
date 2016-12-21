package project.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import project.exceptions.CustomException;
import project.models.NotificationModel;
import project.models.PushModel;
import project.services.NotificationService;

@Service
@PropertySource("file:src/main/resources/application.properties")
public class NotificationServiceImp implements NotificationService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${application.device_token}")
	public String DEVICE_TOKEN;

	@Value("${application.fcm_token}")
	public String FCM_TOKEN = System.getProperty("application.fcm_token");

	public void sendPush(NotificationModel notification) {
		try {
			PushModel push = new PushModel(notification, DEVICE_TOKEN);

			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "key=" + FCM_TOKEN);
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Object> entity = new HttpEntity<Object>(push, headers);
			
			restTemplate.exchange("https://fcm.googleapis.com/fcm/send", HttpMethod.POST,	entity, String.class);
			
		} catch (RestClientException ex) {
			throw new CustomException(ex.getMessage(), null);
		}
	}

}
