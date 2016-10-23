package org.cd.sport.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 新闻附件
 * 
 * @author liuyk
 *
 */
@Entity
@Table(name = "SPORT_ATTACHMENT")
public class NewsAttachment {
	/**
	 * 主键id
	 */
	private String id;

	/**
	 * 新闻id
	 */
	private String newsId;

	/**
	 * 附件名称
	 */
	private String name;

	/**
	 * 附件存放名称
	 */
	private String path;

	@Id
	@Column(name = "ID")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "NEWS_ID")
	public String getNewsId() {
		return newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "PATH")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
