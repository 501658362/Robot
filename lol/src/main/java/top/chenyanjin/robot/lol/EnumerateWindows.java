//package top.chenyanjin.robot.lol;
//
//
//import com.sun.jna.Native;
//import com.sun.jna.Pointer;
////import com.sun.jna.platform.win32.WinDef.HWND;
//import com.sun.jna.platform.win32.WinDef;
//import com.sun.jna.ptr.PointerByReference;
//import org.xvolks.jnative.misc.basicStructures.HWND;
//import org.xvolks.jnative.misc.basicStructures.LPARAM;
//import org.xvolks.jnative.misc.basicStructures.UINT;
//import org.xvolks.jnative.misc.basicStructures.WPARAM;
//import org.xvolks.jnative.util.User32;
//
//import static top.chenyanjin.robot.lol.EnumerateWindows.Kernel32.*;
//import static top.chenyanjin.robot.lol.EnumerateWindows.Psapi.GetModuleBaseNameW;
//import static top.chenyanjin.robot.lol.EnumerateWindows.User32DLL.*;
//
//public class EnumerateWindows {
//
//    private static final int MAX_TITLE_LENGTH = 1024;
//
//    public static void main(String[] args) throws Exception {
//        // https://docs.microsoft.com/en-us/windows/win32/api/_winmsg/
////        HWND hWnd= User32.FindWindow("TXGuiFoundation", "QQ2010");
////名称	PID	状态	用户名	CPU	内存(专用工作集)	描述
////idea64.exe	14604	正在运行	CHEN	00 	2,039,412 K	IntelliJ IDEA
////        if(hWnd.getValue()>0){
////
////            System.out.println("窗口存在");
////
////            User32.SendMessage(hWnd, new UINT(0x10), new WPARAM(0), new LPARAM(0));
////
////        }else{
////
////            System.out.println("窗口不存在");
////
////        }
//        WinDef.LONG hwnd1 = FindWindowA("RCLIENT", "League of Legends");
//        WinDef.LONG aLong = SetActiveWindow(hwnd1);
//        WinDef.LONG aLong1 = ShowWindowAsync(hwnd1, new WinDef.LONG(5));
//        System.out.println(hwnd1 + " " + aLong + " " + aLong1 );
////        WinDef.LONG hwnd1 = FindWindowA("BandizipClass", "JNative_1.4RC3_bin.zip - Bandizip 6.24");
//////        WinDef.LONG hwnd1 = FindWindowA("AutoHotKey", "CabinetWClass");
////        for (int i = 0; i < 12; i++) {
////
////            System.out.println(i);
////            WinDef.LONG aLong = ShowWindowAsync(hwnd1, new WinDef.LONG(i));
////
////            System.out.println(aLong.intValue());
////            Thread.sleep(2000);
////        }
//
//        char[] buffer = new char[MAX_TITLE_LENGTH * 2];
////        WinDef.LONG aLong = GetActiveWindow();
//
////        WinDef.HWND hwnd = GetForegroundWindow();
////        GetWindowTextW(GetForegroundWindow(), buffer, MAX_TITLE_LENGTH);
////        System.out.println("Active window title: " + Native.toString(buffer));
////        char[] buffer1 = new char[MAX_TITLE_LENGTH * 2];
////        GetWindowTextW(GetDesktopWindow(), buffer1, MAX_TITLE_LENGTH);
////        System.out.println("Active window title: " + Native.toString(buffer1));
//
////        PointerByReference pointer = new PointerByReference();
////        GetWindowThreadProcessId(GetForegroundWindow(), pointer);
////        Pointer process = OpenProcess(PROCESS_QUERY_INFORMATION | PROCESS_VM_READ, false, pointer.getValue());
////        GetModuleBaseNameW(process, null, buffer, MAX_TITLE_LENGTH);
////        System.out.println("Active window process: " + Native.toString(buffer));
//    }
//
//    static class Psapi {
//        static { Native.register("psapi"); }
//        public static native int GetModuleBaseNameW(Pointer hProcess, Pointer hmodule, char[] lpBaseName, int size);
//    }
//
//    static class Kernel32 {
//        static { Native.register("kernel32"); }
//        public static int PROCESS_QUERY_INFORMATION = 0x0400;
//        public static int PROCESS_VM_READ = 0x0010;
//        public static native int GetLastError();
//        public static native long GetCurrentProcess();
//        public static native Pointer OpenProcess(int dwDesiredAccess, boolean bInheritHandle, Pointer pointer);
//    }
//
//    static class User32DLL {
//        static { Native.register("user32"); }
//        public static native int GetWindowThreadProcessId(WinDef.HWND hWnd, PointerByReference pref);
//        public static native WinDef.HWND GetForegroundWindow();
//        public static native WinDef.HWND GetDesktopWindow();
//        public static native WinDef.LONG GetActiveWindow();
//        public static native WinDef.LONG FindWindowA(String lpClassName, String lpWindowName);
//        public static native WinDef.LONG SetActiveWindow(WinDef.LONG id);
//        public static native WinDef.LONG ShowWindowAsync(WinDef.LONG id, WinDef.LONG tpe);
//        public static native int GetWindowTextW(WinDef.HWND hWnd, char[] lpString, int nMaxCount);
//    }
//}
