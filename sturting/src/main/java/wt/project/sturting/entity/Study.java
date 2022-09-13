package wt.project.sturting.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wt.project.sturting.entity.enums.StudyMethod;
import wt.project.sturting.entity.enums.StudyStatus;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "study")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Study {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "study_id")
	private Long id;

	@Column(length = 100)
	private String title;

	@Column(columnDefinition = "integer default 1")
	private Integer headcount;

	@Enumerated(EnumType.STRING)
	private StudyStatus studyStatus;

	private LocalDate startDate;

	@Enumerated(EnumType.STRING)
	private StudyMethod method;

	@Column(columnDefinition = "TEXT")
	private String description;

	private LocalDateTime writtenDate;

	@JsonIgnore
	@OneToMany(mappedBy = "study", cascade = CascadeType.ALL, orphanRemoval = true)
	private final List<InterestStudy> interestStudyList = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "study", cascade = CascadeType.ALL, orphanRemoval = true)
	private final List<StudyUser> studyUserList = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "study", cascade = CascadeType.ALL, orphanRemoval = true)
	private final List<Category> categoryList = new ArrayList<>();

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "location_id")
	private Location location;

	@JsonIgnore
	@OneToOne(mappedBy = "study")
	private ChatRoom chatRoom;

	public void setLocation(Location location) {
		this.location = location;
	}
}