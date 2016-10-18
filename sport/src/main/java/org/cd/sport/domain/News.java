package org.cd.sport.domain;

import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
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
	private String columnId;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 附件id
	 */
	private String fileId;

	/**
	 * 附件名称
	 */
	private String fileName;

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

	@Column(name = "COLUMN_ID")
	public String getColumnId() {
		return columnId;
	}

	public void setColumnId(String column) {
		this.columnId = column;
	}

	@Column(name = "TITLE")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Lob
	@Basic(fetch = FetchType.LAZY) 
	@Column(name = "CONTENT", columnDefinition = "CLOB")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "FILE_ID")
	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
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

	@Column(name = "FILE_NAME")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
