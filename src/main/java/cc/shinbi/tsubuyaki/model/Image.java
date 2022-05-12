package cc.shinbi.tsubuyaki.model;

import java.io.InputStream;

//画像データに関するクラス
public class Image {
	private String fileName;
	private InputStream stream;
	
	public Image(String fileName, InputStream stream) {
		this.fileName = fileName;
		this.stream = stream;
	}

	//getter,setter
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getStream() {
		return stream;
	}

	public void setStream(InputStream stream) {
		this.stream = stream;
	}
}
