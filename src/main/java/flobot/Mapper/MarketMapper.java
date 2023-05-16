package flobot.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import flobot.domain.MarketVO;
import flobot.domain.StatVO;

@Mapper
public interface MarketMapper {

	public int marketInsert(MarketVO vo);

	public MarketVO marketSelectOne(String marketNum);

	public int marketUpdate(MarketVO vo);

	public void marketDelete(String userId);
	
	public List<MarketVO> marketAllSelect(String marketWord);
	
	public Integer marketsDelete(String marketDels []);
	
	public MarketVO marketOneDetail(String marketNum);

	public List<StatVO> statList(String userNum);
	
}
