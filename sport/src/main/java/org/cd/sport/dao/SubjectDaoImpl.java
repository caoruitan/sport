package org.cd.sport.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cd.sport.domain.Subject;
import org.cd.sport.hibernate.BaseDaoImpl;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class SubjectDaoImpl extends BaseDaoImpl<Subject> implements SubjectDao {

	@Override
	public void updateEndDates(String[] subjectIds, String sbsEndDate, String rwsEndDate, String subjectEndDate) {
		String sql = "UPDATE SPORT_SUBJECT SET SUBJECT_SBS_END_DATE = :sbsEndDate, SUBJECT_RWS_END_DATE = :rwsEndDate, SUBJECT_END_DATE = :subjectEndDate WHERE SUBJECT_ID IN(:subjectIds)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.getHibernateSqlQuery(sql)
				.setParameter("sbsEndDate", sdf.parse(sbsEndDate)).setParameter("rwsEndDate", sdf.parse(rwsEndDate))
				.setParameter("subjectEndDate", sdf.parse(subjectEndDate)).setParameterList("subjectIds", subjectIds).executeUpdate();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean deleteSubjectById(String sId) {
		String hql = "delete from Subject where id=:id";
		return this.getHibernateQuery(hql).setParameter("id", sId).executeUpdate()!=0;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> getAllSubjectList(String year, String type, String stage, int start, int limit) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder();
		hql.append("from Subject s where date_format(s.createTime,'%Y') = :year");
		params.put("year", year);
		if(type != null && !type.equals("") && !type.equals("ALL")) {
			hql.append(" and s.type = :type");
			params.put("type", type);
		}
		if(stage != null && !stage.equals("") && !stage.equals("ALL")) {
			hql.append(" and s.stage = :stage");
			params.put("stage", stage);
		}
		hql.append(" order by s.createTime desc");
		Query query = this.getHibernateQuery(hql.toString());
		for(String key : params.keySet()) {
			query.setParameter(key, params.get(key));
		}
		return query.setFirstResult(start).setMaxResults(limit).list();
	}
	
	@Override
	public int getAllSubjectCount(String year, String type, String stage) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder();
		hql.append("select count(1) from Subject s where date_format(s.createTime,'%Y') = :year");
		params.put("year", year);
		if(type != null && !type.equals("") && !type.equals("ALL")) {
			hql.append(" and s.type = :type");
			params.put("type", type);
		}
		if(stage != null && !stage.equals("") && !stage.equals("ALL")) {
			hql.append(" and s.stage = :stage");
			params.put("stage", stage);
		}
		Query query = this.getHibernateQuery(hql.toString());
		for(String key : params.keySet()) {
			query.setParameter(key, params.get(key));
		}
		Long count = (Long) query.uniqueResult();
		return count == null ? 0 : count.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> getSubjectListByCreator(String creator, String year, String type, String stage, int start, int limit) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder();
		hql.append("from Subject s where s.creator = :creator and date_format(s.createTime,'%Y') = :year");
		params.put("creator", creator);
		params.put("year", year);
		if(type != null && !type.equals("") && !type.equals("ALL")) {
			hql.append(" and s.type = :type");
			params.put("type", type);
		}
		if(stage != null && !stage.equals("") && !stage.equals("ALL")) {
			hql.append(" and s.stage = :stage");
			params.put("stage", stage);
		}
		hql.append(" order by s.createTime desc");
		Query query = this.getHibernateQuery(hql.toString());
		for(String key : params.keySet()) {
			query.setParameter(key, params.get(key));
		}
		return query.setFirstResult(start).setMaxResults(limit).list();
	}

	@Override
	public int getSubjectCountByCreator(String creator, String year, String type, String stage) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder();
		hql.append("select count(1) from Subject s where s.creator = :creator and date_format(s.createTime,'%Y') = :year");
		params.put("creator", creator);
		params.put("year", year);
		if(type != null && !type.equals("") && !type.equals("ALL")) {
			hql.append(" and s.type = :type");
			params.put("type", type);
		}
		if(stage != null && !stage.equals("") && !stage.equals("ALL")) {
			hql.append(" and s.stage = :stage");
			params.put("stage", stage);
		}
		Query query = this.getHibernateQuery(hql.toString());
		for(String key : params.keySet()) {
			query.setParameter(key, params.get(key));
		}
		Long count = (Long) query.uniqueResult();
		return count == null ? 0 : count.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> getSubjectListByCreateUnit(String createUnitId, String year, String type, String stage, int start, int limit) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder();
		hql.append("from Subject s where s.createUnitId = :createUnitId and date_format(s.createTime,'%Y') = :year");
		params.put("createUnitId", createUnitId);
		params.put("year", year);
		if(type != null && !type.equals("") && !type.equals("ALL")) {
			hql.append(" and s.type = :type");
			params.put("type", type);
		}
		if(stage != null && !stage.equals("") && !stage.equals("ALL")) {
			hql.append(" and s.stage = :stage");
			params.put("stage", stage);
		}
		hql.append(" order by s.createTime desc");
		Query query = this.getHibernateQuery(hql.toString());
		for(String key : params.keySet()) {
			query.setParameter(key, params.get(key));
		}
		return query.setFirstResult(start).setMaxResults(limit).list();
	}

	@Override
	public int getSubjectCountByCreateUnit(String createUnitId, String year, String type, String stage) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder();
		hql.append("select count(1) from Subject s where s.createUnitId = :createUnitId and date_format(s.createTime,'%Y') = :year");
		params.put("createUnitId", createUnitId);
		params.put("year", year);
		if(type != null && !type.equals("") && !type.equals("ALL")) {
			hql.append(" and s.type = :type");
			params.put("type", type);
		}
		if(stage != null && !stage.equals("") && !stage.equals("ALL")) {
			hql.append(" and s.stage = :stage");
			params.put("stage", stage);
		}
		Query query = this.getHibernateQuery(hql.toString());
		for(String key : params.keySet()) {
			query.setParameter(key, params.get(key));
		}
		Long count = (Long) query.uniqueResult();
		return count == null ? 0 : count.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> getSubjectListByOrg(String organizationId, String year, String type, String stage, int start, int limit) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder();
		hql.append("from Subject s where s.organizationId = :organizationId and date_format(s.createTime,'%Y') = :year");
		params.put("organizationId", organizationId);
		params.put("year", year);
		if(type != null && !type.equals("") && !type.equals("ALL")) {
			hql.append(" and s.type = :type");
			params.put("type", type);
		}
		if(stage != null && !stage.equals("") && !stage.equals("ALL")) {
			hql.append(" and s.stage = :stage");
			params.put("stage", stage);
		}
		hql.append(" order by s.createTime desc");
		Query query = this.getHibernateQuery(hql.toString());
		for(String key : params.keySet()) {
			query.setParameter(key, params.get(key));
		}
		return query.setFirstResult(start).setMaxResults(limit).list();
	}

	@Override
	public int getSubjectCountByOrg(String organizationId, String year, String type, String stage) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder();
		hql.append("select count(1) from Subject s where s.organizationId = :organizationId and date_format(s.createTime,'%Y') = :year");
		params.put("organizationId", organizationId);
		params.put("year", year);
		if(type != null && !type.equals("") && !type.equals("ALL")) {
			hql.append(" and s.type = :type");
			params.put("type", type);
		}
		if(stage != null && !stage.equals("") && !stage.equals("ALL")) {
			hql.append(" and s.stage = :stage");
			params.put("stage", stage);
		}
		Query query = this.getHibernateQuery(hql.toString());
		for(String key : params.keySet()) {
			query.setParameter(key, params.get(key));
		}
		Long count = (Long) query.uniqueResult();
		return count == null ? 0 : count.intValue();
	}

	@Override
	public int getAllSubjectCount() {
		String hql = "select count(1) from Subject s";
		Query query = this.getHibernateQuery(hql.toString());
		Long count = (Long) query.uniqueResult();
		return count == null ? 0 : count.intValue();
	}

	@Override
	public int getAllSubjectCountByType(String type) {
		String hql = "select count(1) from Subject s where s.type = :type";
		Query query = this.getHibernateQuery(hql.toString());
		query.setParameter("type", type);
		Long count = (Long) query.uniqueResult();
		return count == null ? 0 : count.intValue();
	}

	@Override
	public int getAllSubjectCountByTypeAndStage(String type, String stage) {
		String hql = "select count(1) from Subject s where s.type = :type and s.stage = :stage";
		Query query = this.getHibernateQuery(hql.toString());
		query.setParameter("type", type);
		query.setParameter("stage", stage);
		Long count = (Long) query.uniqueResult();
		return count == null ? 0 : count.intValue();
	}

	@Override
	public int getSubjectCountByExpert(String expertId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSubjectCountByExpertAndStage(String expertId, String stage) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSubjectCountByOrg(String orgId) {
		String hql = "select count(1) from Subject s where s.organizationId = :organizationId";
		Query query = this.getHibernateQuery(hql.toString());
		query.setParameter("organizationId", orgId);
		Long count = (Long) query.uniqueResult();
		return count == null ? 0 : count.intValue();
	}

	@Override
	public int getSubjectCountByOrgAndStage(String orgId, String stage) {
		String hql = "select count(1) from Subject s where s.organizationId = :organizationId and stage = :stage";
		Query query = this.getHibernateQuery(hql.toString());
		query.setParameter("organizationId", orgId);
		query.setParameter("stage", stage);
		Long count = (Long) query.uniqueResult();
		return count == null ? 0 : count.intValue();
	}

	@Override
	public int getSubjectCountByCreateUnit(String unitId) {
		String hql = "select count(1) from Subject s where s.createUnitId = :createUnitId";
		Query query = this.getHibernateQuery(hql.toString());
		query.setParameter("createUnitId", unitId);
		Long count = (Long) query.uniqueResult();
		return count == null ? 0 : count.intValue();
	}

	@Override
	public int getSubjectCountByCreateUnitAndStage(String unitId, String stage) {
		String hql = "select count(1) from Subject s where s.createUnitId = :createUnitId and s.stage = :stage";
		Query query = this.getHibernateQuery(hql.toString());
		query.setParameter("createUnitId", unitId);
		query.setParameter("stage", stage);
		Long count = (Long) query.uniqueResult();
		return count == null ? 0 : count.intValue();
	}

	@Override
	public int getSubjectCountByCreator(String creator) {
		String hql = "select count(1) from Subject s where s.creator = :creator";
		Query query = this.getHibernateQuery(hql.toString());
		query.setParameter("creator", creator);
		Long count = (Long) query.uniqueResult();
		return count == null ? 0 : count.intValue();
	}

	@Override
	public int getSubjectCountByCreatorAndStage(String creator, String stage) {
		String hql = "select count(1) from Subject s where s.creator = :creator and s.stage = :stage";
		Query query = this.getHibernateQuery(hql.toString());
		query.setParameter("creator", creator);
		query.setParameter("stage", stage);
		Long count = (Long) query.uniqueResult();
		return count == null ? 0 : count.intValue();
	}

	@Override
	public String getMaxSubjectNum(String year, String type) {
		String hql = "from Subject s where s.type = :type and s.num like :year order by s.num desc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", type);
		params.put("year", year + '%');
		List<Subject> subjects = this.getDatasByHQL(hql, params);
		if(subjects != null && subjects.size() > 0) {
			return subjects.get(0).getNum();
		} else {
			return "";
		}
	}
}
