//import net.sourceforge.tess4j.ITesseract;
//import net.sourceforge.tess4j.Tesseract;
//import net.sourceforge.tess4j.TesseractException;
//import org.junit.Test;
//
//import java.io.File;
//
//public class TessTest {
//
//
//    @Test
//    public void t() {
//        // 图片和语言库的存放路径
//        String path = "E://com//dream//begin//Tesseract_OCR";
//        // 图片路径
//        File file = new File("游戏中\\tess.bmp");
//        System.out.println("本地文件路径：".concat(file.getPath()));
//        // 创建ITesseract对象
//        ITesseract instance = new Tesseract();
//        // 设置训练库的位置
//        instance.setDatapath("游戏中\\tessdata");
//        // 根据需求选择语言库 chi_sim ：简体中文， eng
//        instance.setLanguage("chi_sim");
//        String result = null;
//        try {
//            // 识别开始获取时间戳
//            long startTime = System.currentTimeMillis();
//            // 图片识别
//            result =  instance.doOCR(file);
//            // 识别结束时间戳
//            long endTime = System.currentTimeMillis();
//            System.out.println("Time is：" + (endTime - startTime) + " 毫秒");
//        } catch (TesseractException e) {
//            e.printStackTrace();
//        }
//        // 识别信息
//        System.out.println("result: ".concat(result));
//    }
//}
