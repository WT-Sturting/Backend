package wt.project.sturting.entity.enums;

public enum MessageType {
	ENTRANCE("입장"), EXIT("퇴장"), TRANSMISSION("전송");

	private final String value;

	MessageType(String value) {
		this.value = value;
	}
}
