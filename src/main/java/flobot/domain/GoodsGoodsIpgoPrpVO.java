package flobot.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("GIPVO")
public class GoodsGoodsIpgoPrpVO {
	PrpVO prpVO;
	GoodsVO goodsVO;
	GoodsIpgoVO ipgoVO;
}
