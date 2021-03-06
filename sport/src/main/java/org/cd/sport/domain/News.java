package org.cd.sport.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 新闻对象
 * 
 * @author liuyk
 *
 */
@Entity
@Table(name = "SPORT_NEWS")
public class News {

	/**
	 * 主键id
	 */
	private String id;

	/**
	 * 栏目
	 */
	private Integer columnId;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 内容
	 */
	private byte[] content;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 发布时间
	 */
	private Date publishTime;

	/**
	 * 状态
	 */
	private int status;

	@Id
	@Column(name = "ID")
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "TITLE")
	public String getTitle() {
		return title;
	}

	@Column(name = "COLUMN_ID")
	public Integer getColumnId() {
		return columnId;
	}

	public void setColumnId(Integer columnId) {
		this.columnId = columnId;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	@Column(name = "CONTENT")
	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}


	@Column(name = "CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "PUBLISH_TIME")
	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	@Column(name = "STATUS")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
