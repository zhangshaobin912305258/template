package com.zhang.template.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.map.MapUtil;
import com.google.code.kaptcha.Producer;
import com.zhang.template.constants.CaptchaConstants;
import com.zhang.template.constants.ResultState;
import com.zhang.template.util.IdUtils;
import com.zhang.template.util.RedisCache;
import com.zhang.template.vo.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 验证码操作处理
 */
@RestController
@RequiredArgsConstructor
public class CaptchaController {
    private final Producer captchaProducer;

    private final Producer captchaProducerMath;

    private final RedisCache redisCache;

    // 验证码类型
    @Value("${captchaType}")
    private String captchaType;

    /**
     * 生成验证码
     */
    @GetMapping("/captchaImage")
    public Result getCode() {
        // 保存验证码信息
        String uuid = IdUtils.simpleUUID();
        String verifyKey = CaptchaConstants.CAPTCHA_CODE_KEY + uuid;
        String capStr = null, code = null;
        BufferedImage image = null;
        // 生成验证码
        if ("math".equals(captchaType)) {
            String capText = captchaProducerMath.createText();
            capStr = capText.substring(0, capText.lastIndexOf("@"));
            code = capText.substring(capText.lastIndexOf("@") + 1);
            image = captchaProducerMath.createImage(capStr);
        } else if ("char".equals(captchaType)) {
            capStr = code = captchaProducer.createText();
            image = captchaProducer.createImage(capStr);
        }
        redisCache.setCacheObject(verifyKey, code, CaptchaConstants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", os);
        } catch (IOException e) {
            return Result.error(ResultState.IMAGE_ERROR);
        }
        return Result.ok(MapUtil.builder()
                .put("uuid", uuid)
                .put("img", Base64.encode(os.toByteArray()))
                .build());
       /* AjaxResult ajax = AjaxResult.success();
        ajax.put("uuid", uuid);
        ajax.put("img", Base64.encode(os.toByteArray()));
        return ajax;*/
    }
}
