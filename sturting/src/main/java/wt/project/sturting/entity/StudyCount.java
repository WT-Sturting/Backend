package wt.project.sturting.entity;

import wt.project.sturting.entity.enums.StudyMethod;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class StudyCount {
	@Id
	@Enumerated(EnumType.STRING)
	private StudyMethod studyMethod;

	private long count;

	public void increase() {
		count++;
	}

	public void decrease() {
		count--;
	}
}
