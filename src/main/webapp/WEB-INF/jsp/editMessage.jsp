<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- メッセージ編集ページ -->
    
<%@ page import="cc.shinbi.tsubuyaki.model.Const" %>
<%@ page import="cc.shinbi.tsubuyaki.model.Pair" %>
<%@ page import="cc.shinbi.tsubuyaki.model.entity.User" %>
<%@ page import="cc.shinbi.tsubuyaki.model.entity.Message" %>

<%
   User loginUser = (User)session.getAttribute(Const.LOGIN_USER_KEY);
   Message message = (Message)request.getAttribute("message");
   String error = (String)request.getAttribute("error");
   String imageFileName = message.getImageFileName();
   String text = (String)request.getAttribute("text");
   String publicOption = "checked";
   String privateOption = "";
   if(!message.isPublicMessage()) {
	   publicOption = "";
	   privateOption = "checked";
   }
%>
    
<!DOCTYPE html>
<html>
   <head>
      <jsp:include page="head.jsp" />
      <title>つぶやき編集</title>
   </head>
   <body>
        <jsp:include page="header.jsp" />
        
        <main>
           <div id="edit_message_area">
              <form id="new_message_form" method="post" action="postMessage"
                    enctype="multipart/form-data">
                 <div>
                    <textarea id="message_text" name="text"></textarea>
                 </div>      
                 <div>
                    <input type="radio" name="is_public" value="true"
                       <%= publicOption %>>公開
                    <input type="radio" name="is_public" value="false"
                       <%= publicOption %>>非公開
                 </div>
                 <div>
                    <span id="image_file_name"><%= imageFileName %></span>
                    <input id="message_image_file" type="file" name="image_file">
                    <a href="javascript:removeImage()">
                       <span class="icon red fas fa-times-circle"></span>
                    </a> 
                 </div>
                 <div>
                    <input id="submit" type="submit" name="submit" value="更新">
                 </div>
                 <input type="hidden" name="id" value="<%= message.getId() %>">
                 <input id="delete_image_input" type="hidden" name="delete_image_flag" value="false">                    
              </form>
              <script>
<%
   if(imageFileName == null || imageFileName.isEmpty()) {
%>
                 $('#image_file_name').css('display', 'none');
<%
   }
   else {
%>
                 $('#message_image_file').css('display', 'none');
<%
   }
%>  
              </script>
           </div>
<%
   if(error != null) {
%>
           <div id="error"><%= error %></div>
<%
   }
%>
        </main>
        <jsp:include page="footer.jsp" />
   </body>
</html>