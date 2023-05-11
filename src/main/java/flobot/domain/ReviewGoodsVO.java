package flobot.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("regoodsVO")
public class ReviewGoodsVO {
	ReviewVO reviewVO;
	GoodsVO goodsVO;
}
