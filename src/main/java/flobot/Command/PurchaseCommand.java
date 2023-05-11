package flobot.Command;


import lombok.Data;

@Data
public class PurchaseCommand {
	String purchasePhone;
	String purchaseAddr;
	String purchaseAddr2;
	String purchasePost;
	String purchaseMessage;
	String purchaseName;
	
	Integer totalPrice;
	String goodsNums;
}
