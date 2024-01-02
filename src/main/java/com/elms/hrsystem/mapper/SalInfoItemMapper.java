package com.elms.hrsystem.mapper;
import com.elms.hrsystem.entity.SalInfoItem;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * 薪酬项目关联表
 *
 * @author elms elms2002@163.com
 * @since 1.0.0 2023-12-22
 */
@Mapper
public interface SalInfoItemMapper extends BaseMapper<SalInfoItem> {
	
}