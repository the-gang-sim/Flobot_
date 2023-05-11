package flobot.Mapper;

import org.apache.ibatis.annotations.Mapper;

import flobot.domain.GoodsIpgoVO;

@Mapper
public interface GoodsIpgoMapper {

	public String goodsIpgoNumCreate();

	public void goodsIpgo(GoodsIpgoVO vo);

}
