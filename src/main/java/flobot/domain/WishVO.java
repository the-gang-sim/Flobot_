package flobot.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("wishVO")
public class WishVO {
	String goodsNum;
	String memberNum;
	Date wishDate;

}
