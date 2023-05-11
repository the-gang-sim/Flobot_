package flobot.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("reviewVO")
public class ReviewVO {
	String purchaseNum;
	String goodsNum;
	String reviewNum;
	Date reviewDate;
	String reviewContent;
	Integer reviewScore;

	String goodsName;
	String goodsMain;
	String goodsContent;
	String memberNum;
}
