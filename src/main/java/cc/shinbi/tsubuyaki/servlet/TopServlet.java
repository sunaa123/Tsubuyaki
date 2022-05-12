package cc.shinbi.tsubuyaki.servlet;

import java.sql.Connection;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cc.shinbi.tsubuyaki.model.Pair;
import cc.shinbi.tsubuyaki.model.dao.MessageDAO;
import cc.shinbi.tsubuyaki.model.entity.User;

//TOPページに移動するクラス
@WebServlet("/top")
public class TopServlet extends JspServlet {
	
	public TopServlet() {
		super(true);
	}

	//top.jspに移動する処理
	@Override
	protected String view(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Connection connection,
			User loginUser
			) throws Exception {
		MessageDAO dao = new MessageDAO(connection);
		List<Pair> pairs = dao.findVisible(loginUser.getId());
		request.setAttribute("pairs", pairs);
		
		String jsp = "/WEB-INF/jsp/top.jsp";
		return jsp;
	}
}
