package com.niit.web.blog.controller;
import com.niit.web.blog.util.StringUtil;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;

/**
 * @author yujie_zhao
 * @ClassName CodeController
 * @Description TODO
 * @Date 2019/11/19
 * @Version 1.0
 **/
@WebServlet(urlPatterns = {"/api/code"})
public class CodeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //调用字符串生成方法，生成随机字符串
        String codeString = StringUtil.getcode();
        //存入session
        HttpSession session = req.getSession();
        System.out.println(session.getId());
        session.setAttribute("code",codeString);
        int width = 130;
        int height = 35;
        Random random = new Random();
        Color color = new Color(130,182,45);
        //1. 创建图片缓存区，传参为宽高和图片类型
        BufferedImage bi   = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        //2.获取画笔并绘制
        Graphics g = bi.getGraphics();;
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD,16));
        g.setColor(color);
        g.fillRect(0,0,width,height);
        g.setColor(Color.DARK_GRAY);
        g.drawString(codeString,width/4,height/2);
        //3.向客户端传输图片
        resp.setContentType("image/jpg");
        //4.通过输出流，将数组内容传送到客户端
        ServletOutputStream out = resp.getOutputStream();
        ImageIO.write(bi,"jpg",out);
        out.close();
    }
}
