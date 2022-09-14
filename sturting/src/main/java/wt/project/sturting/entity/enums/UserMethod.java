package wt.project.sturting.entity.enums;

public enum UserMethod {
	NORMAL("일반"), KAKAO("카카오");

	private final String value;

	UserMethod(String value) {
		this.value = value;
	}

	public String getKorValue() {
		return value;
	}
}
