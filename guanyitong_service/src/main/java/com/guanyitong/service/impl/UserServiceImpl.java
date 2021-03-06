package com.guanyitong.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guanyitong.mapper.UserDAO;
import com.guanyitong.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.guanyitong.service.UserService;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;

	/**
	 * 累计用户数量
	 * @return
	 */
	@Override
	public int selectUserNums() {
		return userDAO.selectUserNums();
	}

	/**
	 * 累计交易额
	 * @return
	 */
	@Override
	public int selectDealMoney() {
		return userDAO.selectDealMoney();
	}

	/**
	 * 查询用户是否已经存在
	 */
	@Override
	public User selectUserName(User user) {
		return userDAO.selectUserName(user);
	}
	/**
	 * 注册
	 * @param user
	 * @return
	 */
	@Transactional
	@Override
	public int insertUser(User user) {
		// TODO Auto-generated method stub
	    return	userDAO.insertUser(user);
		//throw new RuntimeException("事物回滚");//抛出unchecked异常，触发事物，回滚  
	}

	/**
	 * 登陆时查询用户是否存在
	 * @param user
	 * @return
	 */
	@Override
	public User selectUser(User user) {
		User u = userDAO.selectUser(user);
		return u;
	}
	/**
	 * 添加用户个人资料
	 * @param userPersonalData
	 * @return
	 */
	@Transactional
	@Override
	public int insertUserPersonalData(UserPersonalData userPersonalData) {
		return userDAO.insertUserPersonalData(userPersonalData);
	}
	/**
	 * 修改用户的个人资料
	 * @param userPersonalData
	 * @return
	 */
	@Transactional
	@Override
	public int updatePersonalData(UserPersonalData userPersonalData) {
		return userDAO.updatePersonalData(userPersonalData);
	}
	/**
	 * 修改用户密码
	 * @param user
	 * @return
	 */
	@Transactional
	@Override
	public int updatePassword(User user) {
		return userDAO.updatePassword(user);
	}
	/**
	 * 查询是否有此用户的个人资料，如果有就是修改，没有就是添加
	 * @param userId
	 * @return
	 */
	@Override
	public UserPersonalData selectPersonalData(Long userId) {
		return userDAO.selectPersonalData(userId);
	}

	/**
	 * 查询问卷问题
	 * @return
	 */
	@Override
	public List<UserQuestion> selectQuestion() {
		return userDAO.selectQuestion();
	}

	/**
	 * 查询每个问题的选项
	 * @param questionId
	 * @return
	 */
	@Override
	public List<UserQuestionContent> selectUserQuestionContent(Integer questionId) {
		return userDAO.selectUserQuestionContent(questionId);
	}

	/**
	 * 添加用户冠豆数
	 * @param userGuanDou
	 * @return
	 */
	@Transactional
	@Override
	public int insertGuanDou(UserGuanDou userGuanDou) {
		return userDAO.insertGuanDou(userGuanDou);
	}

	/**
	 * 查询用户的冠豆数
	 * @param userId
	 * @return
	 */
	@Override
	public int selectGuanDou(Long userId) {
		return userDAO.selectGuanDou(userId);
	}

	/**
	 * 修改用户的冠豆数（减少或添加）
	 * @param map
	 * @return
	 */
	@Transactional
	@Override
	public int updateGuanDou(Map map) {
		return userDAO.updateGuanDou(map);
	}

	/**
	 * 查询用户的冠豆详情（获取或者消费）
	 * @param map
	 * @return
	 */
	@Override
	public List<UserGuanDou> selectUserGuanDouInfo(Map map) {
		return userDAO.selectUserGuanDouInfo(map);
	}


	//========================================================后台开发的信息查询
	/**
	 * 分页查询所有注册的用户的信息（条件查询username）
	 * @param user
	 * @return
	 */
	@Override
	public PageInfo<User> selectAllRegistUsers(User user, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		List<User> users = userDAO.selectAllRegistUsers(user);
		PageInfo<User> pageInfo = new PageInfo<User>(users);
		return pageInfo;
	}

	/**
	 * 查询某个用户的个人资料
	 * @param id
	 * @return
	 */
	@Override
	public UserPersonalData selectUserPersonalDataById(Long id) {
		UserPersonalData userPersonalData = userDAO.selectUserPersonalDataById(id);
		return userPersonalData;
	}

	/**
	 * 查询某个用户绑定银行的信息
	 * @param id
	 * @return
	 */
	@Override
	public AccountManager selectAccountManagerById(Long id) {
		AccountManager accountManager = userDAO.selectAccountManagerById(id);
		return accountManager;
	}

	/**
	 * 分页，条件，模糊查询所有出借的注册用户
	 * @param map
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Override
	public PageInfo<User> selectAllUser(Map map, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum,pageSize);
        List<User> users = userDAO.selectAllUser(map);
        PageInfo<User> pageInfo = new PageInfo<User>(users);
        return pageInfo;
	}

    /**
     * 条件查询总数
     * @param map
     * @return
     */
    @Override
    public Integer selectCount(Map map) {
        return userDAO.selectCount(map);
    }

}
