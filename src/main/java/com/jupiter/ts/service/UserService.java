package com.jupiter.ts.service;

import com.jupiter.ts.dto.TsResultDto;
import com.jupiter.ts.exception.CustomizeErrorCode;
import com.jupiter.ts.mapper.UserMapper;
import com.jupiter.ts.model.User;
import com.jupiter.ts.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    //用户登陆
    public TsResultDto userLogin(String username, String password,
                                 HttpServletRequest request, HttpServletResponse response) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        if(null==users || users.size()==0) {
            return TsResultDto.build(CustomizeErrorCode.USER_CHECK_FAILED);
        }
        User user = users.get(0);
        //比对密码
        if(!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
            return TsResultDto.build(CustomizeErrorCode.USER_CHECK_FAILED);
        }
        //往session里写用户信息
        request.getSession().setAttribute("user",user);
        return TsResultDto.ok();
    }


    //校验数据
    public TsResultDto checkData(String param, int type) {
        //创建查询条件
        List<User> list = null;
        //对数据进行校验 1、2、3分别代表username、phone、email
        UserExample example = new UserExample();
        switch(type) {
            case 1:
                //username
                example.createCriteria().andUsernameEqualTo(param);
                list = userMapper.selectByExample(example);
                break;
            case 2:
                //phone
                example.createCriteria().andPhoneEqualTo(param);
                list = userMapper.selectByExample(example);
                break;
            case 3:
                //email
                example.createCriteria().andEmailEqualTo(param);
                list = userMapper.selectByExample(example);
                break;
        }
        //执行查询
        if(list == null || list.size()==0) {
            return TsResultDto.ok(true);
        }
        return TsResultDto.build(CustomizeErrorCode.USER_REGISTER_FAILED);
    }

    //创建用户
    public TsResultDto createUser(User user) {
        //user补全
        user.setUsCreate(System.currentTimeMillis());
        user.setUsModified(System.currentTimeMillis());
        //密码加密MD5
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        userMapper.insert(user);
        return TsResultDto.ok();
    }
}
