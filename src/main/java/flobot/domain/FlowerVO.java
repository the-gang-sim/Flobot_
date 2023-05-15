package flobot.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("flowerVO")
public class FlowerVO {
	String flowerNum;
	String flowerName;
	String flowerKind;
	String flowerContent;
	String flowerImage;

}
