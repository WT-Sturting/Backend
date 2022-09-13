package wt.project.sturting.entity.enums;

public enum StudyUserRole {
	LEADER("방장"), PARTICIPANT("참여자"), APPLICANT("신청자");

	private final String value;

	StudyUserRole(String value) {
		this.value = value;
	}
}
