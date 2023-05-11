package flobot.Service.MemberShip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import flobot.Mapper.MemberShipMapper;

@Service
public class EmailCheckService {
	@Autowired
	MemberShipMapper memberShipMapper;
	public void execute(String chk) {
		memberShipMapper.memberEmailChk(chk);
	}
	
}
