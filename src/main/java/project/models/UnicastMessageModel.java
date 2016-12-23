package project.models;

public class UnicastMessageModel extends MessageModel {

	private String to;
	
	public UnicastMessageModel(){
		super();
	}
	
	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}
	
}
