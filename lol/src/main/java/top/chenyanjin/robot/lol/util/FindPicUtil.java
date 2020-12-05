package top.chenyanjin.robot.lol.util;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FindPicUtil {

    //屏幕截图
    BufferedImage screenShotImage;
    //查找目标图片
    BufferedImage keyImage;

    //屏幕截图宽度
    int scrShotImgWidth;
    //屏幕截图高度
    int scrShotImgHeight;

    //查找目标图片宽度
    int keyImgWidth;
    //查找目标图片高度
    int keyImgHeight;
    //屏幕截图RGB数据
    int[][] screenShotImageRgbData;
    //查找目标图片RGB数据
    int[][] keyImageRgbData;
    List<Point> result = new ArrayList();
    Point oneResult = null;
    boolean isFindOne = false;


    public FindPicUtil(String screenPath, List<String> keyImagePathList) {
        screenShotImage = this.getBfImageFromPath(screenPath);
        screenShotImageRgbData = this.getImageGRB(screenShotImage);
        scrShotImgWidth = screenShotImage.getWidth();
        scrShotImgHeight = screenShotImage.getHeight();
        //开始查找
        this.findImage(keyImagePathList);
    }

    public FindPicUtil(String screenPath, List<String> keyImagePathList, int test) {
        screenShotImage = this.getBfImageFromPath(screenPath);
        screenShotImageRgbData = this.getImageGRB(screenShotImage);
        scrShotImgWidth = screenShotImage.getWidth();
        scrShotImgHeight = screenShotImage.getHeight();
        //开始查找
        this.findImage1(keyImagePathList);
    }

    public FindPicUtil(BufferedImage current, List<String> keyImagePathList) {
        screenShotImage = current;
        screenShotImageRgbData = this.getImageGRB(screenShotImage);
        scrShotImgWidth = screenShotImage.getWidth();
        scrShotImgHeight = screenShotImage.getHeight();
        //开始查找
        this.findImage(keyImagePathList);
    }

    public FindPicUtil(BufferedImage current, List<String> keyImagePathList, boolean findOne) {
        screenShotImage = current;
        screenShotImageRgbData = this.getImageGRB(screenShotImage);
        scrShotImgWidth = screenShotImage.getWidth();
        scrShotImgHeight = screenShotImage.getHeight();
        this.isFindOne = findOne;
        //开始查找
        this.findImage(keyImagePathList);
    }

    /**
     * 全屏截图
     *
     * @return 返回BufferedImage
     */
    public BufferedImage getFullScreenShot() {
        BufferedImage bfImage = null;
        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        try {
            Robot robot = new Robot();
            bfImage = robot.createScreenCapture(new Rectangle(0, 0, width, height));
        } catch (AWTException e) {
            e.printStackTrace();
        }
        return bfImage;
    }

    /**
     * 从本地文件读取目标图片
     *
     * @param keyImagePath - 图片绝对路径
     * @return 本地图片的BufferedImage对象
     */
    public BufferedImage getBfImageFromPath(String keyImagePath) {
        BufferedImage bfImage = null;
        try {
            bfImage = ImageIO.read(new File(keyImagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bfImage;
    }

    /**
     * 根据BufferedImage获取图片RGB数组
     *
     * @param bfImage
     * @return
     */
    public int[][] getImageGRB(BufferedImage bfImage) {
        int width = bfImage.getWidth();
        int height = bfImage.getHeight();
        int[][] result = new int[height][width];
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                //使用getRGB(w, h)获取该点的颜色值是ARGB，而在实际应用中使用的是RGB，所以需要将ARGB转化成RGB，即bufImg.getRGB(w, h) & 0xFFFFFF。
                result[h][w] = bfImage.getRGB(w, h) & 0xFFFFFF;
            }
        }
        return result;
    }


    /**
     * 查找图片
     */
    public void findImage(List<String> keyImagePathList) {
        out:
        for (String keyImagePath : keyImagePathList) {
            keyImage = this.getBfImageFromPath(keyImagePath);
            keyImageRgbData = this.getImageGRB(keyImage);
            keyImgWidth = keyImage.getWidth();
            keyImgHeight = keyImage.getHeight();
            //遍历屏幕截图像素点数据
            for (int y = 0; y < scrShotImgHeight - keyImgHeight; y++) {
                for (int x = 0; x < scrShotImgWidth - keyImgWidth; x++) {
                    //根据目标图的尺寸，得到目标图四个角映射到屏幕截图上的四个点，
                    //判断截图上对应的四个点与图B的四个角像素点的值是否相同，
                    //如果相同就将屏幕截图上映射范围内的所有的点与目标图的所有的点进行比较。
                    if ((keyImageRgbData[0][0] ^ screenShotImageRgbData[y][x]) == 0
                            && (keyImageRgbData[0][keyImgWidth - 1] ^ screenShotImageRgbData[y][x + keyImgWidth - 1]) == 0
                            && (keyImageRgbData[keyImgHeight - 1][keyImgWidth - 1] ^ screenShotImageRgbData[y + keyImgHeight - 1][x + keyImgWidth - 1]) == 0
                            && (keyImageRgbData[keyImgHeight - 1][0] ^ screenShotImageRgbData[y + keyImgHeight - 1][x]) == 0) {

                        boolean isFinded = isMatchAll(y, x);
                        //如果比较结果完全相同，则说明图片找到，填充查找到的位置坐标数据到查找结果数组。
                        if (isFinded) {
                            result.add(new Point(x, y));
                            if (isFindOne) {
                                oneResult = new Point(x, y);
                                return;
                            }
                            continue out;
                        }
                    }
                }
            }
        }
    }

    /**
     * 查找图片
     */
    public void findImage1(List<String> keyImagePathList) {
        out:
        for (String keyImagePath : keyImagePathList) {
            keyImage = this.getBfImageFromPath(keyImagePath);
            keyImageRgbData = this.getImageGRB(keyImage);
            keyImgWidth = keyImage.getWidth();
            keyImgHeight = keyImage.getHeight();
            //遍历屏幕截图像素点数据
            for (int y = 0; y < scrShotImgHeight - keyImgHeight; y++) {
                for (int x = 0; x < scrShotImgWidth - keyImgWidth; x++) {
                    //根据目标图的尺寸，得到目标图四个角映射到屏幕截图上的四个点，
                    //判断截图上对应的四个点与图B的四个角像素点的值是否相同，
                    //如果相同就将屏幕截图上映射范围内的所有的点与目标图的所有的点进行比较。

                    int cx = x;
                    int count = 0;
                    for (int kw = 0; kw < keyImgWidth; kw++) {
                        if (keyImageRgbData[0][kw] == screenShotImageRgbData[y][cx++]) {
                            count++;
                        }
                    }
                    log.info(count + "");
                    if (count / keyImgWidth > 0.8) {
                        boolean isFinded = isMatchAll1(y, x);
                        //如果比较结果完全相同，则说明图片找到，填充查找到的位置坐标数据到查找结果数组。
                        if (isFinded) {
                            result.add(new Point(x, y));
                            if (isFindOne) {
                                oneResult = new Point(x, y);
                                return;
                            }
                            continue out;
                        }
                    }

                }
            }
        }
    }

    /**
     * 判断屏幕截图上目标图映射范围内的全部点是否全部和小图的点一一对应。
     *
     * @param y - 与目标图左上角像素点想匹配的屏幕截图y坐标
     * @param x - 与目标图左上角像素点想匹配的屏幕截图x坐标
     * @return
     */
    public boolean isMatchAll(int y, int x) {
        int biggerY = 0;
        int biggerX = 0;
        int xor = 0;
        for (int smallerY = 0; smallerY < keyImgHeight; smallerY++) {
            biggerY = y + smallerY;
            for (int smallerX = 0; smallerX < keyImgWidth; smallerX++) {
                biggerX = x + smallerX;
                if (biggerY >= scrShotImgHeight || biggerX >= scrShotImgWidth) {
                    return false;
                }
                xor = keyImageRgbData[smallerY][smallerX] ^ screenShotImageRgbData[biggerY][biggerX];
                if (xor != 0) {
                    return false;
                }
            }
            biggerX = x;
        }
        return true;
    }


    public boolean isMatchAll1(int y, int x) {
        int biggerY = 0;
        int biggerX = 0;
        int xor = 0;
        int count = 0;
        int total = 0;
        wxx:
        for (int smallerY = 0; smallerY < keyImgHeight; smallerY++) {
            biggerY = y + smallerY;
            for (int smallerX = 0; smallerX < keyImgWidth; smallerX++) {
                total++;
                biggerX = x + smallerX;
                if (biggerY >= scrShotImgHeight || biggerX >= scrShotImgWidth) {
                    continue wxx;
                }
                xor = keyImageRgbData[smallerY][smallerX] ^ screenShotImageRgbData[biggerY][biggerX];
                if (xor != 0) {
                    continue wxx;
                }
            }
            biggerX = x;
            count++;
        }
        if (count / total > 0.8) {
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
//        String keyImagePath = "inviteBtn.png";
        String keyImagePath = "invite2.bmp";
        FindPicUtil demo = new FindPicUtil("D:\\IdeaProjects\\Robot\\error\\判断蓝红方1606922595090.png", Lists.newArrayList("C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\游戏中\\蓝色方对面中高地塔1.bmp")
                , 1);
        System.out.println(demo.result);
    }

}