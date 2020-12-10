package top.chenyanjin.robot.lol;

import com.google.common.collect.Lists;
import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;
import lombok.extern.slf4j.Slf4j;
import top.chenyanjin.robot.lol.enums.ClientModeEnum;
import top.chenyanjin.robot.lol.thread.ClientService;
import top.chenyanjin.robot.lol.thread.*;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author CHEN
 */
@Slf4j
public class Main implements HotkeyListener {
    public static ClientService clientService = new ClientService("clientService");
    public static MatchingGame matchingGame = new MatchingGame("matchingGame");
    public static SelectHeroGame selectHeroGame = new SelectHeroGame("selectHeroGame");
    public static PlayGame playGame = new PlayGame();

    public static void main(String[] args) throws IOException {
        JIntellitype.getInstance().addHotKeyListener(new Main());
        JIntellitype.getInstance().registerHotKey(1, 0, KeyEvent.VK_F8);


        log.info("开始执行");
        setSetting();

        Thread wd = new WindowDetection();
        wd.start();
        matchingGame.start();
        selectHeroGame.start();
        playGame.start();
        clientService.start();
//        int i = -1;
//        while (true) {
//            switch (GlobalData.mode.get()) {
//                case 1:
//                    // 对局中
//                    break;
//                case 2:
//                    // 客户端
//                    if (!selectHeroGame.isAlive() && !clientService.isAlive()) {
//                        clientService.start();
//                    }
//                    break;
//                case 3:
//                    // 登录器
//                    break;
//                default:
//                    break;
//            }
//            DelayUtil.delay(5000L);
//        }


    }

    /**
     * Event fired when a WM_HOTKEY message is received that was initiated
     * by this application.
     * <p>
     *
     * @param identifier the unique Identifer the Hotkey was assigned
     */
    @Override
    public void onHotKey(int identifier) {
        System.exit(1);
    }

    private static void setSetting() throws IOException {

        Properties properties = new Properties();
        // 使用InPutStream流读取properties文件
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("setting.properties"), "UTF-8"));
//        owner=true
//        teammate=拿回忆下酒s,滚出我的心ea,假装狠辛福vd,暖暖的掌心mn
//        hero=众星之子,战争女神
        properties.load(bufferedReader);
        // 获取key对应的value值
        String owner = properties.getProperty("owner");

        boolean isOwner = Boolean.parseBoolean(owner);
        if (isOwner) {
            GlobalData.clientRuleMode = ClientModeEnum.TEAM_OWNER;
        } else {
            GlobalData.clientRuleMode = ClientModeEnum.TEAM_MEMBER;
        }
        String hero = properties.getProperty("hero");
        GlobalData.heroNameList = Lists.newArrayList(hero.split(","));
        String teammate = properties.getProperty("teammate");
        GlobalData.teamMateList = Lists.newArrayList(teammate.split(","));
        GlobalData.resolution = properties.getProperty("resolution");

    }
}
