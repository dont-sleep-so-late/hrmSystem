package com.elms.hrsystem.entity.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: elms
 * @Description: HRSystem
 * @DateTime: 2023/12/25 19:32
 **/
@Data
public class FileDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 档案id
     */
    private String fid;
    /**
     * 申请人id
     */
    private String uid;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private String sex;
    /**
     * 出生日期
     */
    private String birth;
    /**
     * 年龄
     */
    private String age;
    /**
     * 头像
     */
    private String image;
    /**
     * 职称id
     */
    private String professionId;
    /**
     * 三级机构id
     */
    private String organizionId;
    /**
     * 民族id
     */
    private String nationId;
    /**
     * 学历id
     */
    private String academicId;
    /**
     * 宗教id
     */
    private String religionId;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 电话
     */
    private String phone;
    /**
     * QQ
     */
    private String qq;
    /**
     * 居住地址
     */
    private String address;
    /**
     * 个人履历
     */
    private String personalHistory;
    /**
     * 家庭关系信息
     */
    private String familyRelationship;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 逻辑删除【1true】
     */
    private String isDeleted;
    /**
     * 复核情况【1true】
     */
    private String isCheck;
    /**
     * 是否被驳回【1true】
     */
    private String isReject;



    /**
     * 一级机构名称
     */
    private String o1Name;

    /**
     * 二级机构名称
     */
    private String o2Name;

    /**
     * 三级机构名称
     */
    private String o3Name;



    /**
     * 职称姓名
     */
    private String academic;

    /**
     * 民族
     */
    private String nation;

    /**
     * 职称
     */
    private String profession;

    /**
     * 宗教
     */
    private String religion;

}
