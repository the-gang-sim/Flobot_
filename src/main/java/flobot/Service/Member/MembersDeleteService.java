package flobot.Service.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import flobot.Mapper.MemberMapper;

@Service
public class MembersDeleteService {
	@Autowired
	MemberMapper memberMapper;
	public void execute(String memDels []) {
		memberMapper.membersDelete(memDels);
	}
}
