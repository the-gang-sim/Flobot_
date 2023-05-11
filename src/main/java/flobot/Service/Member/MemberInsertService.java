package flobot.Service.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import flobot.Command.MemberCommand;
import flobot.Mapper.MemberMapper;
import flobot.domain.MemberVO;

@Service
public class MemberInsertService {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	MemberMapper memberMapper;
	public void execute(MemberCommand memberCommand) {
		MemberVO vo = new MemberVO();
		vo.setMemberAddr(memberCommand.getMemberAddr());
		vo.setMemberAddr2(memberCommand.getMemberAddr2());
		vo.setMemberBirth(memberCommand.getMemberBirth());
		vo.setMemberEmail(memberCommand.getMemberEmail());
		vo.setMemberGender(memberCommand.getMemberGender());
		vo.setMemberId(memberCommand.getMemberId());
		vo.setMemberName(memberCommand.getMemberName());
		vo.setMemberNum(memberCommand.getMemberNum());
		vo.setMemberPhone(memberCommand.getMemberPhone());
		vo.setMemberPost(memberCommand.getMemberPost());
		vo.setMemberMarried(memberCommand.getMemberMarried());
		vo.setMemberPw( passwordEncoder.encode(memberCommand.getMemberPw()));
		memberMapper.memberInsert(vo);
		
	}
}
