package flobot.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("paymentVO")
public class PaymentVO {
	String purchaseNum;
	String confirmNumber;
	String cardNum; // 카드번호
	String cardCode;  // 
	String tid; // 거래번호
	String totalPrice; // 결제 금액
	String resultMessage; // 결과 메세지
	String payMethod; // 결제 방법
	String applDate; // 승인날짜
	String applTime; // 승인시간
	String CARD_PurchaseName;
}
