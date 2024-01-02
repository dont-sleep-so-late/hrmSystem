package com.elms.hrsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.elms.hrsystem.entity.SalItem;
import com.elms.hrsystem.entity.dto.ItemDto;

import java.util.List;


/**
 * 薪酬项目表
 *
 * @author elms elms2002@163.com
 * @since 1.0.0 2023-12-22
 */
public interface SalItemService extends IService<SalItem> {

    /**
     * 添加薪酬项目
     * @param salItem
     * @return
     */
    public SalItem insertSalItem(ItemDto salItem);

    /**
     * 返回薪酬项目
     * @param pageSize
     * @param pageNum
     * @return
     */
    public List<SalItem> list(int pageSize, int pageNum);

    /**
     * 删除薪酬项目
     * @param id
     * @return
     */
    public boolean delete(String id);

    /**
     * 修改薪酬项目
     * @param salItem
     * @return
     */
    public boolean update(ItemDto salItem);

}