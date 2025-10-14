package com.wisteria.util;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.core.codec.Base64;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 基于Hutool的图片验证码工具类
 * 支持生成线框验证码、扭曲验证码，直接输出到响应流或转换为Base64
 */
public class CaptchaUtils {

    // 默认配置参数
    public static final int DEFAULT_WIDTH = 160;       // 验证码宽度
    public static final int DEFAULT_HEIGHT = 60;       // 验证码高度
    public static final int DEFAULT_CODE_LENGTH = 4;   // 验证码字符长度
    public static final int DEFAULT_LINE_COUNT = 5;    // 干扰线数量
    public static final String SESSION_KEY = "CAPTCHA_CODE"; // Session存储键

    /**
     * 生成默认配置的线框验证码
     *
     * @return LineCaptcha 验证码对象
     */
    public static LineCaptcha createLineCaptcha() {
        return CaptchaUtil.createLineCaptcha(
                DEFAULT_WIDTH,
                DEFAULT_HEIGHT,
                DEFAULT_CODE_LENGTH,
                DEFAULT_LINE_COUNT
        );
    }

    /**
     * 生成自定义配置的线框验证码
     *
     * @param width      宽度
     * @param height     高度
     * @param codeLength 字符长度
     * @param lineCount  干扰线数量
     * @return LineCaptcha 验证码对象
     */
    public static LineCaptcha createLineCaptcha(int width, int height, int codeLength, int lineCount) {
        return CaptchaUtil.createLineCaptcha(width, height, codeLength, lineCount);
    }

    /**
     * 生成默认配置的扭曲验证码（抗识别性更强）
     *
     * @return ShearCaptcha 验证码对象
     */
    public static ShearCaptcha createShearCaptcha() {
        return CaptchaUtil.createShearCaptcha(
                DEFAULT_WIDTH,
                DEFAULT_HEIGHT,
                DEFAULT_CODE_LENGTH,
                DEFAULT_LINE_COUNT
        );
    }

    /**
     * 生成自定义配置的扭曲验证码
     *
     * @param width      宽度
     * @param height     高度
     * @param codeLength 字符长度
     * @param lineCount  干扰线数量
     * @return ShearCaptcha 验证码对象
     */
    public static ShearCaptcha createShearCaptcha(int width, int height, int codeLength, int lineCount) {
        return CaptchaUtil.createShearCaptcha(width, height, codeLength, lineCount);
    }

    /**
     * Web场景：生成验证码并写入响应流
     * 同时将验证码文本存入Session
     *
     * @param request  请求对象
     * @param response 响应对象
     * @throws IOException IO异常
     */
    public static void generateAndWrite(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 生成验证码
        LineCaptcha captcha = createLineCaptcha();

        // 存入Session用于后续验证
        request.getSession().setAttribute(SESSION_KEY, captcha.getCode());

        // 写入响应流
        writeToResponse(captcha, response);
    }

    /**
     * 将验证码图片写入响应流（通用方法）
     *
     * @param captcha  验证码对象
     * @param response 响应对象
     * @throws IOException IO异常
     */
    public static void writeToResponse(LineCaptcha captcha, HttpServletResponse response) throws IOException {
        response.setContentType("image/png");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        try (OutputStream out = response.getOutputStream()) {
            captcha.write(out);
            out.flush();
        }
    }

    /**
     * 将验证码转换为Base64字符串（适用于前后端分离场景）
     *
     * @param captcha 验证码对象
     * @return Base64编码的图片字符串（带data:image/png;base64前缀）
     * @throws IOException IO异常
     */
    public static String toBase64(LineCaptcha captcha) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            captcha.write(baos);
            return "data:image/png;base64," + Base64.encode(baos.toByteArray());
        }
    }

    /**
     * 验证用户输入的验证码是否正确
     *
     * @param request   请求对象（用于获取Session中的验证码）
     * @param userInput 用户输入的验证码
     * @return 是否验证通过
     */
    public static boolean validate(HttpServletRequest request, String userInput) {
        if (userInput == null) {
            return false;
        }

        // 从Session获取存储的验证码
        String sessionCode = (String) request.getSession().getAttribute(SESSION_KEY);
        if (sessionCode == null) {
            return false;
        }

        // 忽略大小写验证
        boolean result = userInput.equalsIgnoreCase(sessionCode);

        // 验证后清除Session中的验证码（防止重复使用）
        if (result) {
            request.getSession().removeAttribute(SESSION_KEY);
        }
        return result;
    }
}
