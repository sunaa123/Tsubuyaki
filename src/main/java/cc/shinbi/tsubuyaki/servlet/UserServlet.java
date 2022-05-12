package cc.shinbi.tsubuyaki.servlet;

import java.sql.Connection;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cc.shinbi.tsubuyaki.model.dao.UserDAO;
import cc.shinbi.tsubuyaki.model.entity.User;

//ユーザー管理に関するクラス
@WebServlet("/user")
public class UserServlet extends JspServlet {
	
	public UserServlet() {
		super(true);
	}
	
	//CRUDのどれかを選びそれに応じた処理をする
	@Override
	protected String view(
			HttpServletRequest request,
			HttpServletResponse response,
			Connection connection,
			User loginUser
	)throws Exception {
		String operation = request.getParameter("operation");
		String jsp = null;
		
		UserDAO dao = new UserDAO(connection);
		
		if(operation != null) {
			if(operation.equals("new")) {
				jsp = newUser(request, dao, loginUser);
			}
			else if(operation.equals("edit")) {
				jsp = editUser(request, dao, loginUser);
			}
			else if(operation.equals("add")){
				jsp = addUser(request, dao, loginUser);
			}
			else if(operation.equals("update")){
				jsp = updateUser(request, dao, loginUser);
		    }
			else if(operation.equals("delete")) {
				jsp = deleteUser(request, dao, loginUser);
			}
		}
		
		if(jsp == null) {
			jsp = getList(request, dao, loginUser);
		}
		
		return jsp;
	}
	
	//新規ユーザーを追加するページに移動する処理
	private String newUser(HttpServletRequest request, UserDAO dao, User loginUser)
	         throws Exception {
		String jsp = null;
		
		if(loginUser.isAdmin()) {
			jsp = "/WEB-INF/jsp/editUser.jsp";
		}
		else {
			String error = "権限がありません。";
			request.setAttribute("error", error);
			jsp = "/WEB-INF/jsp/error.jsp";
		}
		
		return jsp;
	}
	
	//既存ユーザー情報を編集するページに移動する処理
	private String editUser(HttpServletRequest request, UserDAO dao, User loginUser)
	         throws Exception {
		String jsp = null;
		
		if(loginUser.isAdmin()) {
			String id = request.getParameter("id");
			User user = dao.findById(Integer.parseInt(id));
			request.setAttribute("user", user);
			
			jsp = "/WEB-INF/jsp/editUser.jsp";
		}
		else {
			String error = "権限がありません。";
			request.setAttribute("error", error);
			jsp = "/WEB-INF/jsp/error.jsp";
		}
		
		return jsp;
	}
	
	//ユーザー一覧を取得し、表示するぺージに移動する処理
	private String getList(HttpServletRequest request, UserDAO dao, User loginUser)
	         throws Exception {
		String jsp = null;
		
		if(loginUser.isAdmin()) {
			List<User> users = dao.findAll();
			request.setAttribute("users", users);
			
			jsp = "/WEB-INF/jsp/users.jsp";
		}
		else {
			String error = "権限がありません。";
			request.setAttribute("error", error);
			jsp = "/WEB-INF/jsp/error.jsp";
		}
		
		return jsp;
	}
	
	//新規ユーザーを登録する処理
	private String addUser(HttpServletRequest request, UserDAO dao, User loginUser)
	         throws Exception {
		String jsp = null;
		
		if(loginUser.isAdmin()) {
			String error = "";
			String account = request.getParameter("account");
			if(account == null || account.isEmpty()) {
				error = "アカウント名を入力してください。";
			}
			else {
				User user = dao.findByAccount(account);
				if(user != null) {
					error ="そのアカウントは既に使われています。";
				}
			}
			
			String name = request.getParameter("name");
			if(name == null || name.isEmpty()) {
				error += "名前を入力してください。";
			}
			
			String isAdmin = request.getParameter("is_admin");
			
			String password = request.getParameter("password");
			if(password == null || password.isEmpty()) {
				error += "パスワードを入力してください。";
			}
			
			String confirmed = request.getParameter("confirmed");
			if(!password.equals(confirmed)) {
				error += "パスワードが一致しません。";
			}
			
			if(error.isEmpty()) {
				dao.addUser(account, name, password, Boolean.parseBoolean(isAdmin));
				jsp = this.getList(request, dao, loginUser);
			}
			else {
				request.setAttribute("error", error);
				jsp = "/WEB-INF/jsp/editUser.jsp";
			}
		}
		else {
			String error = "権限がありません。";
			request.setAttribute("error", error);
			jsp = "/WEB-INF/jsp/error.jsp";
		}
		
		return jsp;
	}
	
	//既存ユーザーの編集処理
	private String updateUser(HttpServletRequest request, UserDAO dao, User loginUser) 
	         throws Exception {
		String jsp = null;
		
		if(loginUser.isAdmin()) {
			String error = "";
			
			int id = Integer.parseInt(request.getParameter("id"));
			
			String account = request.getParameter("account");
			if(account == null || account.isEmpty()) {
				error = "アカウント名を入力してください。";
			}
			else {
				User user = dao.findByAccount(account);
				if(user != null && user.getId() != id) {
					error = "そのアカウントは既に使われています。";
				}
			}
			
			String name = request.getParameter("name");
			if(name == null || name.isEmpty()) {
				error += "名前を入力してください。";
			}
			
			String isAdmin = request.getParameter("is_admin");
			
			String password = request.getParameter("password");
			String confirmed = request.getParameter("confirmed");
			if(!password.equals(confirmed)) {
				error = "パスワードが一致しません。";
			}
			
			if(error.isEmpty()) {
				dao.updateUser(id, account, name, Boolean.parseBoolean(isAdmin));
				if(!password.isEmpty()) {
					dao.updatePassword(id, password);
				}
				jsp = this.getList(request, dao, loginUser);
			}
			else {
				User user = dao.findById(id);
				request.setAttribute("user", user);
				request.setAttribute("error", error);
				jsp = "/WEB-INF/jsp/editUser.jsp";
			}
		}
		else {
			String error = "権限がありません。";
			request.setAttribute("error", error);
			jsp = "/WEB-INF/jsp/error.jsp";
		}
		
		return jsp;
	}
	
	//ユーザーの削除処理
	private String deleteUser(HttpServletRequest request, UserDAO dao, User loginUser)
	         throws Exception {
		String jsp = null;
		
		if(loginUser.isAdmin()) {
			String error = "";
			
			int id = Integer.parseInt(request.getParameter("id"));
			
			User user = dao.findById(id);
			if(user.getId() == loginUser.getId()) {
				error = "現在ログイン中のユーザーを消すことはできません。";
				request.setAttribute("error", error);
			}
			else {
				dao.delete(id);
			}
			
			jsp = this.getList(request, dao, loginUser);
		}
		else {
			String error = "権限がありません。";
			request.setAttribute("error", error);
			jsp = "/WEB-INF/jsp/error.jsp";
		}
		
		return jsp;
	}
}
