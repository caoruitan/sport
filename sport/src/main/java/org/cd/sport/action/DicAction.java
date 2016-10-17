package org.cd.sport.action;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.constant.Constants;
import org.cd.sport.domain.Dic;
import org.cd.sport.domain.DicType;
import org.cd.sport.service.DicService;
import org.cd.sport.service.DicTypeService;
import org.cd.sport.support.SportSupport;
import org.cd.sport.utils.GsonUtils;
import org.cd.sport.utils.PageModel;
import org.cd.sport.utils.PageWrite;
import org.cd.sport.view.DicView;
import org.cd.sport.vo.DicQuery;
import org.cd.sport.vo.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * 数据字典控制层
 * 
 * @author liuyk
 *
 */
@Controller
@RequestMapping("/dic")
public class DicAction {

	@Autowired
	private DicTypeService dicTypeService;

	@Autowired
	private DicService dicService;

	@RequestMapping("/list")
	public String gotoIndex(HttpServletRequest request) {
		String pCode = request.getParameter("pCode");
		request.setAttribute("pCode", pCode);
		return "dic/list";
	}

	/**
	 * 查询所有的数字字典分类
	 */
	@RequestMapping(value = "/dataTypes.action", method = RequestMethod.POST)
	public void queryDataTypes(HttpServletRequest request, HttpServletResponse response) {
		String pid = request.getParameter("pId");
		List<Node> dics = this.dicTypeService.getNodeByPid(pid);
		PageWrite.writeTOPage(response, GsonUtils.toJson(dics));
	}

	/**
	 * 通过分类查询数据
	 */
	@RequestMapping(value = "/datas.action", method = RequestMethod.GET)
	public void queryDatas(DicQuery query, HttpServletRequest request, HttpServletResponse response) {
		String startStr = request.getParameter("page");
		int start = SportSupport.processLimit(startStr);
		List<Dic> dics = this.dicService.getByWhere(query, (start - 1) * Constants.Common.PAGE_SIZE,
				Constants.Common.PAGE_SIZE);
		long total = this.dicService.getTotalByWhere(query);
		PageModel<Dic> model = new PageModel<Dic>();
		model.setPage(start);
		model.setRecords(total);
		model.setRows(dics);
		model.setTotal((long) Math.ceil(total * 0.1 / Constants.Common.PAGE_SIZE));
		PageWrite.writeTOPage(response, GsonUtils.toJson(model));
	}

	/**
	 * 通过分类查询数据
	 */
	@RequestMapping(value = "/datas/byPcode.action", method = RequestMethod.GET)
	public void queryDatasByPcode(String pCode, HttpServletRequest request, HttpServletResponse response) {
		List<Dic> dics = this.dicService.getByPcode(pCode);
		PageWrite.writeTOPage(response, GsonUtils.toJson(dics));
	}

	/**
	 * 创建数据字典
	 */
	@RequestMapping(value = "/create/check.action", method = RequestMethod.POST)
	public void checkDic(String name, HttpServletRequest request, HttpServletResponse response) {
		Dic dic = this.dicService.getByName(name);
		PageWrite.writeTOPage(response, dic == null);

	}

	/**
	 * 创建数据字典
	 */
	@RequestMapping(value = "/update/check.action", method = RequestMethod.POST)
	public void checkUpdateDic(String name, String dicId, HttpServletRequest request, HttpServletResponse response) {
		Dic dic = this.dicService.getByName(name);
		boolean result = false;
		if (dic == null || dic.getId().equals(dicId)) {
			result = true;
		}
		PageWrite.writeTOPage(response, result);
	}

	/**
	 * 创建数据字典
	 */
	@RequestMapping(value = "/create.htm", method = RequestMethod.GET)
	public String createPage(HttpServletRequest request, HttpServletResponse response) {
		String code = request.getParameter("pCode");
		// 验证typeId是否存在
		DicType dic = this.dicTypeService.getByCode(code);
		if (dic == null || StringUtils.isBlank(code)) {
			throw new EntityNotFoundException("数据分类没有找到");
		}
		request.setAttribute("dic", dic);
		return "dic/create";
	}

	/**
	 * 创建数据字典
	 */
	@RequestMapping(value = "/create.action", method = RequestMethod.POST)
	public void createDic(DicView dic, HttpServletRequest request, HttpServletResponse response) {
		boolean create = this.dicService.create(dic);
		PageWrite.writeTOPage(response, create);
	}

	/**
	 * 修改数据字典
	 */
	@RequestMapping(value = "/update.htm", method = RequestMethod.GET)
	public String updatePage(HttpServletRequest request, HttpServletResponse response) {
		String dicId = request.getParameter("dicId");
		Dic dic = this.dicService.getById(dicId);
		if (dic == null) {
			throw new EntityNotFoundException("数据项没有找到");
		}
		DicType dicType = this.dicTypeService.getByCode(dic.getpCode());
		request.setAttribute("dic", dic);
		request.setAttribute("dicType", dicType);
		return "dic/update";
	}

	/**
	 * 修改数据字典
	 */
	@RequestMapping(value = "/update.action", method = RequestMethod.POST)
	public void updateDic(DicView dic, HttpServletRequest request, HttpServletResponse response) {
		boolean create = this.dicService.update(dic);
		PageWrite.writeTOPage(response, create);
	}

	/**
	 * 创建数据字典
	 */
	@RequestMapping(value = "/delete.action", method = RequestMethod.POST)
	public void deleteDic(String dicIds, HttpServletRequest request, HttpServletResponse response) {
		boolean result = false;
		if (!StringUtils.isBlank(dicIds)) {
			result = this.dicService.deleteById(dicIds.split(","));
		}
		PageWrite.writeTOPage(response, result);
	}
}
