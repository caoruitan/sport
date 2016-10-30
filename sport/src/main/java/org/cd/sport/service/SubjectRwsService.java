package org.cd.sport.service;

import org.cd.sport.domain.SubjectRws;

/**
 * 课题服务类
 * @author caort
 */
public interface SubjectRwsService {
	
	public SubjectRws getSubjectRwsById(String id);
	
	public SubjectRws getRwsBySubjectId(String subjectId);
	
	public SubjectRws createSubjectRws(String subjectId);
	
	public void saveBaseInfo(String subjectId, String address, String phone, String cooperateOrg);
	
	public void saveYjmb(String subjectId, String yjmb);
	
	public void saveJsgj(String subjectId, String jsgj);
	
	public void saveYjff(String subjectId, String yjff);
	
	public void saveSyfa(String subjectId, String syfa);
	
	public void saveYqjg(String subjectId, String yqjg);
	
	public void saveGztj(String subjectId, String gztj);
	
	public void checkAndSubmit(String subjectId, String basePath);
	
	public void sbadminPass(String subjectId);
	
	public void sbadminUnpass(String subjectId, String comment);
	
	public void orgadminPass(String subjectId);
	
	public void orgadminUnpass(String subjectId, String comment);
	
	public void kjsadminPass(String subjectId);
	
	public void kjsadminUnpass(String subjectId, String comment);
	
}
