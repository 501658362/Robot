package top.chenyanjin.robot.lol.thread;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import top.chenyanjin.robot.lol.enums.ClientModeEnum;
import top.chenyanjin.robot.lol.util.DelayUtil;
import top.chenyanjin.robot.lol.util.DmPicUtil;
import top.chenyanjin.robot.lol.util.ErrorUtil;
import top.chenyanjin.robot.lol.util.RobotUtil;

import java.awt.*;
import java.util.List;

/**
 * @author CHEN
 */
@Slf4j
public class ClientService extends Thread {

    /**
     * Allocates a new {@code Thread} object. This constructor has the same
     * effect as
     * {@code (null, null, name)}.
     *
     * @param name the name of the new thread
     */
    public ClientService(String name) {
        super(name);
    }

    public static volatile int currentPage = 1;

    @Override
    public void run() {
        while (true) {
            while (GlobalData.mode.get() == 2) {
//                if (Main.selectHeroGame.isAlive()) {
//                    log.info("选英雄线程正在运行中");
//                    DelayUtil.delay(5000L);
//                    return;
//                }
                currentPage = getCurrentPage();

                // 首页
                if (currentPage == 1) {
                    enterRoom();
                }
                // 房间页
                if (currentPage == 2) {
                    log("在房间中");
                    // 检测是否需要选位置
                    RobotUtil.delay(3000);
                    boolean check = DmPicUtil.check("补位.bmp", "补位1.bmp");
                    if (!check) {
                        log("还没有选位置");
                        boolean b = selectPlayPosition();
//                        if (!b) {
//                            log("选位置失败");
//                            return;
//                        }
                    } else {
                        log("已经选好位置了");
                    }

                    RobotUtil.delay(3000);
                    if (isOwner()) {
                        // 检测是否需要邀请
                        RobotUtil.delay(3000);
                        inviteTeamMate();
                        RobotUtil.delay(3000);
                        clickFindGame();
                        RobotUtil.delay(3000);
                        matchingGame();
                    } else {
                        // 等待游戏开始
                        log("等待游戏开始匹配");
                        waitingForInvite();
                        matchingGame();

                        RobotUtil.delay(5000);
                    }
                }
                // 匹配游戏中
                if (currentPage == 3) {
                    log("匹配游戏中");
                    //TODO 等待接受游戏
                    matchingGame();
                    RobotUtil.delay(5000);
                }
                // 选择英雄中
                if (currentPage == 4) {
                    log("选择英雄中");
                    DelayUtil.delay(5000L);
                }
                // 秒退惩罚中
                if (currentPage == 5) {
                    log("秒退惩罚中");
                    DelayUtil.delay(5000L);
                }

                // 接受对局
                if (currentPage == 6) {
                    log("接受对局");
                    DelayUtil.delay(5000L);
                }

                DelayUtil.delay(1000L);
            }
            DelayUtil.delay(1000L);
        }

    }

    private void enterRoom() {

        if (isTeamMode()) {
            // 开启组队
            log.info("开启组队模式");
            // TODO 判断是否为队长
            if (isOwner()) {
                boolean room = createRoom();
                if (!room) {
                    return;
                }
            } else {
                waitingForInvite();
            }
        } else {
            log.info("开启单人模式");
        }
    }

    private boolean createRoom() {
        //TODO 创建房间
        log("创建房间");
        if (!clickPlayToRoomBtn()) {
            error("点击 play 按钮失败");
            return false;
        }
        if (!selectGameMode()) {
            error("选择游戏模式失败");
            return false;
        }
        if (!selectPlayPosition()) {
            error("选择位置失败");
            return false;
        }
        inviteTeamMate();
        clickFindGame();
        matchingGame();
        return true;
    }

    private void inviteTeamMate() {
        //TODO 邀请队友
        if (GlobalData.clientTeamMode == ClientModeEnum.TEAM && GlobalData.clientRuleMode == ClientModeEnum.TEAM_OWNER) {
            while (true) {
                //TODO 判断需要优化 以后加上判断右上角的人数
                List<Point> points = checkTeamMateIsShowed();
                if (points == null || points.size() == 0) {
                    log("队友齐了，可以寻找对局了");
                    return;
                } else {
                    log("邀请队友");
                    boolean invite = invite(points);
                    if (!invite) {

                    }
                    DelayUtil.delay(1000L);
                }
            }

        }
    }

    private void matchingGame() {
        // 匹配中 等待接受按钮

        GlobalData.matchingDelay.set(1000);

    }

    private void clickFindGame() {
        // 点击寻找对局
        DmPicUtil.click("寻找对局.bmp");
    }

    private List<Point> checkTeamMateIsShowed() {
        //TODO 需要优化 判断需要邀请几个 还剩几个没有邀请
        return DmPicUtil.findPositions("invite1.bmp", "invite2.bmp", "invite3.bmp", "invite4.bmp");
//        return Lists.newArrayList();
    }

    private boolean invite(List<Point> points) {
        for (Point point : points) {
            RobotUtil.delay(1000);
            // 点击邀请
            RobotUtil.click(point);
            RobotUtil.delay(1000);


            List<String> accountList = Lists.newArrayList("拿回忆下酒s", "滚出我的心ea", "假装狠辛福vd", "暖暖的掌心mn");
            int searchX = 0, searchY = 0, inputX = 0, inputY = 0;
            for (String account : accountList) {
//                if (inputX == 0) {
//
//                    boolean b = DmPicUtil.click("邀请队友输入框1.bmp", "邀请队友输入框.bmp");
//                    inputX = RobotUtil.x;
//                    inputY = RobotUtil.y;
//                    RobotUtil.threeClick();
//                    String x = "lolimg";
//                    if (!b) {
//                        error("邀请队友输入框");
//                    }
//                } else {
//                    RobotUtil.click(inputX, inputY);
//                }
//                RobotUtil.delay(1000);
//                RobotUtil.mouseMove(inputX, inputY);
//                RobotUtil.threeClick();
//
//                RobotUtil.sendTextToInput(account);
//                RobotUtil.delay(1000);
//                if (searchX == 0) {
//                    boolean b = DmPicUtil.click("搜索队友按钮高亮.bmp");
//                    searchX = RobotUtil.x;
//                    searchY = RobotUtil.y;
//                    if (!b) {
//                        error("搜索队友按钮找不到");
//                    }
//                } else {
//                    RobotUtil.click(searchX, searchY);
//                }
//                RobotUtil.delay(1000);
                RobotUtil.clickRelative(698, 101);
                RobotUtil.delay(1000);
                RobotUtil.threeClick();
                RobotUtil.sendTextToInput(account);
                RobotUtil.delay(1000);
                RobotUtil.clickRelative(786, 95);
                RobotUtil.delay(1000);
            }
            RobotUtil.clickRelative(644, 642);
            return true;
//            return DmPicUtil.click("发送邀请.bmp");
        }
        return false;
    }

    private boolean selectPlayPosition() {
        //TODO 选择游戏位置
        log("选择游戏位置");
        RobotUtil.clickRelative(487, 485);
        DelayUtil.delay(1000);
        RobotUtil.clickRelative(483, 580);
        DelayUtil.delay(1000);
        RobotUtil.clickRelative(530, 484);
        DelayUtil.delay(1000);
        RobotUtil.clickRelative(528, 580);
        DelayUtil.delay(1000);
//        boolean click = DmPicUtil.click(true, "选位置按钮.bmp", "选位置按钮1.bmp");
//        if (!click) {
//            return false;
//        }
//
//        RobotUtil.delay(1000);
//        RobotUtil.click(RobotUtil.x + 10, RobotUtil.y + 100);
//        RobotUtil.delay(1000);

        return true;

    }

    private boolean clickPlayToRoomBtn() {
        //TODO 点击 play 按钮
        log("点击 play 按钮");
        return DmPicUtil.click("play按钮.bmp", "play按钮1.bmp",
                "play按钮不可用.bmp");
    }

    private boolean selectGameMode() {
        //TODO 选择游戏模式
        log("选择游戏模式");
        RobotUtil.clickRelative(63, 98);
        DelayUtil.delay(1000);
        RobotUtil.clickRelative(125, 234);
        DelayUtil.delay(1000);
        RobotUtil.clickRelative(143, 543);
        DelayUtil.delay(1000);
        RobotUtil.clickRelative(546, 689);
        DelayUtil.delay(1000);

//        boolean click = DmPicUtil.click("灵活组排.bmp", "灵活组排1.bmp");
//        if (!click) {
//            log("找不到游戏模式");
//            boolean click1 = DmPicUtil.click("玩家对战.bmp");
//
//            boolean click2 = DmPicUtil.click("灵活组排.bmp", "灵活组排1.bmp");
//            if (click2) {
//                log("先点玩家对战 还是 找不到游戏模式");
//                return DmPicUtil.click("确认开房间.bmp");
//            }
//            boolean click4 = DmPicUtil.click("召唤师峡谷.bmp", "召唤师峡谷1.bmp");
//            boolean click5 = DmPicUtil.click("灵活组排.bmp", "灵活组排1.bmp");
//            if (click5) {
//                log("先点召唤师峡谷 还是 找不到游戏模式");
//                return DmPicUtil.click("确认开房间.bmp");
//            }
//        } else {
//            return DmPicUtil.click("确认开房间.bmp");
//        }
        return false;
    }


    private void waitingForInvite() {
        //TODO 等待邀请
        log("等待邀请ing");
        DmPicUtil.click("被邀请.bmp","被队友邀请按钮.bmp");
    }

    private boolean isTeamMode() {
        //TODO 判断是否组队模式
        boolean isTeamMode = true;
        if (isTeamMode) {
            GlobalData.clientTeamMode = ClientModeEnum.TEAM;
        }
        return isTeamMode;
    }

    private boolean isOwner() {
        //判断是否为队长
        return GlobalData.clientRuleMode.equals(ClientModeEnum.TEAM_OWNER);
    }

    private int getCurrentPage() {
        // 首页
        int page = 1;
        // 650 560
        boolean b = DmPicUtil.click("接受对局.bmp", "接受1.bmp");
        if (b) {
            log.info("找到对局");

            page = 6;
            GlobalData.clientCurrentPage.set(page);
            return page;
        }

        // 赞队友
        // 643 649 跳过赞队友
        // 结算页面
        // 547,690 点再来一局

        // 选英雄中
        List<Point> positions = DmPicUtil.findPositions("编辑符文.bmp",
                "闪现.bmp"
        );
        if (positions != null && positions.size() > 0) {
            // 选择英雄中
            page = 4;
            GlobalData.clientCurrentPage.set(page);
            return page;
        }


        boolean check = DmPicUtil.check("赞队友.bmp");
        if (check) {
            log("赞队友");
            RobotUtil.clickRelative(643, 649);
        }

        boolean checkRe = DmPicUtil.check("结算页面.bmp");
        if (checkRe) {
            log("结算页面 找再来一局");
            RobotUtil.clickRelative(547,690 );
            RobotUtil.delay(500);
            RobotUtil.doubleClickRelative(547,690 );
            RobotUtil.delay(500);
        }

        // 在房间里
        if (DmPicUtil.click("房间按钮.bmp",
                "房间按钮1.bmp",
                "房间按钮2.bmp"
        )) {


            // 在房间里 排队中
            if (DmPicUtil.check(
                    "秒退屏蔽按钮1.bmp", "秒退屏蔽按钮.bmp"
            )) {
                page = 5;
                GlobalData.clientCurrentPage.set(page);
                return page;
            }

            // 在房间里 排队中
            if (DmPicUtil.check(
                    "正在寻找对局.bmp",
                    "队列中.bmp"
            )) {
                // 匹配游戏中
                page = 3;
            } else {
                // 在房间中
                page = 2;
            }
        }

        GlobalData.clientCurrentPage.set(page);
        return page;
    }

    private void error(String info) {
        ErrorUtil.error(info);
    }

    private void log(String info) {
        log.info("开启{}，当前为{}模式，{}", GlobalData.clientTeamMode.getName(), GlobalData.clientRuleMode.getName(), info);
    }
}
