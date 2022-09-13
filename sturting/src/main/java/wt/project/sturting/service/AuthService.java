package wt.project.sturting.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wt.project.sturting.controller.dto.request.SignUpRequestDto;
import wt.project.sturting.controller.dto.response.ResponseDto;
import wt.project.sturting.controller.dto.response.ResultResponseDto;
import wt.project.sturting.entity.User;
import wt.project.sturting.entity.enums.UserMethod;
import wt.project.sturting.entity.enums.UserStatus;
import wt.project.sturting.repository.UserRepository;

@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private static final String basicUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRlx2rvqRVwn6S5uXPkHl856CcYvV2z8bUMyw&usqp=CAU";
	private static final UserStatus basicStatus = UserStatus.NORMAL;

	public ResponseDto<?> duplicateCheckId(String id) {
		boolean checkId = userRepository.findById(id).isEmpty();

		return ResponseDto.builder()
				.code(200)
				.message("success to duplicate-check-id")
				.data(new ResultResponseDto(checkId))
				.build();
	}

	public ResponseDto<?> duplicateCheckEmail(String email) {
		boolean checkEmail = userRepository.findByEmailAndMethod(email, UserMethod.NORMAL) == null;

		return ResponseDto.builder()
				.code(200)
				.message("success to duplicate-check-email")
				.data(new ResultResponseDto(checkEmail))
				.build();
	}

	@Transactional
	public ResponseDto<?> join(SignUpRequestDto signUpRequestDto, UserMethod method) {
		String id = signUpRequestDto.getId();
		String email = signUpRequestDto.getEmail();
		String password = signUpRequestDto.getPassword();
		String encodedPassword = passwordEncoder.encode(password);
		User saveEntity = User.builder()
				.email(email)
				.id(id).password(encodedPassword)
				.profileUrl(basicUrl)
				.status(basicStatus)
				.method(method).build();

		try {
			userRepository.save(saveEntity);
		} catch (DataAccessException e) { // 데이터베이스 저장 오류

		}

		return ResponseDto.builder()
				.code(200)
				.message( "success to join")
				.build();
	}
}
