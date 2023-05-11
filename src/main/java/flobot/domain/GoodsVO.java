package flobot.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("goodsVO")
public class GoodsVO {
	String goodsNum;
	String goodsName;
	String goodsContent;
	Integer deliveryCost;
	String manufacturer;
	Integer visitCount;
	String marketNum;
	String goodsKind;
	Date RegiDate;
	Date updateRegiDate;
	String goodsMain;
	String goodsImage;
	String goodsMainOrg;
	String goodsImageOrg;
	
	Integer totalQty;
	Integer goodsPrice;
	String marketName;
	Integer reviewScore;
	Integer reviewCount;
}

