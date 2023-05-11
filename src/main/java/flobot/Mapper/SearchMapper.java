package flobot.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import flobot.domain.GoodsVO;
import flobot.domain.PageVO;

@Mapper
public interface SearchMapper {

	public List<GoodsVO> goodsSearch(PageVO vo);

	public Integer goodsCount(String keyword);

}
