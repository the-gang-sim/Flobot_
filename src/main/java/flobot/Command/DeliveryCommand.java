package flobot.Command;

import lombok.Data;

@Data
public class DeliveryCommand {
	String purchaseNum;
	String deliveryNum;
	String deliveryCompany;
}