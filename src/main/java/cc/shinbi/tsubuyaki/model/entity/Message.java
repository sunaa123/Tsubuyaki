package cc.shinbi.tsubuyaki.model.entity;

//メッセージ情報に関するクラス
public class Message extends EntityBase {
	private int userId;
	private String text;
	private String imageFileName;
	private boolean publicMessage;
	
	//getter,setter
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getImageFileName() {
		return imageFileName;
	}
	
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	
	public boolean isPublicMessage() {
		return publicMessage;
	}
	
	public void setPublicMessage(boolean publicMessage) {
		this.publicMessage = publicMessage;
	}
}
