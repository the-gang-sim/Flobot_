package flobot.Mapper;

import org.apache.ibatis.annotations.Mapper;

import flobot.domain.MemberVO;

@Mapper
public interface MemberShipMapper {
	public Integer MemberShipInsert(MemberVO vo);
	public String memberNumCreate();
	public void memberEmailChk(String chk);
	public void memberPwModipy(MemberVO vo);
	public MemberVO myInfoSelect(String userId);

}
