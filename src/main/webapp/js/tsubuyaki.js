//ユーザーの新規作成に関する処理
function addUser() {
	$('#operation_input').val('new');
	$('#user_form').submit();
}

//ユーザーの編集に関する処理
function editUser(id) {
	$('#operation_input').val('edit');
	$('#user_id_input').val(id);
	$('#user_form').submit();
}

//ユーザーの削除に関する処理
function deleteUser(id) {
	var ret = confirm('ユーザーを削除してもよろしいですか？');
	if(ret) {
		$('#operation_input').val('delete');
		$('#user_id_input').val(id);
		$('#user_form').submit();
	}
}

//投稿フォームの表示に関する処理
function toggleNewMessage() {
	var currentStatus = $('#new_message_area').css('display');
	if(currentStatus == 'none') {
		$('#new_message_area').css('display', 'block');
		$('#new_message_icon').removeClass('fa-plus-square');
		$('#new_message_icon').addClass('fa-minus-square');
	}
	else {
		$('#new_message_area').css('display', 'none');
		$('#new_message_icon').removeClass('fa-minus-square');
		$('#new_message_icon').addClass('fa-plus-square');
	}
}

//画像フォームに関する処理
function removeImage() {
	$('#message_image_file').css('display', 'inline');
	$('#image_file_name').css('display', 'none');
	$('#delete_image_input').val('true');
	$('#message_image_file').val('');
}

//投稿の編集に関する処理
function editMessage(id) {
	$('#id_input').val(id);
	$('#operation_input').val('edit');
	$('#message_operation_form').submit();
}

//投稿の削除に関する処理
function deleteMessage(id) {
	var ret = confirm('メッセージを削除してもよろしいですか？');
	if(ret) {
		$('#id_input').val(id);
		$('#operation_input').val('delete');
		$('#message_operation_form').submit();
	}
}