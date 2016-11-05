package org.cd.sport.service;

import java.util.List;

import org.cd.sport.domain.SubjectRwsUndertaker;
import org.cd.sport.view.SubjectRwsUndertakerView;
import org.cd.sport.vo.SubjectRwsUndertakerVo;

/**
 * 任务书承担着
 * 
 * @author liuyk
 */
public interface SubjectRwsUndertakerService {

	public boolean create(SubjectRwsUndertakerView view);

	public boolean update(SubjectRwsUndertakerView view);

	public boolean deleteById(String id);

	public boolean deleteById(String[] id);

	public SubjectRwsUndertaker getById(String id);

	public List<SubjectRwsUndertakerVo> getByRwsId(String sbsId);

	public List<SubjectRwsUndertaker> getByRwsId(String sbsId, int start, int limit);

	public long getTotalByRwsId(String sbsId);

}
