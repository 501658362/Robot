import com.cjoop.dm.DmSoft;
import org.junit.Test;
import top.chenyanjin.robot.lol.util.ImgPathUtil;

public class DmTest {

    @Test
    public void test() {
        DmSoft dm = new DmSoft();
        System.out.println("version:" + dm.Ver());
        System.out.println("path:" + dm.GetBasePath());
        String s = dm.FindPicE(0, 0, 1900, 1080, ImgPathUtil.getPath("play按钮.bmp"), "000000", 0.7, 0);
        System.out.println(s);
    }
}
