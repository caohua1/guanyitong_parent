package com.guanyitong.mapper;


import com.guanyitong.model.*;

import java.util.List;
import java.util.Map;

public interface UserDAO {

	/**
	 * 累计用户数量
	 * @return
	 */
	public int selectUserNums();

	/**
	 * 累计交易额
	 * @return
	 */
	public int selectDealMoney();
	/**
	 * 查询用户是否已经存在
	 */
    public User selectUserName(User user);
	/**
	 * 添加新用户
	 * @param user
	 * @return
	 */
	public int insertUser(User user);

	/**
	 * 查询用户是否存在
	 */
	public User selectUser(User user);

	/**
	 * 添加用户资料
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


	//========================================================后台开发的信息查询

	/**
	 * 分页查询所有注册的用户的信息（条件查询username）
	 * @param user
	 * @return
	 */
	public List<User> selectAllRegistUsers(User user);

	/**
	 * 查询某个用户的个人资料
	 * @param id
	 * @return
	 */
	public UserPersonalData selectUserPersonalDataById(Long id);

	/**
	 * 查询某个用户绑定银行的信息
	 * @param id
	 * @return
	 */
	public AccountManager selectAccountManagerById(Long id);

	/**
	 * 分页，条件，模糊查询所有注册的出借用户
	 * @param map
	 * @return
	 */
	public List<User> selectAllUser(Map map);

	/**
	 * 条件查询，查询总数
	 * @param map
	 * @return
	 */
	public Integer selectCount(Map map);

}
