package wt.project.sturting.controller.dto.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import wt.project.sturting.entity.Location;
import wt.project.sturting.entity.Study;
import wt.project.sturting.entity.enums.StudyMethod;
import wt.project.sturting.entity.enums.StudyStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class StudyCreateDto {
	private String title;
	private int headcount;
	private StudyMethod studyMethod;

	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private LocalDate startDate;
	private String description;
	private Location location;
	private List<TagRequestDto> tagRequestDtoList;

	public Study toEntity() {
		return Study.builder()
				.title(title)
				.headcount(headcount)
				.method(studyMethod)
				.studyStatus(StudyStatus.RECRUITING)
				.startDate(startDate)
				.description(description)
				.location(location)
				.writtenDate(LocalDateTime.now())
				.build();
	}
}
