package org.cd.sport.vo;

/**
 * 
 * 键值对对象
 * 
 * @author liuyk
 *
 */
public class KV {
	private String name;
	private String value;

	public KV(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
