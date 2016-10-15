package org.cd.sport.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cd.sport.domain.Subject;
import org.cd.sport.hibernate.BaseDaoImpl;
import org.hibernate.Query;

public class SubjectDaoImpl extends BaseDaoImpl<Subject> implements SubjectDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> getAllSubjectList(String year, String type, String stage, int start, int limit) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		Date date = null;
		try {
			date = sdf.parse(year);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder();
		hql.append("from Subject s where s.createTime > :year");
		params.put("year", date);
		if(!type.equals("ALL")) {
			hql.append(" and s.type = :type");
			params.put("type", type);
		}
		if(!stage.equals("ALL")) {
			hql.append(" and s.stage = :stage");
			params.put("stage", stage);
		}
		Query query = this.getHibernateQuery(hql.toString());
		for(String key : params.keySet()) {
			query.setParameter(key, params.get(key));
		}
		return query.setFirstResult(start).setMaxResults(limit).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> getSubjectListByCreator(String creator, String year, String type, String stage, int start, int limit) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		Date date = null;
		try {
			date = sdf.parse(year);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder();
		hql.append("from Subject s where s.creator = :creator and s.createTime > :year");
		params.put("creator", creator);
		params.put("year", date);
		if(!type.equals("ALL")) {
			hql.append(" and s.type = :type");
			params.put("type", type);
		}
		if(!stage.equals("ALL")) {
			hql.append(" and s.stage = :stage");
			params.put("stage", stage);
		}
		Query query = this.getHibernateQuery(hql.toString());
		for(String key : params.keySet()) {
			query.setParameter(key, params.get(key));
		}
		return query.setFirstResult(start).setMaxResults(limit).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> getSubjectListByCreateUnit(String createUnitId, String year, String type, String stage, int start, int limit) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		Date date = null;
		try {
			date = sdf.parse(year);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder();
		hql.append("from Subject s where s.createUnitId = :createUnitId and s.createTime > :year");
		params.put("createUnitId", createUnitId);
		params.put("year", date);
		if(!type.equals("ALL")) {
			hql.append(" and s.type = :type");
			params.put("type", type);
		}
		if(!stage.equals("ALL")) {
			hql.append(" and s.stage = :stage");
			params.put("stage", stage);
		}
		Query query = this.getHibernateQuery(hql.toString());
		for(String key : params.keySet()) {
			query.setParameter(key, params.get(key));
		}
		return query.setFirstResult(start).setMaxResults(limit).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> getSubjectListByOrg(String organizationId, String year, String type, String stage, int start, int limit) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		Date date = null;
		try {
			date = sdf.parse(year);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder();
		hql.append("from Subject s where s.organizationId = :organizationId and s.createTime > :year");
		params.put("organizationId", organizationId);
		params.put("year", date);
		if(!type.equals("ALL")) {
			hql.append(" and s.type = :type");
			params.put("type", type);
		}
		if(!stage.equals("ALL")) {
			hql.append(" and s.stage = :stage");
			params.put("stage", stage);
		}
		Query query = this.getHibernateQuery(hql.toString());
		for(String key : params.keySet()) {
			query.setParameter(key, params.get(key));
		}
		return query.setFirstResult(start).setMaxResults(limit).list();
	}

}
