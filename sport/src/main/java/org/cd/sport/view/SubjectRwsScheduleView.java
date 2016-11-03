package org.cd.sport.view;

/**
 * 任务书进度安排
 * 
 * @author liuyk
 *
 */
public class SubjectRwsScheduleView {

	private String subjectId;
	/**
	 * 任务书id
	 */
	private String rwsId;

	/**
	 * 主键
	 */
	private String sId;

	/**
	 * 安排时间
	 */
	private String schTime;

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	/**
	 * 工作内容
	 * 
	 */
	private String work;

	/**
	 * 目标
	 */
	private String goal;

	public String getRwsId() {
		return rwsId;
	}

	public void setRwsId(String rwsId) {
		this.rwsId = rwsId;
	}

	public String getsId() {
		return sId;
	}

	public void setsId(String sId) {
		this.sId = sId;
	}

	public String getSchTime() {
		return schTime;
	}

	public void setSchTime(String schTime) {
		this.schTime = schTime;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}
}
