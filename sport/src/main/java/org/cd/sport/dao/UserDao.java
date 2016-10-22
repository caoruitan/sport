package org.cd.sport.dao;

import java.util.List;

import org.cd.sport.domain.UserDomain;
import org.cd.sport.hibernate.IBaseDao;
import org.cd.sport.vo.UserQuery;
import org.cd.sport.vo.UserVo;

/**
 * 用户数据接口
 * 
 * @author liuyk
 *
 */
public interface UserDao extends IBaseDao {

	public boolean deleteById(String id);

	public boolean deleteById(String[] id);

	public UserDomain findById(String id);

	public UserVo findVoById(String id);

	public UserVo findMangerByOrgId(String orgId);

	public UserVo findVoByLoginName(String loginName);

	public UserDomain findByLoginName(String loginName);

	public List<UserDomain> findByRole(String[] role, int start, int limit);

	public List<UserDomain> findByRole(String[] role, String name, int start, int limit);

	public List<UserDomain> find(int start, int limit);

	public List<UserVo> findVoByOrgId(String orgId, int start, int limit);

	public List<UserVo> findVoByWhere(UserQuery query, int start, int limit);

	public long findTotal(String[] role);

	public long findTotal(String[] role, String name);

	public long findTotalByOrgId(String orgId);

	public long findTotalByWhere(UserQuery query);

	public long findTotal();

}
