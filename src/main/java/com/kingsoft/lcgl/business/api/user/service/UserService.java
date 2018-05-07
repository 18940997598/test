package com.kingsoft.lcgl.business.api.user.service;

import com.kingsoft.lcgl.business.api.admin.dto.AddUserRequest;
import com.kingsoft.lcgl.business.api.admin.dto.UserInfoResponse;
import com.kingsoft.lcgl.business.api.user.dto.*;
import com.kingsoft.lcgl.business.common.support.CommonApiResponse;
/**
 * Created by yangdiankang on 2017/11/15.
 */
public interface UserService {
    CommonApiResponse addProject(UserRegisterRequest request);
    CommonApiResponse mailSendIdentifyCode(String  mail);
    UserLoginRespone login(UserLoginRquest request);
    CommonApiResponse followProject(Long projectId, int follow, String mail);

    UserBaseInfoResponse getBaseInfo(String mail);

    CommonApiResponse deleteMessage(String mail,String type);

    UserInfoResponse getUserInfo(String mail);

    CommonApiResponse updateInfo(AddUserRequest request);

    CommonApiResponse updataPass(UserRegisterRequest request);

    void saveHeadImg(String mail, String imgPath);
}
