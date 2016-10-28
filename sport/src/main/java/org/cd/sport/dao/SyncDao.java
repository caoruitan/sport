package org.cd.sport.dao;

import java.sql.SQLException;

import org.cd.sport.hibernate.IBaseDao;

public interface SyncDao extends IBaseDao {

	public void importSubject() throws SQLException;

	public void importSubjectSbs() throws SQLException;

	public void importSubjectRws() throws SQLException;
}
