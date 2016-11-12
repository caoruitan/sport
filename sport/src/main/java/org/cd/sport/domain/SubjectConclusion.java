package org.cd.sport.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 结题报告
 * 
 * 
 * @author liuyk
 *
 */
@Entity
@Table(name = "SPORT_SUBJECT_CONCLUSION")
public class SubjectConclusion {

	private String subjectId;
	private String conclusionId;

	@Column(name = "SUBJECT_ID")
	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "guid")
	@Column(name = "CONCLUSION_ID")
	public String getConclusionId() {
		return conclusionId;
	}

	public void setConclusionId(String conclusionId) {
		this.conclusionId = conclusionId;
	}

}
