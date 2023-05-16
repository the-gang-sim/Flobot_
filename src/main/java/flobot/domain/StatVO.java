package flobot.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("statVO")
public class StatVO {
	String statYear;
	String statQut;
	Double statSales;
	Double statProfit;
	Double statTotprofit;

}
