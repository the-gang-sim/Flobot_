package flobot.Service;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Service
public class SMSSendService {
	public void smsSend(String _from, String _to, String _content) {
		String api_key = "NCSW6VCZXODXGPOX";
		String api_secret = "UZMGJ9ADCK2LCTIEWQRITSVRXW3J0B3T";
		Message coolsms = new Message(api_key, api_secret);
		
		//       key     value
		HashMap<String , String> params = new HashMap<String , String>();
		params.put("from", _from ); //관리자 전화번호
		params.put("to", _to); ///
		params.put("text", _content);
		params.put("app_version", "JAVA SDK v2.2");
		if(_content.length() > 80) {
			params.put("type", "LMS");
		}else {
			params.put("type", "SMS");
		}
		try {
			JSONObject obj = (JSONObject) coolsms.send(params);
		} catch (CoolsmsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
