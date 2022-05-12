package cc.shinbi.tsubuyaki.servlet;

import java.io.InputStream;
import java.sql.Connection;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cc.shinbi.tsubuyaki.model.Image;
import cc.shinbi.tsubuyaki.model.dao.MessageDAO;

//画像の取得に関するクラス
@WebServlet("/image")
public class ImageServlet extends DbServlet{
	
	@Override
	protected void action(
			HttpServletRequest request,
			HttpServletResponse response,
			Connection connection
	)throws Exception {
		String id = request.getParameter("id");
		
		MessageDAO dao = new MessageDAO(connection);
		Image image = dao.getImage(Integer.parseInt(id));
		if(image != null && image.getStream() != null) {
			String fileName = image.getFileName();
			InputStream stream = image.getStream();
			
			if(fileName.toLowerCase().endsWith(".png")) {
				response.setContentType("image/png");
			}
			else {
				response.setContentType("image/jpeg");
			}
			
			byte[] buffer = new byte[1024];
			int length = 0;
			while((length = stream.read(buffer)) > 0) {
				response.getOutputStream().write(buffer, 0, length);
			}
		}
	}
}
