package flobot.Service.iniPay;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.inicis.std.util.SignatureUtil;

import flobot.Mapper.PurchaseMapper;
import flobot.domain.PurchaseVO;
import jakarta.servlet.http.HttpSession;

@Service
public class IniPayReqService {
	@Autowired
	PurchaseMapper purchaseMapper;
	public void execute(String purchaseNum,Integer totalPrice,
			HttpSession session,Model model) throws Exception{
		String mid					= "INIpayTest";		                    // 상점아이디					
		String signKey			    = "SU5JTElURV9UUklQTEVERVNfS0VZU1RS";	// 웹 결제 signkey
		
		String mKey = SignatureUtil.hash(signKey, "SHA-256");

		String timestamp			= SignatureUtil.getTimestamp();			// util에 의해서 자동생성
		String orderNumber			= purchaseNum; //mid+"_"+SignatureUtil.getTimestamp();	// 가맹점 주문번호(가맹점에서 직접 설정)
		String price				= totalPrice.toString();								// 상품가격(특수기호 제외, 가맹점에서 직접 설정)


		Map<String, String> signParam = new HashMap<String, String>();

		signParam.put("oid", orderNumber);
		signParam.put("price", price);
		signParam.put("timestamp", timestamp);
		
		String signature = SignatureUtil.makeSignature(signParam);

		PurchaseVO vo = purchaseMapper.purchaseSelect(purchaseNum);
		vo.setPurchasePrice(totalPrice);

		model.addAttribute("timestamp", timestamp);
		model.addAttribute("mid", mid);
		model.addAttribute("signature", signature);
		model.addAttribute("mKey", mKey);
		model.addAttribute("vo", vo);
	}

}
