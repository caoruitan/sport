package org.cd.sport.action;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.constant.Constants;
import org.cd.sport.exception.ParameterIsWrongException;
import org.cd.sport.service.NewsService;
import org.cd.sport.support.SportSupport;
import org.cd.sport.utils.GsonUtils;
import org.cd.sport.utils.PageModel;
import org.cd.sport.utils.PageWrite;
import org.cd.sport.view.NewsView;
import org.cd.sport.vo.NewsQuery;
import org.cd.sport.vo.NewsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 新闻管理
 * 
 * @author liuyk
 *
 */
@Controller
@RequestMapping("news")
public class NewsAction {

	@Autowired
	private NewsService newsSevice;

	@RequestMapping(value = "/kjsadmin/list.htm")
	private String kjsadminIndex(HttpServletRequest request) {
		Map<Integer, String> columns = Constants.News.getColumns();
		request.setAttribute("columns", columns);
		Map<Integer, String> status = Constants.News.getStatus();
		request.setAttribute("status", status);
		return "news/list";
	}

	@RequestMapping(value = "/kjsadmin/create.htm", method = RequestMethod.GET)
	private String createPage(HttpServletRequest request) {
		Map<Integer, String> columns = Constants.News.getColumns();
		request.setAttribute("columns", columns);
		return "news/create";
	}

	@RequestMapping(value = "/kjsadmin/create.action", method = RequestMethod.POST)
	private void create(NewsView news, HttpServletRequest request, HttpServletResponse response) {
		boolean create = newsSevice.create(news);
		PageWrite.writeTOPage(response, create);
	}

	@RequestMapping(value = "/kjsadmin/update.htm", method = RequestMethod.GET)
	private String updatePage(String newsId, HttpServletRequest request) {
		if (StringUtils.isBlank(newsId)) {
			throw new ParameterIsWrongException("参数错误!");
		}
		NewsVo news = this.newsSevice.getById(newsId);
		if (news == null) {
			throw new EntityNotFoundException("新闻对象不存在!");
		}
		Map<Integer, String> columns = Constants.News.getColumns();
		request.setAttribute("columns", columns);
		request.setAttribute("news", news);
		return "news/update";
	}

	@RequestMapping(value = "/kjsadmin/update.action", method = RequestMethod.POST)
	private void update(NewsView news, HttpServletRequest request, HttpServletResponse response) {
		boolean create = newsSevice.update(news);
		PageWrite.writeTOPage(response, create);
	}

	@RequestMapping(value = "/kjsadmin/publish.action", method = RequestMethod.POST)
	private void publish(String newsIds, HttpServletRequest request, HttpServletResponse response) {
		boolean create = false;
		if (!StringUtils.isBlank(newsIds)) {
			create = newsSevice.publish(newsIds.split(","));
		}
		PageWrite.writeTOPage(response, create);
	}

	@RequestMapping(value = "/kjsadmin/unpublish.action", method = RequestMethod.POST)
	private void unpublish(String newsIds, HttpServletRequest request, HttpServletResponse response) {
		boolean create = false;
		if (!StringUtils.isBlank(newsIds)) {
			create = newsSevice.unpublish(newsIds.split(","));
		}
		PageWrite.writeTOPage(response, create);
	}

	@RequestMapping(value = "/kjsadmin/delete.action", method = RequestMethod.POST)
	private void delete(String newsIds, HttpServletRequest request, HttpServletResponse response) {
		boolean result = false;
		if (!StringUtils.isBlank(newsIds)) {
			result = newsSevice.delete(newsIds.split(","));
		}
		PageWrite.writeTOPage(response, result);
	}

	@RequestMapping("/kjsadmin/datas.action")
	private void newsDatas(NewsQuery query, HttpServletRequest request, HttpServletResponse response) {
		String startStr = request.getParameter("page");
		int start = SportSupport.processLimit(startStr);
		List<NewsVo> news = this.newsSevice.getByWhere(query, (start - 1) * Constants.Common.PAGE_SIZE,
				Constants.Common.PAGE_SIZE);
		long total = this.newsSevice.getTotalByWhere(query);
		PageModel<NewsVo> model = new PageModel<NewsVo>();
		model.setPage(start);
		model.setRecords(total);
		model.setRows(news);
		model.setTotal((long) Math.ceil(total * 0.1 / Constants.Common.PAGE_SIZE));
		PageWrite.writeTOPage(response, GsonUtils.toJson(model));
	}

}
