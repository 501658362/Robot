package top.chenyanjin.robot.lol.util;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import top.chenyanjin.robot.lol.enums.WindowNameEnum;

public class WinUtil {
    private static final int MAX_TITLE_LENGTH = 1024;

    public static void active(String lpClassName, String lpWindowName) {
        WinDef.HWND hwnd = findWindow(lpClassName, lpWindowName);
        active(hwnd);
    }

    public static void active(WindowNameEnum windowNameEnum) {
        active(windowNameEnum.getClassName(), windowNameEnum.getTitleName());
    }

    public static void active(WinDef.HWND hwnd) {
        // SW_RESTORE
        User32.INSTANCE.ShowWindow(hwnd, 9);
        // bring to front
        User32.INSTANCE.SetForegroundWindow(hwnd);
    }

    public static WinDef.HWND findWindow(WindowNameEnum windowNameEnum) {
        return findWindow(windowNameEnum.getClassName(), windowNameEnum.getTitleName());
    }

    public static WinDef.HWND findWindow(String lpClassName, String lpWindowName) {
        return User32.INSTANCE.FindWindow
                (lpClassName, lpWindowName);
    }


    public static void showChildName(WinDef.HWND hwnd) {
        User32.INSTANCE.EnumChildWindows(hwnd, new WinUser.WNDENUMPROC() {
            public boolean callback(WinDef.HWND hwnd, Pointer arg1) {
                try {
                    char[] buffer = new char[MAX_TITLE_LENGTH * 2];
                    User32.INSTANCE.GetWindowText(hwnd, buffer, MAX_TITLE_LENGTH);
                    System.out.println(showWindowName(hwnd));
                    showChildName(hwnd);

                } catch (Exception var7) {
                    var7.printStackTrace();
                }

                return true;
            }
        }, Pointer.createConstant(1));
    }

    public static void showChild(WinDef.HWND hwnd, WinUser.WNDENUMPROC wndenumproc) {
        User32.INSTANCE.EnumChildWindows(hwnd, wndenumproc, Pointer.createConstant(1));
    }

    public static String showWindowName(WinDef.HWND hwnd) {
        char[] buffer = new char[MAX_TITLE_LENGTH * 2];
        User32.INSTANCE.GetWindowText(hwnd, buffer, MAX_TITLE_LENGTH);
        return new String(buffer);
    }
    public static WinUser.WINDOWPLACEMENT getWindowPlacement(WinDef.HWND hwnd) {
        WinUser.WINDOWPLACEMENT rect = new WinUser.WINDOWPLACEMENT();
        User32.INSTANCE.GetWindowPlacement(hwnd, rect);
        return rect;
    }
}
