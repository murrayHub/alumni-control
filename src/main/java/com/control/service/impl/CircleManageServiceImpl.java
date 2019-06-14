package com.techGoal.service.impl;

import com.google.common.collect.Lists;
import com.techGoal.constant.RedisDict;
import com.techGoal.convert.CircleApplyConvert;
import com.techGoal.convert.CircleConvert;
import com.techGoal.convert.UserInfoConvert;
import com.techGoal.dict.NumberDict;
import com.techGoal.enums.ErrorCodeEnum;
import com.techGoal.exception.BizServiceException;
import com.techGoal.mapper.CircleApplyMapper;
import com.techGoal.mapper.CircleMapper;
import com.techGoal.mapper.CircleUserMapper;
import com.techGoal.pojo.bo.CircleBo;
import com.techGoal.pojo.dao.CircleApplyDo;
import com.techGoal.pojo.dao.CircleDo;
import com.techGoal.pojo.dao.CircleUserDo;
import com.techGoal.pojo.dao.UserInfoDo;
import com.techGoal.pojo.vo.CircleApplyVo;
import com.techGoal.pojo.vo.ExitCircleVo;
import com.techGoal.pojo.vo.HandleCircleApplyVo;
import com.techGoal.pojo.vo.UserInfoVo;
import com.techGoal.redis.OrderIdManager;
import com.techGoal.service.CircleManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * description : 圈子管理-服务层-实现类
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/5/20 10:24
 */
@Slf4j
@Service
public class CircleManageServiceImpl implements CircleManageService {

    /**
     * 圈子管理 Mapper
     */
    @Autowired
    private CircleMapper circleMapper;

    /**
     * 入圈申请 Mapper
     */
    @Autowired
    private CircleApplyMapper circleApplyMapper;

    /**
     * 圈子用户中间表 Mapper
     */
    @Autowired
    private CircleUserMapper circleUserMapper;

    /**
     * 流水号创建服务
     */
    @Autowired
    private OrderIdManager orderIdManager;

    /**
     * 根据圈名称模糊查询匹配圈子
     *
     * @param circleName 圈名称
     * @return 结果集
     */
    @Override
    public List<CircleBo> getCircleByName(String circleName) {
        List<CircleBo> boList = Lists.newArrayList();
        List<CircleDo> circleDoLists = circleMapper.getCircleByName(circleName);
        if (!CollectionUtils.isEmpty(circleDoLists)) {
            for (CircleDo circleDo : circleDoLists) {
                CircleBo circleBo = CircleConvert.circleDoToConvertBo(circleDo);
                boList.add(circleBo);
            }
        }
        return boList;
    }

    /**
     * 根据圈名称查询由用户创建的匹配圈子
     *
     * @param circleName   圈名称
     * @param circleHostNo 圈主
     * @return 结果集
     */
    @Override
    public List<CircleBo> getCircleByNameAndHostNo(String circleName, String circleHostNo) {
        List<CircleBo> boList = Lists.newArrayList();
        List<CircleDo> circleDoLists = circleMapper.getCircleByNameAndHostNo(circleName, Long.valueOf(circleHostNo));
        if (!CollectionUtils.isEmpty(circleDoLists)) {
            for (CircleDo circleDo : circleDoLists) {
                CircleBo circleBo = CircleConvert.circleDoToConvertBo(circleDo);
                boList.add(circleBo);
            }
        }
        return boList;
    }

    /**
     * 根据用户编号查询已加入的圈子(支持圈名模糊查询)
     *
     * @param circleBo 检索条件
     * @return 结果集
     */
    @Override
    public List<CircleBo> getCircleByUserId(CircleBo circleBo) {
        List<CircleBo> boList = Lists.newArrayList();
        CircleDo circleDo = CircleConvert.circleBoToConvertDo(circleBo);
        List<CircleDo> circleDoList = circleMapper.getCircleByUserId(circleDo);
        if (!CollectionUtils.isEmpty(circleDoList)) {
            for (CircleDo circleDo1 : circleDoList) {
                CircleBo circleBo1 = CircleConvert.circleDoToConvertBo(circleDo1);
                boList.add(circleBo1);
            }
        }
        return boList;
    }

    /**
     * 根据圈子编号查询圈子主页信息
     *
     * @param circleNo 圈编号
     * @return 结果集
     */
    @Override
    public CircleBo getCircleInfoByCircleNo(String circleNo) {
        CircleBo circleBo = new CircleBo();
        // 查询圈子成员人数
        CircleDo circleDo = new CircleDo();
        circleDo.setCircleNo(Long.valueOf(circleNo));
        List<UserInfoDo> userInfoDos = circleMapper.getAllCircleMembers(circleDo);
        circleBo.setMemberCounts(String.valueOf(userInfoDos.size()));
        List<CircleDo> circleDoList = circleMapper.select(circleDo);
        if(!CollectionUtils.isEmpty(circleDoList)){
            circleBo.setCircleNo(String.valueOf(circleDoList.get(0).getCircleNo()));
            circleBo.setCircleHostNo(String.valueOf(circleDoList.get(0).getCircleHostNo()));
            circleBo.setCircleName(circleDoList.get(0).getCircleName());
            circleBo.setCircleTheme(circleDoList.get(0).getCircleTheme());
            circleBo.setImage(circleDoList.get(0).getImage());
            circleBo.setCircleLabel(circleDoList.get(0).getCircleLabel());
            circleBo.setCreateAt(circleDoList.get(0).getCreateAt());
        }
        return circleBo;
    }

    /**
     * 获取圈成员信息
     *
     * @param circleNo 圈编号
     * @return 结果集
     */
    @Override
    public List<UserInfoVo> getAllCircleMembers(Long circleNo) {
        List<UserInfoVo> userInfoVoList = Lists.newArrayList();
        CircleDo circleDo = new CircleDo();
        circleDo.setCircleNo(circleNo);
        List<UserInfoDo> userInfoDos = circleMapper.getAllCircleMembers(circleDo);
        for (UserInfoDo userInfoDo : userInfoDos) {
            UserInfoVo userInfoVo = UserInfoConvert.UserInfoDoToVo(userInfoDo);
            userInfoVoList.add(userInfoVo);
        }
        return userInfoVoList;
    }

    /**
     * 入圈申请
     *
     * @param circleApplyVo 请求参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void circleApply(CircleApplyVo circleApplyVo) {
        CircleApplyDo circleApplyDo = new CircleApplyDo();
        circleApplyDo.setCircleNo(Long.valueOf(circleApplyVo.getCircleNo()));
        circleApplyDo.setCircleName(circleApplyVo.getCircleName());
        circleApplyDo.setUserId(Long.valueOf(circleApplyVo.getApplyUserId()));
        // 查看用户是否已经申请加入该圈
        List<CircleApplyDo> circleApplyDos = circleApplyMapper.select(circleApplyDo);
        if (!CollectionUtils.isEmpty(circleApplyDos)) {
            if (NumberDict.ONE == (circleApplyDos.get(0).getState())) {
                throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000010);
            }
            if (NumberDict.TWO == (circleApplyDos.get(0).getState())) {
                throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000011);
            }
        }
        circleApplyDo.setApplyContent(circleApplyVo.getApplyContent());
        circleApplyDo.setState(NumberDict.ONE);
        circleApplyDo.setCreateBy(circleApplyVo.getApplyUserId());
        circleApplyDo.setCreateAt(new Date());
        Long applyCircleId = orderIdManager.orderIdCreate(RedisDict.CIRCLE_APPLY_NO_KEY);
        circleApplyDo.setApplyCircleId(applyCircleId);
        // 插入入圈申请信息
        circleApplyMapper.insert(circleApplyDo);
    }

    /**
     * 圈主拉取入圈申请记录
     *
     * @param userId 用户编号
     * @return 结果集
     */
    @Override
    public List<CircleApplyVo> circleApplyRecord(Long userId) {
        List<CircleApplyVo> circleApplyVos = new ArrayList<>();
        List<CircleApplyDo> circleApplyDos = circleApplyMapper.circleApplyRecord(userId);
        for (CircleApplyDo circleApplyDo : circleApplyDos) {
            CircleApplyVo circleApplyVo = CircleApplyConvert.circleApplyDoToConvertVo(circleApplyDo);
            circleApplyVos.add(circleApplyVo);
        }
        return circleApplyVos;
    }

    /**
     * 拉取个人入圈申请记录
     *
     * @param userId 用户编号
     * @return 结果集
     */
    @Override
    public List<CircleApplyVo> circleApplyPerRecord(Long userId) {
        List<CircleApplyVo> circleApplyVos = new ArrayList<>();
        CircleApplyDo circleApplyReqDo = new CircleApplyDo();
        circleApplyReqDo.setUserId(userId);
        List<CircleApplyDo> circleApplyDos = circleApplyMapper.select(circleApplyReqDo);
        for (CircleApplyDo circleApplyDo : circleApplyDos) {
            CircleApplyVo circleApplyVo = CircleApplyConvert.circleApplyDoToConvertVo(circleApplyDo);
            circleApplyVos.add(circleApplyVo);
        }
        return circleApplyVos;
    }

    /**
     * 退出圈子
     *
     * @param exitCircleVo 请求参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void exitCircle(ExitCircleVo exitCircleVo) {
        CircleUserDo circleUserDo = new CircleUserDo();
        circleUserDo.setCircleNo(Long.valueOf(exitCircleVo.getCircleNo()));
        circleUserDo.setUserId(Long.valueOf(exitCircleVo.getUserId()));
        circleUserDo.setUpdateBy(exitCircleVo.getOperatorNo());
        circleUserMapper.exitCircle(circleUserDo);
    }

    /**
     * 圈主处理入圈申请记录
     *
     * @param handleCircleApplyVo 请求参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void handleCircleApply(HandleCircleApplyVo handleCircleApplyVo) {
        CircleApplyDo circleApplyDo = new CircleApplyDo();
        circleApplyDo.setCircleNo(Long.valueOf(handleCircleApplyVo.getCircleNo()));
        circleApplyDo.setApplyCircleId(Long.valueOf(handleCircleApplyVo.getApplyCircleId()));
        circleApplyDo.setUpdateBy(handleCircleApplyVo.getOperatorNo());
        circleApplyDo.setState(Integer.valueOf(handleCircleApplyVo.getState()));
        circleApplyMapper.handleCircleApply(circleApplyDo);
    }
}
