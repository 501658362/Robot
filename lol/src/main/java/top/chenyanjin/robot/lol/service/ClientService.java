package top.chenyanjin.robot.lol.service;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import top.chenyanjin.robot.lol.Main;
import top.chenyanjin.robot.lol.enums.ClientModeEnum;
import top.chenyanjin.robot.lol.thread.GlobalData;
import top.chenyanjin.robot.lol.util.*;

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

    @Override
    public void run() {
        while (!this.isInterrupted() && GlobalData.mode.get() == 2) {
            if (Main.selectHeroGame.isAlive()) {
                log.info("选英雄线程正在运行中");
                DelayUtil.delay(5000L);
                return;
            }
            int currentPage = getCurrentPage();

            // 首页
            if (currentPage == 1) {
                enterRoom();
            }
            // 房间页
            if (currentPage == 2) {
                log("在房间中");
                //TODO 检测是否需要选位置
                if (isOwner()) {
                    // 检测是否需要邀请
                    inviteTeamMate();
                    clickFindGame();
                    matchingGame();
                } else {
                    // 等待游戏开始
                    matchingGame();
                }
            }
            // 匹配游戏中
            if (currentPage == 3) {
                log("匹配游戏中");
                //TODO 等待接受游戏
                matchingGame();
            }
            // 匹配游戏中
            if (currentPage == 4) {
                log("选择英雄中");
                if (!Main.selectHeroGame.isAlive()) {
                    Main.selectHeroGame.start();
                }
            }
            // 秒退惩罚中
            if (currentPage == 5) {
                log("秒退惩罚中");
            }

            DelayUtil.delay(5000L);
        }

    }

    private void enterRoom() {

        if (isTeamMode()) {
            // 开启组队
            log.info("开启组队模式");
            // TODO 判断是否为队长
            if (isOwner()) {
                createRoom();
            } else {
                waitingForInvite();
            }
        } else {
            log.info("开启单人模式");
        }
    }

    private void createRoom() {
        //TODO 创建房间
        log("创建房间");
        if (!clickPlayToRoomBtn()) {
            error("点击 play 按钮失败");
            return;
        }
        if (!selectGameMode()) {
            error("选择游戏模式失败");
            return;
        }
        if (!selectPlayPosition()) {
            error("选择位置失败");
            return;
        }
        inviteTeamMate();
        clickFindGame();
        matchingGame();
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
        if (!Main.matchingGame.isAlive()) {
            Main.matchingGame.start();
        }

    }

    private void clickFindGame() {
        // 点击寻找对局
        ClickByImgUtil.clickOne("C:\\\\Users\\\\CHEN\\\\Desktop\\\\AutoHotKey\\\\dm\\\\lol\\\\寻找对局.bmp", "寻找对局");
    }

    private List<Point> checkTeamMateIsShowed() {
        //TODO 需要优化 判断需要邀请几个 还剩几个没有邀请
        return Lists.newArrayList();
//        return ImageUtil.find(Lists.newArrayList(
//                "C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\invite1.png",
//                "C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\invite2.bmp",
//                "C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\invite3.bmp",
//                "C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\invite4.bmp"
//        ));
    }

    private boolean invite(List<Point> points) {
        for (Point point : points) {
            RobotUtil.delay(1000);
            // 点击邀请
            RobotUtil.click(point);
            RobotUtil.delay(1000);


            List<String> accountList = Lists.newArrayList("账号1", "账号2", "账号3", "账号4");
            int searchX = 0, searchY = 0, inputX = 0, inputY = 0;
            for (String account : accountList) {
                if (inputX == 0) {
                    boolean b = ClickByImgUtil.clickOne(Lists.newArrayList("C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\邀请队友输入框.bmp"));
                    inputX = RobotUtil.x;
                    inputY = RobotUtil.y;
                    if (!b) {
                        error("邀请队友输入框");
                    }
                } else {
                    RobotUtil.click(inputX, inputY);
                }

                RobotUtil.doubleClick();

                RobotUtil.sendTextToInput(account);
                if (searchX == 0) {
                    boolean b = ClickByImgUtil.clickOne(Lists.newArrayList("C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\搜索队友按钮高亮.bmp"));
                    searchX = RobotUtil.x;
                    searchY = RobotUtil.y;
                    if (!b) {
                        error("搜索队友按钮找不到");
                    }
                } else {
                    RobotUtil.click(searchX, searchY);
                }
                RobotUtil.delay(1000);

            }
            return ClickByImgUtil.clickOne(Lists.newArrayList("C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\发送邀请.bmp"), "发送邀请");
        }
        return false;
    }

    private boolean selectPlayPosition() {
        //TODO 选择游戏位置
        log("选择游戏位置");
        List<Point> points = ImageUtil.findPoint(Lists.newArrayList("C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\选位置按钮.bmp"));
        if (points != null && points.size() > 0) {
            RobotUtil.delay(1000);
            RobotUtil.click(points.get(0));
            RobotUtil.delay(1000);
        } else {
            return false;
        }

        RobotUtil.delay(1000);
        RobotUtil.click(RobotUtil.x + 10, RobotUtil.y + 100);
        RobotUtil.delay(1000);
//        List<Point> points1 = ImageUtil.find(Lists.newArrayList("C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\补位.bmp"));
//        if (points1 != null && points1.size() > 0) {
//            RobotUtil.delay(1000);
//            RobotUtil.click(points1.get(0));
//            RobotUtil.delay(1000);
//            return true;
//        } else {
//            return false;
//        }
        return true;

    }

    private boolean clickPlayToRoomBtn() {
        //TODO 点击 play 按钮
        log("点击 play 按钮");
        List<Point> points = ImageUtil.findPoint(Lists.newArrayList("C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\play按钮.bmp",
                "C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\play按钮不可用.bmp"));
        if (points != null && points.size() > 0) {
            RobotUtil.delay(1000);
            RobotUtil.click(points.get(0));
            RobotUtil.delay(1000);
            return true;
        }
        return false;
    }

    private boolean selectGameMode() {
        //TODO 选择游戏模式
        log("选择游戏模式");
        List<Point> points = ImageUtil.findPoint(Lists.newArrayList("C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\玩家对战.bmp",
                "C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\灵活组排.bmp",
                "C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\确认开房间.bmp"
        ));
        if (points != null && points.size() == 3) {
            for (Point point : points) {
                RobotUtil.delay(1000);
                RobotUtil.click(point);
                RobotUtil.delay(1000);
            }
            return true;
        }
        return false;
    }


    private void waitingForInvite() {
        //TODO 等待邀请
        log("等待邀请ing");
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
        //TODO 判断是否为队长
        boolean isOwner = true;
        if (isOwner) {
            GlobalData.clientRuleMode = ClientModeEnum.TEAM_OWNER;
        }
        return true;
    }

    private int getCurrentPage() {
        int page = 1;
        // 选英雄中
        if (ImageUtil.find(Lists.newArrayList("C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\编辑符文.bmp",
                "C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\闪现.bmp",
                "C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\选择你的英雄1.bmp",
                "C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\禁用不可用.bmp",
                "C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\首选.bmp"
        ))) {
            page = 4;
            GlobalData.clientCurrentPage.set(page);
            return page;
        }

        // 在房间里
        if (ClickByImgUtil.clickOne(Lists.newArrayList("C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\房间按钮.bmp",
                "C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\房间按钮1.bmp",
                "C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\房间按钮2.bmp"
        ))) {


            // 在房间里 排队中
            if (ImageUtil.find(Lists.newArrayList(
                    "C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\秒退屏蔽按钮.bmp"
            ))) {
                page = 5;
                GlobalData.clientCurrentPage.set(page);
                return page;
            }

            // 在房间里 排队中
            if (ImageUtil.find(Lists.newArrayList(
                    "C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\正在寻找对局.bmp",
                    "C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\队列中.bmp"
            ))) {
                page = 3;
            } else {
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
