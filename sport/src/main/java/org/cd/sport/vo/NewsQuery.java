package org.cd.sport.vo;

/**
 * 数据字典查询对象
 * 
 * @author liuyk
 *
 */
public class NewsQuery {

	private String column;
	private String title;
	private String status;

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
