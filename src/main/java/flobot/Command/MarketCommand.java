package flobot.Command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MarketCommand {
	@NotBlank(message="사업자등록번호를 입력해주세요.")
	String marketNum;
	@NotBlank(message="업종을 입력해주세요.")
	String marketKind;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "날짜를 입력하여 주세요.")
	Date marketRegiDate;
	@NotBlank(message="아이디를 입력해주세요.")
	String marketId;
	@Pattern(regexp = "^(?=.*?[A-Za-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-+]).{8,}$",
			 message = "영문자와 숫자 그리고 특수문자가 포함된 8글자 이상")
	String marketPw;
	@NotBlank(message="비밀번호를 재입력해주세요.")
	String marketPwCon;
	@NotBlank(message="업체명을 입력해주세요.")
	String marketName;
	@NotBlank(message="주소를 입력해주세요.")
	String marketPost;
	String marketAddr;
	String marketAddr2;
	@NotBlank(message="연락처을 입력해주세요.")
	String marketPhone;
	@NotBlank(message="이메일을 입력해주세요.")
	String marketEmail;
	String marketAccount;
	String marketBank;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date marketBirth;
}