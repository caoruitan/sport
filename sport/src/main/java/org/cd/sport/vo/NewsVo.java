package org.cd.sport.vo;

import java.util.List;

import org.cd.sport.view.FileView;

/**
 * 数据字典查询对象
 * 
 * @author liuyk
 *
 */
public class NewsVo {

	/**
	 * 主键id
	 */
	private String id;

	/**
	 * 栏目
	 */
	private String columnId;

	/**
	 * 栏目
	 */
	private String columnName;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 内容
	 */
	private String content;

	/**
	 * 附件
	 */
	private List<FileView> files;

	/**
	 * 创建时间
	 */
	private String createTime;

	/**
	 * 发布时间
	 */
	private String publishTime;

	/**
	 * 状态
	 */
	private int status;

	/**
	 * 状态
	 */
	private String statusName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getColumnId() {
		return columnId;
	}

	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<FileView> getFiles() {
		return files;
	}

	public void setFiles(List<FileView> files) {
		this.files = files;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
}
