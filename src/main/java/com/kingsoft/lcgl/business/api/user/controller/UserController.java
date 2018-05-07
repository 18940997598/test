package com.kingsoft.lcgl.business.api.user.controller;

import com.kingsoft.lcgl.business.api.admin.dto.AddUserRequest;
import com.kingsoft.lcgl.business.api.admin.dto.UserInfoResponse;
import com.kingsoft.lcgl.business.api.user.dto.*;
import com.kingsoft.lcgl.business.api.user.service.UserService;
import com.kingsoft.lcgl.business.common.support.CommonApiResponse;
import com.kingsoft.lcgl.business.common.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yangdiankang on 2017/11/15.
 */

@RestController
@RequestMapping("/api/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest httpRequest;
    @Value("${file.upload-path}")
    private String uploadPath;
    @Value("${file.acess-path}")
    private String acessPath;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="login", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public UserLoginRespone login(@RequestBody UserLoginRquest request) {
        return  userService.login(request);
    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="register", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonApiResponse register(@RequestBody UserRegisterRequest request) {
        return  userService.addProject(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="getBaseInfo", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public UserBaseInfoResponse getBaseInfo() {

        return  userService.getBaseInfo(httpRequest.getHeader("mail"));
    }

    /**
     * 通过邮箱发送验证码
     * @param
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="mailSendIdentifyCode", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonApiResponse mailSendIdentifyCode(@RequestBody String  mail) {
        return  userService.mailSendIdentifyCode(mail);
    }

    /**
     * 取消或者关注项目
     * @param request
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="followProject", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonApiResponse followProject(@RequestBody FollowProjectRequest request) {
        return  userService.followProject(request.getProjectId(),request.getFollow(),httpRequest.getHeader("mail"));
    }


    /**
     * 删除通知消息
     * @param type
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="deleteMessage", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonApiResponse deleteMessage(String type) {
        return  userService.deleteMessage(httpRequest.getHeader("mail"),type);
    }

    /**
     * 获取基本用户信息
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="getUserInfo", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public UserInfoResponse getUserInfo() {
        return  userService.getUserInfo(httpRequest.getHeader("mail"));
    }

    /**
     * 更新用户信息
     * @param request
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="updateInfo", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonApiResponse updateUser(@RequestBody AddUserRequest request) {
        return  userService.updateInfo(request);
    }

    /**
     * 修改密码
     * @param request
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="updataPass", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonApiResponse updataPass(@RequestBody UserRegisterRequest request) {
        return  userService.updataPass(request);
    }

    /**
     * 上传头像
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value="/uploadHeadImg", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String uploadHeadImg(@RequestParam("file") MultipartFile file,
                                          HttpServletRequest request) {
        String mail = httpRequest.getHeader("mail");
        String fileName = file.getOriginalFilename();
        String imgPath = acessPath+fileName;
        logger.info("上传图片：uploadPath:"+uploadPath+"+accessPath:"+imgPath);
        try {
            FileUtil.uploadFile(file.getBytes(), uploadPath, fileName);
            try {
                userService.saveHeadImg(mail,imgPath);
            }catch (Exception e){
                return "保存路径失败";
            }

        } catch (Exception e) {
            return "上传失败";
        }

        return "上传成功";
    }
}
