package com.fetion.base.service.admin;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
/**
 * 数据库备份service
 */
import org.springframework.stereotype.Service;

import com.fetion.base.bean.PageBean;
import com.fetion.base.mapper.admin.DatabaseBakMapper;
import com.fetion.base.entity.admin.DatabaseBak;
import com.fetion.base.util.StringUtil;

@Service
public class DatabaseBakService extends ServiceImpl<DatabaseBakMapper,DatabaseBak> {

	@Autowired
	private OperaterLogService operaterLogService;
	@Autowired
	private DatabaseBakMapper databaseBakMapper;
	@Value("${byx.database.backup.dir}")
	private String backUpDir;
	@Value("${byx.database.backup.username}")
	private String dbUsername;
	@Value("${byx.database.backup.password}")
	private String dbPwd;
	@Value("${byx.database.backup.database.name}")
	private String dbName;
	
	private Logger log = LoggerFactory.getLogger(DatabaseBakService.class);
	
	/**
	 * 分页查找数据库备份记录
	 * @param operaterLog
	 * @param pageBean
	 * @return
	 */
	public PageBean<DatabaseBak> findList(PageBean<DatabaseBak> pageBean){
		Page pageInfo = new Page(pageBean.getCurrentPage()-1, pageBean.getPageSize());

		databaseBakMapper.selectPage(pageInfo,null);
		pageBean.setContent(pageInfo.getRecords());
		pageBean.setTotal(pageInfo.getTotal());
		pageBean.setTotalPage(pageInfo.getPages());
		return pageBean;
	}
	
	/**
	 * 添加或修改数据库备份记录
	 * @param databaseBak
	 * @return
	 */
	public int insert(DatabaseBak databaseBak){
		return databaseBakMapper.insert(databaseBak);
	}
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public DatabaseBak find(Long id){
		return databaseBakMapper.selectById(id);
	}
	
	/**
	 * 根据id删除
	 * @param id
	 */
	public void delete(Long id){
		databaseBakMapper.deleteById(id);
	}
	
	/**
	 * 备份数据库
	 */
	public void backup(){
		File path = new File(backUpDir);
		if(!path.exists()){
			path.mkdir();
		}
		try {
			String filename = dbName + "_" + StringUtil.getFormatterDate(new Date(), "yyyyMMddHHmmss") + ".sql";
			String cmd = "mysqldump -u"+dbUsername+" -p"+dbPwd+" "+dbName+" -r " + backUpDir + filename;
			Runtime.getRuntime().exec(cmd);
			DatabaseBak databaseBak = new DatabaseBak();
			databaseBak.setFilename(filename);
			databaseBak.setFilepath(backUpDir);
			insert(databaseBak);
			log.info("数据库备份成功");
			operaterLogService.add("数据库成功备份，备份文件信息：" + databaseBak);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	/**
	 * 还原数据库
	 * @param id
	 */
	public void restore(Long id){
		DatabaseBak databaseBak = find(id);
		if(databaseBak != null){
			try {
				String filename = databaseBak.getFilename();
				File file = new File(databaseBak.getFilepath() + databaseBak.getFilename());
				String cmd = "mysql -u"+dbUsername+" -p"+dbPwd+" "+dbName+" < " + backUpDir + filename;;
				if(!file.exists()){
					cmd = "mysql -u"+dbUsername+" -p"+dbPwd+" "+dbName+" < " + databaseBak.getFilepath() + databaseBak.getFilename();
				}
				String stmt1 = "mysqladmin -u "+dbUsername+" -p"+dbPwd+" create "+dbName;
				String[] cmds = { "cmd", "/c", cmd };
				Runtime.getRuntime().exec(stmt1);
				Process exec = Runtime.getRuntime().exec(cmds);
				log.info(StringUtil.getStringFromInputStream(exec.getErrorStream()));
				log.info("数据库还原成功");
				operaterLogService.add("数据库成功还原，还原文件信息：" + databaseBak);
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 备份总数
	 * @return
	 */
	public long total(){
		return databaseBakMapper.selectCount(null);
	}
}
