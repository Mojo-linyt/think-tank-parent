package cn.linyt.thinktanklogin.controller;

import cn.linyt.common.response.Result;
import cn.linyt.common.annotation.JwtIgnore;
import cn.linyt.thinktanklogin.entity.Audience;
import cn.linyt.common.entity.User;
import cn.linyt.thinktanklogin.utils.JwtTokenUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * @ClassName AdminUserController
 * @Description TODO
 * @Author Mojo
 * @Date 2020/4/14 5:02
 * @Version 1.0
 **/
@Slf4j
@CrossOrigin
@RestController
public class AdminUserController {

    @Autowired
    private Audience audience;

    @JwtIgnore
    @PostMapping("/login")
    public Result adminLogin(HttpServletResponse response, String username, String password) throws IOException {

        // 这里模拟测试, 默认登录成功，返回用户ID和角色信息
        if (!"admin".equals(username) && !"666666".equals(password)) {
            log.info("### username: " + username + " ###");
            log.info("### password: " + password + " ###");
            log.info("### username and password is fail! ###");
            return Result.FAIL("用户名或密码错误!");
        }
        String userId = UUID.randomUUID().toString();
        String role = "admin";

        // 创建token
        String token = JwtTokenUtil.createJWT(userId, username, role, audience, response);
        log.info("### 登录成功, token={} ###", token);

        // 将token放在响应头
        response.setHeader(JwtTokenUtil.AUTH_HEADER_KEY, JwtTokenUtil.TOKEN_PREFIX + token);
        // 将token响应给客户端
        JSONObject result = new JSONObject();
        result.put("token", token);
        return Result.SUCCESS(result);
    }

}
