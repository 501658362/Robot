package top.chenyanjin.robot.lol.thread;

import com.sun.jna.platform.win32.WinDef;
import lombok.Data;
import top.chenyanjin.robot.lol.enums.ClientModeEnum;

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
    public static AtomicInteger mode = new AtomicInteger();

    /**
     * 单人还是组队
     */
    public static volatile ClientModeEnum clientTeamMode = ClientModeEnum.TEAM;
    /**
     * 房主还是队友
     */
    public static volatile ClientModeEnum clientRuleMode = ClientModeEnum.TEAM_OWNER;
    /**
     * 客户端当前页面
     */
    public static volatile AtomicInteger clientCurrentPage = new AtomicInteger();


}
