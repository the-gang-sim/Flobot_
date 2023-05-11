package flobot.Command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GoodsCommand {
	String goodsNum;
	@NotBlank(message="상품명을 입력해주세요.")
	String goodsName;
	@NotBlank(message="상품설명을 입력해주세요.")
	String goodsContent;
	Integer deliveryCost;
	@NotBlank(message="제조사를 입력해주세요.")
	String manufacturer;
	Integer visitCount;
	String marketNum;
	@NotBlank(message="상품품목을 입력해주세요.")
	String goodsKind;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date RegiDate;
	Date updateRegiDate;
	MultipartFile goodsMain;
	MultipartFile goodsImage [];
}