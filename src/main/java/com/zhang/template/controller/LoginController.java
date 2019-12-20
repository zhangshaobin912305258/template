package com.zhang.template.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.zhang.template.service.UserServiceImpl;
import com.zhang.template.vo.LoginVo;
import com.zhang.template.vo.Result;
import com.zhang.template.vo.constEnum.ResultState;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private Producer producer;

    @Autowired
    private UserServiceImpl userService;

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

    @PostMapping("/login")
    public Result login(@RequestBody LoginVo loginInfo,HttpServletRequest request) {
        String captcha = loginInfo.getCaptcha();
        captcha = captcha.trim();
        Object captchaCorrect = request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (captcha.equalsIgnoreCase((String)captchaCorrect)) {
            return userService.login(loginInfo,request);
        }
        return Result.error(ResultState.INVALID_CAPTCHA);
    }


}
