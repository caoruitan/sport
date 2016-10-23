package org.cd.sport.view;

import java.util.List;

/**
 * 新闻视图对象
 * 
 * @author liuyk
 *
 */
public class NewsView {
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
	 * 附件
	 */
	private List<FileView> files;

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
}
