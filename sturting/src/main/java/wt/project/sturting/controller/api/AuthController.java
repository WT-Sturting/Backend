package wt.project.sturting.controller.api;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wt.project.sturting.controller.dto.request.SignUpRequestDto;
import wt.project.sturting.controller.dto.response.ResponseDto;
import wt.project.sturting.entity.enums.UserMethod;
import wt.project.sturting.service.AuthService;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@RestController
public class AuthController {
	private final AuthService authService;

	@PostMapping("/local/new")
	@ApiOperation(value = "일반 회원가입", notes = "일반 회원가입")
	public ResponseDto<?> join(@RequestBody @Validated SignUpRequestDto signUpRequestDto, BindingResult bindingResult) {
		return authService.join(signUpRequestDto, UserMethod.NORMAL);
	}


	@GetMapping("/local/checkid/{id}")
	@ApiOperation(value = "아이디 중복 확인", notes = "아이디 중복 확인")
	public ResponseDto<?> duplicateCheckId(@PathVariable String id) {
		return authService.duplicateCheckId(id);
	}


	@GetMapping("/local/checkemail/{email}")
	@ApiOperation(value = "이메일 중복 확인", notes = "이메일 중복 확인")
	public ResponseDto<?> duplicateCheckEmail(@PathVariable String email) {
		return authService.duplicateCheckEmail(email);
	}

}
