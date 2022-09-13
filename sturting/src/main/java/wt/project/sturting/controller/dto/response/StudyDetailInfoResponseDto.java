package wt.project.sturting.controller.dto.response;

import lombok.Data;
import wt.project.sturting.entity.Location;
import wt.project.sturting.entity.Study;

@Data
public class StudyDetailInfoResponseDto {
	private StudyResponseDto study;
	private LocationResponseDto location;

	public StudyDetailInfoResponseDto(Study study, Location location) {
		this.study = new StudyResponseDto(study);
		this.location = new LocationResponseDto(location);
	}
}
