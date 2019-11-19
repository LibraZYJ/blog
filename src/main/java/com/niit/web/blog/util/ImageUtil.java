package com.niit.web.blog.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * @author yujie_zhao
 * @ClassName ImageUtil
 * @Description TODO
 * @Date 2019/11/19
 * @Version 1.0
 **/
public class ImageUtil {
    public  static BufferedImage getImage(int width, int height, String content){
        BufferedImage img   = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = (Graphics2D) img.getGraphics();
        graphics2D.setBackground(Color.ORANGE);
        graphics2D.fillRect(50,50,width,height);

        //字体颜色
        graphics2D.setPaint(Color.GREEN);
        Font font = new Font(  "Serif",Font.BOLD,10);
        graphics2D.setFont(font);
        graphics2D.drawString(content,width/6,height/3);
        return img;
    }

    public static void main(String[] args) throws IOException {
        //在缓冲区生成图片，用随机生成的字符串
        BufferedImage img = ImageUtil.getImage(120,35, StringUtil.getcode());
        //指定图片输出目录和图片
        String filePath = "D:/WebPicture/code4.jpg";
        File file = new File(filePath);
        //通过IO和write方法，将图片以指定格式输出到指定文件
        ImageIO.write(img,"jpg",file);
    }
}
