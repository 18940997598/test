package com.kingsoft.lcgl.core.webservice.service;

import com.kingsoft.lcgl.business.api.common.dto.EmailDto;
import com.kingsoft.lcgl.business.api.common.dto.EmailListRespone;
import com.kingsoft.lcgl.business.api.common.dto.TokenRespone;
import com.kingsoft.lcgl.business.common.support.CommonApiResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by yangdiankang on 2018/1/3.
 */
public interface TokenService {
    /**
     * 获取token
     * @return
     */
    TokenRespone commonGetToken(String mail,String refreshToken);
    /**
     * 获取regreshToken
     * @return
     */
    String commonGetRegreshToken(String mail);

    void checkToken(HttpServletRequest request);
}
