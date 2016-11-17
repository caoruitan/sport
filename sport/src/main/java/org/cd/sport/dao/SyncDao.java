package org.cd.sport.dao;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import org.cd.sport.hibernate.IBaseDao;

public interface SyncDao extends IBaseDao {
	public void sync() throws SQLException, UnsupportedEncodingException;
}
