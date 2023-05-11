package flobot.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Alias("purVO")
public class PurchaseVO {
	String purchaseNum;
	String purchaseName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date purchase_date;
	String purchasePhone;
	String purchaseAddr;
	String purchaseAddr2;
	String purchasePost;
	String purchaseMessage;
	String purchaseStatus;
	String memberNum;
	
	Integer purchasePrice;
	String [] goodsNums;
}