package wt.project.sturting.entity.enums;

public enum StudyStatus {
	RECRUITING("모집중"), RECRUITING_COMPLETED("모집완료");

	private final String value;

	StudyStatus(String value) {
		this.value = value;
	}
}
