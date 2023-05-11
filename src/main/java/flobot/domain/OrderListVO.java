package flobot.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("orderListVO")
public class OrderListVO {
	String goodsName;
	String goodsMain;
	String totalPrice;
	String applDate;
	Integer purchaseQty;
	String purchaseStatus;
	String goodsNum;
	Integer purchasePrice;
	String purchaseNum;
	
	String reviewNum;
}