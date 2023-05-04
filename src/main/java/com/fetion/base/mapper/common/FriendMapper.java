package com.fetion.base.mapper.common;
/**
 * 好友数据库操作层
 */

import java.util.List;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Mapper;


import com.fetion.base.entity.common.Friend;

@Mapper
public interface FriendMapper extends BaseMapper<Friend> {

//	@Query(value = "select * from friend  where id = :id",nativeQuery=true)
//	Friend find(@Param("id")Long id);

	List<Friend> findByAccountId(@Param("accountId") Long accountId);

    List<Friend> findAllAccount(@Param("id") Long id);

	void deleteFlag(@Param("flag") String flag);

    Friend findById(@Param("id") Long id);
}
