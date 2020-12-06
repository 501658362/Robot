//package top.chenyanjin.robot.lol.service;
//
//import com.sun.jna.platform.win32.WinDef;
//import org.junit.Test;
//import top.chenyanjin.robot.lol.enums.WindowNameEnum;
//import top.chenyanjin.robot.lol.thread.GlobalData;
//import top.chenyanjin.robot.lol.thread.MatchingGame;
//import top.chenyanjin.robot.lol.thread.SelectHeroGame;
//import top.chenyanjin.robot.lol.util.DelayUtil;
//import top.chenyanjin.robot.lol.util.WinUtil;
//
//import static org.junit.Assert.*;
//
//public class ClientServiceTest {
//
//    @org.junit.Before
//    public void setUp() throws Exception {
//        for (WindowNameEnum value : WindowNameEnum.values()) {
//            WinDef.HWND game = WinUtil.findWindow(value);
//            if (game != null  ) {
//                GlobalData.mode.set(value.getMode());
//                GlobalData.hwnd = game;
//                break;
//            }
//        }
//    }
//
//    @org.junit.Test
//    public void run() {
////        ClientService clientService  = new ClientService();
////        clientService.run();
//
//    }
//
//    @Test
//    public void t() {
////        MatchingGame matchingGame = new MatchingGame();
////        matchingGame.start();
////        SelectHeroGame selectHeroGame = new SelectHeroGame();
////        selectHeroGame.start();
////        while (true){
////            DelayUtil.delay(3000L);
////        }
//    }
//}