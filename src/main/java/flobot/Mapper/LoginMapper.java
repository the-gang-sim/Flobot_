package flobot.Mapper;

import org.apache.ibatis.annotations.Mapper;

import flobot.domain.AuthInfoVO;
import flobot.domain.MemberVO;

@Mapper
public interface LoginMapper {

	public AuthInfoVO login(String userId);

	public MemberVO resetPw(String uesrEmail);

	public void updatePw(MemberVO vo);

	public MemberVO findId(String userName);
	
}
