package project.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "discriminator", visible = true, defaultImpl = UnicastMessageModel.class)
@JsonSubTypes({// @formatter:off
	@Type(name = "UNICAST", value = UnicastMessageModel.class),
	@Type(name = "MULTICAST", value = MulticastMessageModel.class) 
}) // @formatter:on
public abstract class MessageModel {

	private NotificationModel notification;
	private String condition;
	private String discriminator;

	public MessageModel() {
		super();
	}

	public String getDiscriminator() {
		return discriminator;
	}

	public void setDiscriminator(String discriminator) {
		this.discriminator = discriminator;
	}

	public NotificationModel getNotification() {
		return notification;
	}

	public void setNotification(NotificationModel data) {
		this.notification = data;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

}
