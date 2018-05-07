package com.binger.uaa.service.impl;

import com.binger.common.exception.BusinessException;
import com.binger.common.util.DozerUtils;
import com.binger.uaa.dao.UserMapper;
import com.binger.uaa.domain.User;
import com.binger.uaa.domain.UserExample;
import com.binger.uaa.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;


    @Override
    public long count(UserExample userExample) {
        return userMapper.countByExample(userExample);
    }

    @Override
    public List<User> listByExample(UserExample userExample) {
        List<User> userList = userMapper.selectByExample(userExample);
        if(userList != null) {
            return userList;
        }else {
            throw BusinessException.create("数据不存在");
        }
    }

    @Override
    public User findById(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        if(null != user){
            return DozerUtils.convert(user,User.class);
        }else {
            throw BusinessException.create("不存在该用户ID,请确认后输入");
        }
    }

    @Override
    public User updateById(User user, Integer id) {
        User user1 = userMapper.selectByPrimaryKey(id);
        if (user1 == null) {
            throw BusinessException.create("无法找到该用户");
        }
        user.setId(id);

        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andIdNotEqualTo(user.getId());
        List<User> list = userMapper.selectByExample(example);
        boolean isUpdate = true;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUserCode().equals(user.getUserCode()))
                isUpdate = false;
        }
        if (isUpdate) {
            long count = userMapper.updateByPrimaryKey(user);
            if (count > 0) {
                return DozerUtils.convert(userMapper.selectByPrimaryKey(id), User.class);
            } else {
                throw BusinessException.create("修改失败，可能重复操作");
            }
        } else {
            throw BusinessException.create("用户编号已存在");
        }
    }

        @Override
        public User add (User user){
            if (StringUtils.isEmpty(user.getUserCode())) {
                throw BusinessException.create("用户编号不能为空");
            }
            UserExample example = new UserExample();
            UserExample.Criteria criteria = example.createCriteria();
            criteria.andUserCodeLike(user.getUserCode());
            long count = userMapper.countByExample(example);
            if (count > 0) {
                throw BusinessException.create("用户编号已存在！");
            } else {
                long result = userMapper.insert(user);
                if (result > 0) {
                    return DozerUtils.convert(userMapper.selectByPrimaryKey(user.getId()), User.class);
                } else {
                    throw BusinessException.create("新增失败！");
                }
            }
        }

    @Override
    public Integer deleteById(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        if(user == null){
            throw BusinessException.create("未找到该用户，可能已被删除");
        }
        int count = userMapper.deleteByPrimaryKey(id);
        if(count>0){
            return count;
        }else {
            throw BusinessException.create("删除失败");
        }
    }

    @Override
    public User findUserByName(String s) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(s);
        List<User> users = userMapper.selectByExample(example);
        if (! CollectionUtils.isEmpty(users)) {
            return users.get(0);
        }
        return null;
    }
}
