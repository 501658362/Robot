package top.chenyanjin.robot.lol;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import top.chenyanjin.robot.lol.enums.WindowNameEnum;
import top.chenyanjin.robot.lol.util.WinUtil;

import java.awt.*;

public class JNATest {
    private static final int MAX_TITLE_LENGTH = 1024;

    public static void main(String[] args) {


        // 第一个参数是Windows窗体的窗体类，第二个参数是窗体的标题。不熟悉windows编程的需要先找一些Windows窗体数据结构的知识来看看，还有windows消息循环处理，其他的东西不用看太多。
        WinDef.HWND hwnd = WinUtil.findWindow(WindowNameEnum.LOGIN);

        WinUser.WINDOWPLACEMENT windowplacement = WinUtil.getWindowPlacement(hwnd);
        Rectangle rectangle = windowplacement.rcNormalPosition.toRectangle();
        System.out.println(rectangle);
//        final WinUser.WNDENUMPROC wndenumproc1 = new WinUser.WNDENUMPROC() {
//            public boolean callback(WinDef.HWND hWnd, Pointer data) {
//                Rectangle rectangle = WinUtil.getWindowPlacement(hWnd).rcNormalPosition.toRectangle();
//
//                System.out.println(WinUtil.showWindowName(hWnd) + rectangle);
//                WinUtil.showChild(hWnd, this);
//                return true;
//            }
//        };
//        WinUser.WNDENUMPROC wndenumproc2 = new WinUser.WNDENUMPROC() {
//            public boolean callback(WinDef.HWND hWnd, Pointer data) {
//                Rectangle rectangle = WinUtil.getWindowPlacement(hWnd).rcNormalPosition.toRectangle();
//                System.out.println(rectangle);
//                WinUtil.showChild(hWnd, wndenumproc1);
//                return true;
//            }
//        };
//        WinUtil.showChild(hwnd, wndenumproc2);
        if (hwnd == null) {
            System.out.println("lol is not running");
        } else {
            System.out.println("lol is running");

            // SW_RESTORE
            User32.INSTANCE.ShowWindow(hwnd, 9);
            // bring to front
            User32.INSTANCE.SetForegroundWindow(hwnd);
            //获取现在前台窗口
            //User32.INSTANCE.GetForegroundWindow()
//            WinDef.RECT qqwin_rect = new  WinDef.RECT();
//            User32.INSTANCE.GetWindowRect(hwnd, qqwin_rect);
//            int qqwin_width = qqwin_rect.right-qqwin_rect.left;
//            int qqwin_height = qqwin_rect.bottom-qqwin_rect.top;
//
//            User32.INSTANCE.MoveWindow(hwnd, 700, 100, qqwin_width, qqwin_height, true);
//            for(int i = 700; i > 100; i -=10) {
//                User32.INSTANCE.MoveWindow(hwnd, i, 100, qqwin_width, qqwin_height, true);   // bring to front
//                try {
//                    Thread.sleep(80);
//                }catch(Exception e){}
//            }
        }
    }
}
