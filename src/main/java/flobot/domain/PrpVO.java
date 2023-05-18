package flobot.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("prpVO")
public class PrpVO {
	String goodsNum;
	Long brilliance;
	Long deliCharge;
	Long rootYn;
	Integer flower=0;
	Integer seed=0;
	Integer herb=0;
	Integer tree=0;
	Integer fleshy=0;
}
