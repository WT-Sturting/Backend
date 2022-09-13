package wt.project.sturting.entity.enums;

public enum NotificationStatus {
	SUCCESS("성공"), FAIL("실패");

	private final String value;

	NotificationStatus(String value) {
		this.value = value;
	}
}
