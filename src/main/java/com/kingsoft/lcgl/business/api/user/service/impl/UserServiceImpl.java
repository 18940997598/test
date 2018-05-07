package com.kingsoft.lcgl.business.api.user.service.impl;

import com.kingsoft.lcgl.business.api.admin.dto.AddUserRequest;
import com.kingsoft.lcgl.business.api.admin.dto.UserInfoResponse;
import com.kingsoft.lcgl.business.api.admin.mapper.AdminMapper;
import com.kingsoft.lcgl.business.api.common.mapper.CommonMapper;
import com.kingsoft.lcgl.business.api.project.mapper.ProjectMapper;
import com.kingsoft.lcgl.business.api.task.mapper.TaskMapper;
import com.kingsoft.lcgl.business.api.user.dto.*;
import com.kingsoft.lcgl.core.redis.util.JedisEnum;
import com.kingsoft.lcgl.core.redis.util.JedisClientTemplate;
import com.kingsoft.lcgl.business.api.common.service.EmailService;
import com.kingsoft.lcgl.business.api.common.service.impl.EmailServiceImpl;
import com.kingsoft.lcgl.business.api.user.mapper.UserMapper;
import com.kingsoft.lcgl.business.api.user.service.UserService;
import com.kingsoft.lcgl.business.common.support.CommonApiResponse;
import com.kingsoft.lcgl.business.common.util.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yangdiankang on 2017/11/15.
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EmailService emailService;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private CommonMapper commonMapper;
    @Autowired
    private JedisClientTemplate jedisClientTemplate;
    @Value("${timeout.identifyCodeTimeout}")
    private int identifyCodeTimeout;
    @Value("${timeout.tokenTimeout}")
    private int tokenTimeout;
    @Value("${timeout.refreshTokenTimeout}")
    private int refreshTokenTimeout;

    @Override
    public UserLoginRespone login(UserLoginRquest request) {
        UserLoginRespone respone = new UserLoginRespone();
        UserDto userDto = userMapper.getUserInfoByMail(request.getMail());
        if(userDto!=null&&request.getPass().equals(userDto.getPass())){
            String token = RandomUtil.getToken();
            String refreshToken = RandomUtil.getRefreshToken(request.getMail());
            HashMap<String,String> map = new HashMap<>();
            map.put("token",token);
            map.put("refreshToken",refreshToken);

            /**
             * redis 操作：
             * 1.清除原先的 token,refreshToken
             * 2.插入新的token,refreshToken
             * 3.更新mail
             */
            String oldToken = jedisClientTemplate.hmget(JedisEnum.MAIL.getName()+request.getMail(),"token").get(0);
            String oldRefresh =  jedisClientTemplate.hmget(JedisEnum.MAIL.getName()+request.getMail(),"refreshToken").get(0);
            jedisClientTemplate.del(JedisEnum.TOKEN.getName()+oldToken);
            jedisClientTemplate.del(JedisEnum.REFRESHTOKEN.getName()+oldRefresh);

            jedisClientTemplate.set(JedisEnum.TOKEN.getName()+token,request.getMail());
            jedisClientTemplate.set(JedisEnum.REFRESHTOKEN.getName()+refreshToken,request.getMail());
            jedisClientTemplate.hmset(JedisEnum.MAIL.getName()+request.getMail(),map);
            jedisClientTemplate.timeout(JedisEnum.TOKEN.getName()+token,tokenTimeout);
            jedisClientTemplate.timeout(JedisEnum.REFRESHTOKEN.getName()+refreshToken,refreshTokenTimeout);
            respone.setCode(0);
            respone.setMsg(request.getMail());
            respone.setToken(token);
            respone.setRefreshToken(refreshToken);
        }else {
            respone.setCode(500);
            respone.setMsg("用户名不存在或者密码错误");
        }
        return respone;
    }

    @Override
    public CommonApiResponse addProject(UserRegisterRequest request) {
        int code  =  0;
        String msg =  "";
        CommonApiResponse commonApiResponse = new CommonApiResponse();
        String identifyCode = request.getIdentifyCode();
        String redisIdentifyCode = jedisClientTemplate.get(JedisEnum.IDENTIFYCODE.getName()+request.getMail());
        if(redisIdentifyCode!=null){
            if(redisIdentifyCode.equals(identifyCode)){
                if(userMapper.getIdByMail(request.getMail())==null){
                    userMapper.addUser(request);
                    commonApiResponse.setCode(0);
                }else {
                    code = 500;
                    msg = "该邮箱已经注册";
                }
            }else {
                code = 500;
                msg = "验证码不匹配";
            }
        }else {
            code = 500;
            msg = "验证码不匹配";
        }
        commonApiResponse.setCode(code);
        commonApiResponse.setMsg(msg);

        return commonApiResponse;
    }

    @Override
    public CommonApiResponse mailSendIdentifyCode(String  mail) {
        String Mail=  mail.replaceAll("\"","");
        CommonApiResponse response = new CommonApiResponse();
        String reciver[] = {Mail};
        String identifyCode = RandomUtil.getRandomSix();
        String content = "尊敬的用户你好，您正在注册西山居任务管理系统，（如果非本人注册请忽略此消息），您的验证码为："+identifyCode;
        try{
            emailService.sendMail(reciver,"REGIST",content);
            jedisClientTemplate.set(JedisEnum.IDENTIFYCODE.getName()+Mail,identifyCode);
            jedisClientTemplate.timeout(JedisEnum.IDENTIFYCODE.getName()+Mail,identifyCodeTimeout);
            response.setCode(0);
            response.setMsg(identifyCode);
        }catch (Exception e){
            response.setCode(500);
        }
        return response;
    }

    @Override
    public CommonApiResponse followProject(Long projectId, int follow, String mail) {

        CommonApiResponse response = new CommonApiResponse();
        Long userId = userMapper.getIdByMail(mail);
        Long projectUserId = Long.valueOf(projectMapper.getProjectInfoByProjectId(projectId).getUserId());
        if(userId == projectUserId){
            projectMapper.updateFollow(projectId,follow);
        }
        try{
            taskMapper.updateFollow(projectId,userId,follow);
        }catch (Exception e){
            response.setCode(500);
           new Throwable(e);
        }

        return response;
    }

    @Override
    public UserBaseInfoResponse getBaseInfo(String mail) {

        UserBaseInfoResponse response = new UserBaseInfoResponse();
        UserDto userDto = userMapper.getUserInfoByMail(mail);
        response.setName(userDto.getName());
        response.setAdminLevel(adminMapper.getAdminLevel(userDto.getId()));
        response.setImg(userDto.getImg());
        List<MessageDto>  messageDtoList = new ArrayList<>();
        messageDtoList.addAll(userMapper.getNotice(userDto.getId()));
        messageDtoList.addAll(userMapper.getNeedHandle(userDto.getId()));
        messageDtoList.addAll(userMapper.getMessage(userDto.getId()));

        response.setData(messageDtoList);
        return response;
    }

    @Override
    public CommonApiResponse deleteMessage(String mail,String type) {
        CommonApiResponse response = new CommonApiResponse();
        try{
            Long userId = userMapper.getIdByMail(mail);
            switch (type){
                case "待办":
                    userMapper.deleteNeedHandle(userId);
                    break;
                case "消息":
                    userMapper.deleteMesssage(userId);
                    break;
                case "通知":
                    userMapper.deleteNotice(userId);
                    break;
            }
        }catch (Exception e){
           response.setCode(500);
        }
        return response;
    }

    @Override
    public UserInfoResponse getUserInfo(String mail) {
        UserInfoResponse response = new UserInfoResponse();
        UserDto dto = userMapper.getUserInfoByMail(mail);
        dto.setDepartment(commonMapper.getDepartNameById(dto.getDepartmentId()));
        response.setData(dto);
        return response;
    }

    @Override
    public CommonApiResponse updateInfo(AddUserRequest request) {
        CommonApiResponse response = new CommonApiResponse();
        userMapper.updateUser(request);
        return response;
    }

    @Override
    public CommonApiResponse updataPass(UserRegisterRequest request) {
        int code  =  0;
        String msg =  "";
        CommonApiResponse commonApiResponse = new CommonApiResponse();
        String identifyCode = request.getIdentifyCode();
        String redisIdentifyCode = jedisClientTemplate.get(JedisEnum.IDENTIFYCODE.getName()+request.getMail());

        if(redisIdentifyCode!=null&&redisIdentifyCode.equals(identifyCode))
        {
            userMapper.updatePass(request.getMail(),request.getPass());
        }else{
            code = 500;
            msg = "验证码不匹配";
        }
        commonApiResponse.setCode(code);
        commonApiResponse.setMsg(msg);

        return commonApiResponse;
    }

    @Override
    public void saveHeadImg(String mail, String imgPath) {
        userMapper.updateHeadImg( mail,imgPath);
    }
}
