package wt.project.sturting.controller.dto.response;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import wt.project.sturting.entity.Study;
import wt.project.sturting.entity.enums.StudyMethod;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class StudyResponseDto {
	private String title;
	private int headcount;
	private LocalDateTime writtenDate;
	private LocalDate startDate;
	private StudyMethod studyMethod;
	private String description;

	public StudyResponseDto(Study study) {
		this.title = study.getTitle();
		this.headcount = study.getHeadcount();
		this.writtenDate = study.getWrittenDate();
		this.startDate = study.getStartDate();
		this.studyMethod = study.getMethod();
		this.description = study.getDescription();
	}
}
