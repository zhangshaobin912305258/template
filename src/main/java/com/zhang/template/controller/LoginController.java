package com.zhang.template.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.zhang.template.service.impl.UserServiceImpl;
import com.zhang.template.vo.LoginVo;
import com.zhang.template.vo.Result;
import com.zhang.template.vo.constEnum.ResultState;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
public class LoginController {

    private final Producer producer;

    private final UserServiceImpl userService;

    public LoginController(Producer producer, UserServiceImpl userService) {
        this.producer = producer;
        this.userService = userService;
    }

    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        // 生成文字验证码
        String text = producer.createText();
        // 生成图片验证码
        BufferedImage image = producer.createImage(text);
        // 保存到验证码到 session
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }

    @PostMapping(value = "/login")
    public Result login(@RequestBody LoginVo loginInfo, HttpServletRequest request) {
        String captcha = loginInfo.getCaptcha();
        if (StringUtils.isEmpty(captcha)) {
            return Result.error(ResultState.INVALID_CAPTCHA);
        }
        // 校验验证码
        Object sessionCaptcha = request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (sessionCaptcha == null) {
            return Result.error(ResultState.INVALID_CAPTCHA);
        }
        if (!captcha.equalsIgnoreCase((String) sessionCaptcha)) {
            return Result.error(ResultState.INVALID_CAPTCHA);
        }
        return userService.login(loginInfo, request);
    }


    public void test2() {
        System.out.println(101010);
        System.out.println("111");
        System.out.println("222");
        System.out.println(555);
        System.out.println(666);
        System.out.println(777);
        System.out.println(888);
        System.out.println(999);
    }

    public void test1() {
        System.out.println(111);
        System.out.println(222);
    }
}
