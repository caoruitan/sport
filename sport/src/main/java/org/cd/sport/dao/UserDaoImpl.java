package org.cd.sport.dao;

import java.util.List;

import org.cd.sport.domain.UserDomain;
import org.cd.sport.hibernate.BaseDaoImpl;
import org.cd.sport.vo.UserVo;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends BaseDaoImpl<UserDomain> implements UserDao {

	@Override
	public UserDomain findById(String id) {
		return this.getEntityById(UserDomain.class, id);
	}

	@Override
	public UserDomain findByLoginName(String loginName) {
		return (UserDomain) this.getHibernateQuery("from UserDomain where loginName=:loginName")
				.setParameter("loginName", loginName).uniqueResult();
	}

	@Override
	public List<UserVo> findByRole(String role, int start, int limit) {
		String querySql = "SELECT RB.BIDDING_ID AS \"bidId\" ,RB.USER_ID AS \"userId\", RB.STATUS AS \"status\",U.CREDIT AS \"credit\",U.ABILITY AS \"ability\",U.GOOD_RATIO AS \"goodRatio\",U.INCOME AS \"income\",U.TRADE_TOTAL AS \"tradeTotal\",RB.BID_NO AS \"bidNo\",RB.BIDDING_TIME AS \"bidTime\" "
				+ " ,RB.BROWSE AS \"browse\",F.FILE_ID AS \"fileId\",F.FILE_NAME AS \"fileName\",RB.DESCRIPTION AS \"content\",RB.WIN_LEVEL AS \"level\",DD.COMMENT_TOTAL AS \"commentTotal\"  FROM BH_REWARD_BIDDING RB LEFT JOIN BH_USER_STATISTICS_SWORDMAN U ON U.USER_ID = RB.USER_ID "
				+ " LEFT JOIN (SELECT COUNT(DATA_ID) AS \"COMMENT_TOTAL\",DATA_ID FROM  BH_REWARD_BIDDING_MESSAGE GROUP BY DATA_ID) DD ON DD.DATA_ID=RB.BIDDING_ID "
				+ " LEFT JOIN (SELECT BIDDING_ID,GROUP_CONCAT(FILE_ID) AS \"FILE_ID\", GROUP_CONCAT(FILE_NAME) AS \"FILE_NAME\" FROM BH_REWARD_BIDDING_FILE GROUP BY BIDDING_ID ) F ON F.BIDDING_ID=RB.BIDDING_ID WHERE RB.REWARD_ID=:rewardId AND RB.STATUS IN (:status) ORDER BY RB.BIDDING_TIME DESC ";
		SQLQuery hibernateSqlQuery = this.getHibernateSqlQuery(querySql);
		hibernateSqlQuery.addScalar("bidId");
		hibernateSqlQuery.addScalar("userId");
		hibernateSqlQuery.addScalar("status");
		hibernateSqlQuery.addScalar("credit", StandardBasicTypes.INTEGER);
		hibernateSqlQuery.addScalar("ability", StandardBasicTypes.INTEGER);
		hibernateSqlQuery.addScalar("goodRatio", StandardBasicTypes.DOUBLE);
		hibernateSqlQuery.addScalar("income", StandardBasicTypes.BIG_DECIMAL);
		hibernateSqlQuery.addScalar("bidNo", StandardBasicTypes.LONG);
		hibernateSqlQuery.addScalar("bidTime");
		hibernateSqlQuery.addScalar("browse", StandardBasicTypes.INTEGER);
		hibernateSqlQuery.addScalar("tradeTotal", StandardBasicTypes.LONG);
		hibernateSqlQuery.addScalar("fileId");
		hibernateSqlQuery.addScalar("commentTotal", StandardBasicTypes.LONG);
		hibernateSqlQuery.addScalar("fileName");
		hibernateSqlQuery.addScalar("content", StandardBasicTypes.STRING);
		hibernateSqlQuery.addScalar("level", StandardBasicTypes.INTEGER);
		hibernateSqlQuery.setResultTransformer(Transformers.aliasToBean(UserVo.class));
		hibernateSqlQuery.setMaxResults(limit);
		hibernateSqlQuery.setFirstResult(start);
		return hibernateSqlQuery.list();
	}

	@Override
	public List<UserVo> find(int start, int limit) {
		// String queryHql = "from UserDomain";
		// return
		// this.getHibernateQuery(queryHql).setFirstResult(start).setMaxResults(limit).list();
		return null;
	}

	@Override
	public long count(String role) {
		String updateHql = "select count(1) from UserDomain where role=:role";
		Long count = (Long) this.getHibernateQuery(updateHql).setParameter("role", role).uniqueResult();
		return count == null ? 0 : count.intValue();
	}

	@Override
	public long count() {
		String updateHql = "select count(1) from UserDomain";
		Long count = (Long) this.getHibernateQuery(updateHql).uniqueResult();
		return count == null ? 0 : count.intValue();
	}

	@Override
	public boolean deleteById(String userId) {
		String deleteHql = "delete from UserDomain where userId=:userId";
		return this.getHibernateQuery(deleteHql).setParameter("userId", userId).executeUpdate() != 0;
	}

	@Override
	public boolean deleteById(String[] userId) {
		String deleteHql = "delete from UserDomain where userId in (:userId)";
		return this.getHibernateQuery(deleteHql).setParameterList("userId", userId).executeUpdate() != 0;
	}

}