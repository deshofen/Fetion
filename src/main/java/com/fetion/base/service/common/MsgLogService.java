package com.fetion.base.service.common;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fetion.base.entity.common.MsgLog;
import com.fetion.base.mapper.common.MsgLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fetion.base.mapper.common.MsgLogMapper;
import com.fetion.base.entity.common.MsgLog;

/**
 * 消息记录管理service
 * @author 卞宇轩
 *
 */
@Service
public class MsgLogService extends ServiceImpl<MsgLogMapper, MsgLog> {

	@Autowired
	private MsgLogMapper msgLogMapper;
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public MsgLog find(Long id){
		return msgLogMapper.findById(id);
	}
	
	
	/**
	 * 添加/编辑操作
	 * @param account
	 * @return
	 */
	public int insert(MsgLog msgLog){
		return msgLogMapper.insert(msgLog);
	}
	
	/**
	 * 获取指定用户的指定状态消息
	 * @param accountId
	 * @param status
	 * @return
	 */
	public List<MsgLog> findByAccountIdAndStatus(Long accountId,int status){
		return msgLogMapper.findByAccountIdAndStatus(accountId, status);
	}
	
	/**
	 * 根据消息内容查询关联记录
	 * @param msgContentId
	 * @return
	 */
	public List<MsgLog> findByMsgContentId(Long msgContentId){
		return msgLogMapper.findByMsgContentId(msgContentId);
	}
	
	/**
	 * 返回总数
	 * @return
	 */
	public long total(){
		return msgLogMapper.selectCount(null);
	}
}
