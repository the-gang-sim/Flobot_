package flobot.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import flobot.domain.MemberVO;

@Mapper
public interface MemberMapper {
	
	public List<MemberVO> memberAllSelect(String memberWord);
	public String autoNum();
	public Integer memberInsert(MemberVO vo);
	public MemberVO memberOneSelect(String memberNum);
	public Integer memberUpdate(MemberVO vo);
	public Integer memberDelete(String memberNum);
	public Integer membersDelete(String memDels []); // 여러개 삭제

}
