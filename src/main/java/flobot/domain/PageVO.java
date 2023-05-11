package flobot.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("pageVO")
public class PageVO {
	int startRow;
	int endRow;
	String keyword;
}
