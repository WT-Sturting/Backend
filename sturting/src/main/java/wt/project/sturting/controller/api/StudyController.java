package wt.project.sturting.controller.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wt.project.sturting.controller.dto.request.StudyCreateDto;
import wt.project.sturting.controller.dto.response.ResponseDto;
import wt.project.sturting.service.StudyService;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/study")
@RestController
public class StudyController {
	private final StudyService studyService;

	@GetMapping("/{studyId}")
	public ResponseDto<?> findStudy(@PathVariable("studyId") Long id) {
		log.info("find id = {}", id);
		return studyService.findOneStudy(id);
//		return null;
	}

	@PostMapping
	public ResponseDto<?> createStudy(@RequestBody StudyCreateDto studyCreateDto) {
		return studyService.createStudyGroup(studyCreateDto);
	}
}
