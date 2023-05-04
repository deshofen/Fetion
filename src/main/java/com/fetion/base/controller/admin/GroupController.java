package com.fetion.base.controller.admin;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fetion.base.bean.CodeMsg;
import com.fetion.base.bean.PageBean;
import com.fetion.base.bean.Result;
import com.fetion.base.constant.SessionConstant;
import com.fetion.base.entity.common.Account;
import com.fetion.base.entity.common.AccountGroup;
import com.fetion.base.entity.common.AccountGroupMember;
import com.fetion.base.service.admin.OperaterLogService;
import com.fetion.base.service.common.AccountGroupMemberService;
import com.fetion.base.service.common.AccountGroupService;
import com.fetion.base.service.common.AccountService;
import com.fetion.base.util.SessionUtil;

/**
 * 后台聊天室管理控制器
 * @author 卞宇轩
 *
 */
@RequestMapping("/admin/group")
@Controller
public class GroupController {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AccountGroupService accountGroupService;
	
	@Autowired
	private AccountGroupMemberService accountGroupMemberService;
	
	@Autowired
	private OperaterLogService operaterLogService;
	/**
	 * 聊天室列表页面
	 * @param model
	 * @param accountGroup
	 * @param pageBean
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(Model model,AccountGroup accountGroup,PageBean<AccountGroup> pageBean){
		model.addAttribute("title", "聊天室列表");
		model.addAttribute("name", accountGroup.getName());
		model.addAttribute("pageBean", accountGroupService.findList(accountGroup, pageBean));
		return "admin/group/list";
	}
	
	/**
	 * 编辑聊天室
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> edit(@RequestParam(name="id",required=true)Long id,@RequestParam(name="status",required=true)Integer status){
		Account account = accountService.find(id);
		if(account != null){
			account.setStatus(status);
			accountService.updateById(account);
		}
		operaterLogService.add("编辑聊天室状态，聊天室ID：" + id);
		return Result.success(true);
	}
	
	/**
	 * 解散聊天室
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/destory",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> destory(@RequestParam(name="id",required=true)Long id){
		AccountGroup accountGroup = accountGroupService.findById(id);
		if(accountGroup != null){
			//删除所有成员
			accountGroupMemberService.deleteByAccountGroupId(id);
			accountGroupService.delete(id);
		}
		return Result.success(true);
	}
	
	/**
	 * 移除成员
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete_member",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> deleteMember(@RequestParam(name="id",required=true)Long id){
		AccountGroupMember groupMember = accountGroupMemberService.find(id);
		if(groupMember != null){
			if(groupMember.getAccountGroup().getAdmin().getId().longValue() == groupMember.getMember().getId().longValue()){
				return Result.error(CodeMsg.GROUP_ADMIN_NO_DELETE);
			}
			accountGroupMemberService.delete(id);
			AccountGroup accountGroup = groupMember.getAccountGroup();
			//修改聊天室成员人数
			accountGroup.setCurPeople(accountGroup.getCurPeople()-1);

			accountGroupService.updateById(accountGroup);
		}
		return Result.success(true);
	}
	
	/**
	 * 获取聊天室成员
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/view_member",method=RequestMethod.POST)
	@ResponseBody
	public Result<List<AccountGroupMember>> viewMember(@RequestParam(name="id",required=true)Long id){
		return Result.success(accountGroupMemberService.findByGroup(id));
	}
}
