package com.kingsoft.lcgl.business.api.user.mapper;

import com.kingsoft.lcgl.business.api.admin.dto.AddUserRequest;
import com.kingsoft.lcgl.business.api.admin.dto.UserInfoResponse;
import com.kingsoft.lcgl.business.api.user.dto.MessageDto;
import com.kingsoft.lcgl.business.api.user.dto.UserDto;
import com.kingsoft.lcgl.business.api.user.dto.UserRegisterRequest;
import com.kingsoft.lcgl.business.common.support.CommonApiResponse;
import com.kingsoft.lcgl.core.mybatis.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yangdiankang on 2017/11/15.
 */
@Mapper
public interface UserMapper {

    Long getIdByMail(@Param("mail")String mail);

    void addUser(UserRegisterRequest request);

    UserDto getUserInfoByMail(@Param("mail")String mail);

    String getMailById(@Param("id") Long id);

    UserDto getUserInfoByprojectId(@Param("projectId") Long projectId);

    String getNameById(@Param("userId")Long userId);

    List<UserDto> getAllUser(@Param("userName")String userName, @Param("departmentId") Long departmentId);

    UserDto getUserInfoById(@Param("userId")Long userId);

    void updateUser(AddUserRequest request);

    CommonApiResponse deleteUser(@Param("userId")Long userId);

    void deleteUsers(@Param("userIds")Long userIds[]);

    List<MessageDto> getMessage(@Param("userId")Long id);

    List<MessageDto> getNotice(@Param("userId")Long id);

    List<MessageDto> getNeedHandle(@Param("userId")Long id);

    void deleteNeedHandle(@Param("userId")Long userId);

    void deleteMesssage(@Param("userId")Long userId);

    void deleteNotice(@Param("userId")Long userId);

    void delteAllHandle();

    void addHandle(@Param("userId") Long userId,@Param("endTime") Long endTime,@Param("content")String content,@Param("title") String title,@Param("projectId") Long projectId);

    void addNotice(@Param("content")String content, @Param("userId")Long userId, @Param("title")String title);


    void updatePass(@Param("mail")String mail, @Param("pass")String pass);

    void updateHeadImg(@Param("mail")String mail,@Param("imgPath") String imgPath);

    List<UserDto> getUserByDepartId(@Param("departmentId") Long id);
}
