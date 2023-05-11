package flobot.Command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class GoodsIpgoCommand {
	String goodsIpgoNum;
	String goodsNum;
	Integer ipgoQty;
	Date ipgoDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date madeDate;
	Integer goodsPrice;
}  