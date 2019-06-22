package com.alumni.control.service;

import com.alumni.control.pojo.dao.AlumniManagerInfoDo;
import com.alumni.control.pojo.dao.LevelOneIdentifyDo;
import com.alumni.control.pojo.dao.UserDegreeIdentifyDo;
import com.alumni.control.pojo.vo.AlumniInfoUpdVo;
import com.alumni.control.pojo.vo.AlumniManagerInfoVo;
import com.alumni.control.pojo.vo.LevelOneAlumniUpdInfoVo;
import com.alumni.control.pojo.vo.UserDegreeIdentifyVo;

import java.util.List;

/**
 * description : 校友信息管理-服务层
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/6/17 11:31
 */
public interface AlumniManageService {
    /**
     * 获取一级认证申请信息
     * @param alumniManagerInfoVo 请求参数
     * @return 结果集
     */
    List<LevelOneIdentifyDo> getLevelOneIdentifyInfo(AlumniManagerInfoVo alumniManagerInfoVo);
    /**
     * 获取一级认证申请信息
     * @param alumniManagerInfoVo 请求参数
     * @return 结果集
     */
    List<LevelOneIdentifyDo> getLevelOneIdentifyInfoDetail(AlumniManagerInfoVo alumniManagerInfoVo);

    /**
     * 获取校友信息
     * @param alumniManagerInfoVo 请求参数
     * @return 结果
     */
    List<UserDegreeIdentifyDo> getAlumniInfo(AlumniManagerInfoVo alumniManagerInfoVo);

    /**
     * 获取校友信息
     * @param alumniManagerInfoVo 请求参数
     * @return 结果集
     */
    List<UserDegreeIdentifyDo> getAlumniInfos(AlumniManagerInfoVo alumniManagerInfoVo);

    /**
     * 处理学生信息展示结果
     * @param list 预处理数据
     * @return 结果集
     */
    List<UserDegreeIdentifyVo> dealWithViewResults(List<UserDegreeIdentifyDo> list);


    /**
     * 获取操作员信息
     * @param managerId 操作员编号
     * @return 结果
     */
    AlumniManagerInfoDo getManagerInfo(Long managerId);

    /**
     * 修改校友信息
     * @param alumniInfoUpdVo 请求参数
     */
    void updateAlumniInfo(AlumniInfoUpdVo alumniInfoUpdVo) throws Exception;

    /**
     * 修改校友信息复审
     * @param alumniInfoUpdVo 请求参数
     */
    void levelTwoUpdateAudit(AlumniInfoUpdVo alumniInfoUpdVo);

    /**
     * 修改校友一级认证信息
     * @param levelOneAlumniUpdInfoVo 请求参数
     */
    void updateLevelOneAlumniInfo(LevelOneAlumniUpdInfoVo levelOneAlumniUpdInfoVo) throws Exception;

    /**
     * 修改校友一级认证复审
     * @param levelOneAlumniUpdInfoVo 请求参数
     */
    void levelOneUpdateAudit(LevelOneAlumniUpdInfoVo levelOneAlumniUpdInfoVo);
}
