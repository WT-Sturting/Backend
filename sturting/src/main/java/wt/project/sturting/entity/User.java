package wt.project.sturting.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wt.project.sturting.entity.enums.UserMethod;
import wt.project.sturting.entity.enums.UserStatus;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "user")
@Table(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;

	@Column(length = 100)
	private String email;

	@Column(length = 30, unique = true)
	private String id;

	@Column(columnDefinition = "TEXT")
	private String password;

	@Column(columnDefinition = "TEXT")
	private String profileUrl;

	@Enumerated(EnumType.STRING)
	private UserMethod method;

	@Enumerated(EnumType.STRING)
	private UserStatus status;

	@Column(name = "refreshToken")
	private String refreshToken;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private final List<StudyUser> StudyUserList = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private final List<InterestStudy> InterestStudyList = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private final List<Notification> notificationList = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private final List<Message> messageList = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private final List<ChatRoomJoin> chatRoomJoinList = new ArrayList<>();
}

