package top.chenyanjin.robot.lol.thread;

import com.google.common.collect.Lists;
import com.sun.jna.platform.win32.WinDef;
import lombok.Data;
import top.chenyanjin.robot.lol.enums.ClientModeEnum;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: chenchaopeng
 * Date: 2020/11/29
 */
@Data
public class GlobalData {
    public static volatile WinDef.HWND hwnd = new WinDef.HWND();

    /**
     * 1=游戏中；2=客户端；3登录器；
     */
    public static AtomicInteger mode = new AtomicInteger(0);

    /**
     * 单人还是组队
     */
    public static volatile ClientModeEnum clientTeamMode = ClientModeEnum.TEAM;
    /**
     * 房主还是队友
     */
    public static volatile ClientModeEnum clientRuleMode = ClientModeEnum.UNKNOWN;
    /**
     * 客户端当前页面
     */
    public static volatile AtomicInteger clientCurrentPage = new AtomicInteger();

    public static volatile AtomicInteger matchingDelay = new AtomicInteger(1000);

    public static List<String> heroNameList = Lists.newArrayList();
    public static List<String> teamMateList = Lists.newArrayList();


}
