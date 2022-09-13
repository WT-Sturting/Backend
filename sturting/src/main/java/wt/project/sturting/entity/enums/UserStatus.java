package wt.project.sturting.entity.enums;

public enum UserStatus {
	NORMAL("정상"), WITHDRAWAL("탈퇴");

	private final String value;

	UserStatus(String value) {
		this.value = value;
	}

	public String getKorValue() {
		return value;
	}
}
