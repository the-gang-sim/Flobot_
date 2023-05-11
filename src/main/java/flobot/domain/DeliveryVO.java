package flobot.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("DeliVO")
public class DeliveryVO {
	String purchaseNum;
	String deliveryNum;
	String deliveryCompany;
}
