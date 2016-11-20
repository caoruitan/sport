package org.cd.sport.dao;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import org.cd.sport.hibernate.IBaseDao;

public interface SyncDao extends IBaseDao {
	public void sync() throws SQLException, UnsupportedEncodingException;

	public void importUser() throws SQLException;

	public void importOrg() throws SQLException;

	public void importNews() throws SQLException, UnsupportedEncodingException;

	public void importRwsAppropriation() throws SQLException;

	public void importRwsBudget() throws SQLException;

	public void importRwsDevice() throws SQLException;

	public void importRwsSchedule() throws SQLException;

	public void importRwsUndertaker() throws SQLException;

	public void importSbsBudget() throws SQLException;

	public void importSbsProposer() throws SQLException;

	public void importSubject() throws SQLException;

	public void importSubjectRws() throws SQLException;

	public void importSubjectSbs() throws SQLException;
}
