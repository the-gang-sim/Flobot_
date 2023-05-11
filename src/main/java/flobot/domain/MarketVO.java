package flobot.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("marketVO")
public class MarketVO {
	String marketNum;
	String marketKind;
	Date marketRegiDate;
	String marketId;
	String marketPw;
	String marketName;
	String marketPost;
	String marketAddr;
	String marketAddr2;
	String marketPhone;
	String marketEmail;
	String marketAccount;
	String marketBank;
	Date marketBirth;
}
