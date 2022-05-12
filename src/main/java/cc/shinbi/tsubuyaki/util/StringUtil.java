package cc.shinbi.tsubuyaki.util;

//メッセージ投稿時のセキュリティに関するクラス
public class StringUtil {
	//HTMLに関する文字列
	private static String[][] REPLACE_ARRAY = {
			{"&", "&amp;"},
			{"<", "&lt;"},
			{">", "&gt;"},
			{"\"", "&quot;"},
			{" ", "&nbsp"},
			{"\n", "<br>"}
	};
	
    //文字列を変換をする処理
	static public String escapeHtml(String html) {
		String replaced = html;
		for(String[] element : REPLACE_ARRAY) {
			replaced = replaced.replace(element[0], element[1]);
		}
		
		return replaced;
	}
	
	//変換された文字列を戻す処理
	static public String unescapeHtml(String html) {
		String replaced = html;
		for(String[] element : REPLACE_ARRAY) {
			replaced = replaced.replace(element[1], element[0]);
		}
		
		return replaced;
	}
}
