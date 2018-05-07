package com.kingsoft.lcgl.core.webservice.service.impl;


import com.kingsoft.lcgl.business.api.common.dto.TokenRespone;
import com.kingsoft.lcgl.business.common.util.RandomUtil;
import com.kingsoft.lcgl.core.redis.util.JedisEnum;
import com.kingsoft.lcgl.core.redis.util.JedisClientTemplate;
import com.kingsoft.lcgl.core.webservice.exception.RestCheckException;
import com.kingsoft.lcgl.core.webservice.exception.TokenException;
import com.kingsoft.lcgl.core.webservice.service.TokenService;
import com.kingsoft.lcgl.core.webservice.support.ExceptionMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


/**
 * Created by yangdiankang on 2017/12/29.
 */
@Service
public class TokenServiceImpl implements TokenService {
    private static final Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);
    @Autowired
    private JedisClientTemplate jedisClientTemplate;
    @Value("${timeout.tokenTimeout}")
    private int tokenTimeout;
    @Value("${timeout.refreshTokenTimeout}")
    private int refreshTokenTimeout;

    @Override
    public TokenRespone commonGetToken(String mail,String refreshToken ) {
        TokenRespone respone = new TokenRespone();

        String tokenNew = RandomUtil.getToken();
        String refreshTokenNew = RandomUtil.getRefreshToken(mail);
        HashMap<String,String> map = new HashMap<>();
        map.put("token",tokenNew);
        map.put("refreshToken",refreshTokenNew);

        /**
         * redis 操作：
         * 1.清除原先的 token,refreshToken
         * 2.插入新的token,refreshToken
         * 3.更新mail
         */
        String oldToken = jedisClientTemplate.hmget(JedisEnum.MAIL.getName()+mail,"token").get(0);
        String oldRefresh =  jedisClientTemplate.hmget(JedisEnum.MAIL.getName()+mail,"refreshToken").get(0);
        jedisClientTemplate.del(JedisEnum.TOKEN.getName()+oldToken);
        jedisClientTemplate.del(JedisEnum.REFRESHTOKEN.getName()+oldRefresh);

        jedisClientTemplate.set(JedisEnum.TOKEN.getName()+tokenNew,mail);
        jedisClientTemplate.set(JedisEnum.REFRESHTOKEN.getName()+refreshTokenNew,mail);
        jedisClientTemplate.hmset(JedisEnum.MAIL.getName()+mail,map);
        jedisClientTemplate.timeout(JedisEnum.TOKEN.getName()+tokenNew,tokenTimeout);
        jedisClientTemplate.timeout(JedisEnum.REFRESHTOKEN.getName()+refreshTokenNew,refreshTokenTimeout);
        respone.setCode(0);
        respone.setMail(mail);
        respone.setToken(tokenNew);
        respone.setRefreshToken(refreshTokenNew);
        return respone;
    }

    @Override
    public String commonGetRegreshToken(String mail) {
        return null;
    }

    @Override
    public void checkToken(HttpServletRequest request) {
        String token  = request.getHeader("token");
        String refreshToken  = request.getHeader("refreshToken");
        System.out.println("token:"+token+"+：refreshToken:"+refreshToken);

        if(token==null||refreshToken==null){
            return;
        }
        String oldToken = jedisClientTemplate.hmget(JedisEnum.MAIL.getName()+request.getHeader("mail"),"token").get(0);
        String oldFreshToken = jedisClientTemplate.hmget(JedisEnum.MAIL.getName()+request.getHeader("mail"),"refreshToken").get(0);

        String tToken = jedisClientTemplate.get(JedisEnum.TOKEN.getName()+token);
        String rToken = jedisClientTemplate.get(JedisEnum.REFRESHTOKEN.getName()+refreshToken);

        if(tToken != null){
            if(rToken == null){
                throw new TokenException(ExceptionMsg.getRestExceptionResponse(ExceptionMsg.AUTHORITY_ERROR));
            }
        }else {
            throw new TokenException(ExceptionMsg.getRestExceptionResponse(ExceptionMsg.TOKEN_ERROR));
        }
    }
}
