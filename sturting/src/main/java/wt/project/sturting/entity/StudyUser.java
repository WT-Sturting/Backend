package wt.project.sturting.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wt.project.sturting.entity.enums.StudyUserRole;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "study_user")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudyUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "study_user_id")
	private Long id;

	@Enumerated(EnumType.STRING)
	private StudyUserRole role;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "study_id")
	private Study study;

	@Column(columnDefinition = "TEXT")
	private String motive;
}
