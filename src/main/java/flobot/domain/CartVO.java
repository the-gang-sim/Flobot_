package flobot.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("cartVO")
public class CartVO {
	String goodsNum;
	Integer qty;
	String memberNum;
	
	String goodsName;
	Integer goodsPrice;
	String goodsMain;
	Integer deliveryCost;
	
	String[] goodsNums;
}
