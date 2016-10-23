package org.cd.sport.view;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.utils.Nullable;

public class FileView implements Nullable {
	private String id;
	private String name;
	private String path;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public boolean isNull() {
		return StringUtils.isBlank(id) || StringUtils.isBlank(name) || StringUtils.isBlank(path);
	}
}
