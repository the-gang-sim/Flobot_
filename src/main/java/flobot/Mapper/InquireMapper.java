package flobot.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import flobot.domain.InquireVO;

@Mapper
public interface InquireMapper {
	public List<InquireVO> inquireList(InquireVO vo);
	
	public Integer inquireInsert(InquireVO vo);
	
	public String inquireNumCreate();
	
	public Integer inquireUpdate(InquireVO vo);
	
	public Integer inquireDelete(String inquireNum);
	
	public List<InquireVO> MarketInquireList(InquireVO vo);
	
	public String SelectMarketNum(String marketNum);
	
	public Integer inquireAnswerUpdate(InquireVO vo);

}
