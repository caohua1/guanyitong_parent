package com.guanyitong.service;
import com.guanyitong.model.*;

import java.util.List;
import java.util.Map;

public interface UserService {

	/**
	 * 累计用户数量
	 * @return
	 */
	public int selectUserNums();
	/**
	 * 注册师查询用户名是否已经存在
	 * @param user
	 * @return
	 */
	public User selectUserName(User user);

	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public int insertUser(User user);

	/**
	 * 累计交易额
	 * @return
	 */
	public int selectDealMoney();

	/**
	 * 登陆时，判断用户名和密码是否正确
	 * @param user
	 * @return
	 */
	public User selectUser(User user);

	/**
	 * 添加用户个人资料
	 * @param userPersonalData
	 * @return
	 */
	public int insertUserPersonalData(UserPersonalData userPersonalData);

	/**
	 * 修改个人资料
	 * @param userPersonalData
	 * @return
	 */
	public int updatePersonalData(UserPersonalData userPersonalData);

	/**
	 * 修改用户密码
	 * @param user
	 * @return
	 */
	public int updatePassword(User user);

	/**
	 * 查询有没有此用户的个人资料，如果有就是修改，没有就是添加
	 * @param userId
	 * @return
	 */
	public UserPersonalData selectPersonalData(Long userId);

	/**
	 * 查询问卷问题
	 * @return
	 */
	public List<UserQuestion> selectQuestion();

	/**
	 * 查询问卷每个问题的选项
	 * @param questionId
	 * @return
	 */
	public List<UserQuestionContent> selectUserQuestionContent(Integer questionId);

    /**
     * 给用户添加冠豆
     * @param userGuanDou
     * @return
     */
    public int insertGuanDou(UserGuanDou userGuanDou);

    /**
     * 查询用户冠豆数
     * @param userId
     * @return
     */
    public int selectGuanDou(Long userId);

    /**
     * 修改冠豆数（增加或减少，减少传负数）
     * @param map
     * @return
     */
    public int updateGuanDou(Map map);

	/**
	 * 查询用户的冠豆详情（获取或者消费）
	 * @param map
	 * @return
	 */
	public List<UserGuanDou> selectUserGuanDouInfo(Map map);
}
