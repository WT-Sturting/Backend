package wt.project.sturting.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name = "location")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Location {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "location_id")
	private Long id;

	@Column(columnDefinition = "TEXT")
	private String address;

	private Float latitude;
	private Float longitude;

	@JsonIgnore
	@OneToOne(mappedBy = "location")
	private Study study;
}
