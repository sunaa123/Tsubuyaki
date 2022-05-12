package cc.shinbi.tsubuyaki.model.entity;

import java.sql.Timestamp;

//MessageクラスとUserクラスと共通したスーパークラス
public class EntityBase {
	//ID
	private int id;
	//作成時間
	private Timestamp createdAt;
	//更新時間
	private Timestamp updatedAt;
	
    //getter,setter
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
}
