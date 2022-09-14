package wt.project.sturting.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wt.project.sturting.controller.dto.request.StudyCreateDto;
import wt.project.sturting.controller.dto.request.TagRequestDto;
import wt.project.sturting.controller.dto.response.LocationResponseDto;
import wt.project.sturting.controller.dto.response.ResponseDto;
import wt.project.sturting.controller.dto.response.StudyDetailInfoResponseDto;
import wt.project.sturting.controller.dto.response.StudyResponseDto;
import wt.project.sturting.entity.Category;
import wt.project.sturting.entity.ChatRoom;
import wt.project.sturting.entity.Location;
import wt.project.sturting.entity.Study;
import wt.project.sturting.entity.StudyCount;
import wt.project.sturting.entity.StudyUser;
import wt.project.sturting.entity.Tag;
import wt.project.sturting.entity.User;
import wt.project.sturting.entity.enums.StudyUserRole;
import wt.project.sturting.repository.CategoryRepository;
import wt.project.sturting.repository.ChatRoomRepository;
import wt.project.sturting.repository.LocationRepository;
import wt.project.sturting.repository.StudyCountRepository;
import wt.project.sturting.repository.StudyRepository;
import wt.project.sturting.repository.StudyUserRepository;
import wt.project.sturting.repository.TagRepository;
import wt.project.sturting.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class StudyService {
	private final StudyRepository studyRepository;
	private final TagRepository tagRepository;
	private final CategoryRepository categoryRepository;
	private final UserRepository userRepository;
	private final StudyUserRepository studyUserRepository;
	private final ChatRoomRepository chatRoomRepository;
	private final StudyCountRepository studyCountRepository;
	private final LocationRepository locationRepository;

	/**
	 * study group 생성 method
	 * 1. 사용자로부터 스터디 정보 받아오기
	 * 2. 존재하지 않는 Tag 에 대해서는 새로 생성 후 향후 Study 와 연관지어 줄 예정.
	 * 3. Tag 를 Study 와 연관지어주기 위해서 Category 객체 사용. 생성후 Tag-Study 를 연관지어 준다.
	 * 4. 현재 Study group 을 생성하려는 사용자 계정을 study leader 로 정한 뒤 StudyUser 에 저장.
	 * 5. Chat room 생성
	 * 6. 대면/비대면 여부에 따라 스터디 개수 1 증가.
	 * 7. Study 생성
	 *
	 * @param studyCreateDto study info. tags, detail info, chat room
	 * @return success message
	 */
	@Transactional
	public ResponseDto<?> createStudyGroup(StudyCreateDto studyCreateDto) {
		Study study = studyCreateDto.toEntity();
		Location location = study.getLocation();
		locationRepository.save(location);
		study.setLocation(location);

		List<TagRequestDto> tagRequestDtoList = studyCreateDto.getTagRequestDtoList();
		List<Tag> studyTagList = new ArrayList<>();

		for (TagRequestDto tagRequestDto : tagRequestDtoList) {
			Optional<Tag> tagName = tagRepository.findByTagName(tagRequestDto.getTagName());
			if (tagName.isEmpty()) {
				Tag tag = Tag.builder()
						.tagName(tagRequestDto.getTagName())
						.build();
				tagRepository.save(tag);
				studyTagList.add(tag);
			} else {
				studyTagList.add(tagName.get());
			}
		}

		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<User> loginUser = userRepository.findById(id);
		/**
		 * test 위해서 제거. 사용자 로그인과 함께 확인해야 할 때에는 주석 제거 해야합니다
		 */
//		if (loginUser.isEmpty()) {
//			throw new IllegalArgumentException("존재하지 않는 로그인 계정입니다");
//		}

//		User studyLeader = loginUser.get();
		StudyUser studyUser = StudyUser.builder()
				.study(study)
				.user(null)
				.role(StudyUserRole.LEADER)
				.build();
		studyUserRepository.save(studyUser);

		ChatRoom chatRoom = ChatRoom.builder()
				.study(study)
				.createDate(LocalDateTime.now())
				.build();
		chatRoomRepository.save(chatRoom);

		Optional<StudyCount> counter = studyCountRepository.findByStudyMethod(study.getMethod());
		if (counter.isPresent()) {
			StudyCount studyCount = counter.get();
			studyCount.increase();
		}
		studyRepository.save(study);

		for (Tag studyTag : studyTagList) {
			Category category = Category.builder()
					.study(study)
					.tag(studyTag)
					.build();
			categoryRepository.save(category);
		}

		return ResponseDto.builder()
				.code(200)
				.message("success to create study group")
				.data(study)
				.build();
	}

	public ResponseDto<?> findOneStudy(Long id) {
		Optional<Study> study = studyRepository.findById(id);
		if (study.isPresent()) {
			Study studyEntity = study.get();
			Location location = studyEntity.getLocation();

			return ResponseDto.builder()
					.code(200)
					.message("success to find study")
					.data(new StudyDetailInfoResponseDto(studyEntity, location))
					.build();
		}

		return ResponseDto.builder()
				.code(404)
				.message("fail to find study")
				.build();
	}
}
