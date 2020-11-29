package top.chenyanjin.robot.lol;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LaunchApp {

    public static void launchClient(String path){
        try {
            Desktop.getDesktop().open(new File("C:\\Program Files (x86)\\WeGameApps\\英雄联盟\\TCLS\\Client.exe"));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
//            Runtime.getRuntime().exec("C:\\Program Files (x86)\\WeGameApps\\英雄联盟");
            //            Desktop.getDesktop().open(new File("C:/Program Files (x86)/WeGameApps/英雄联盟/TCLS/Client.exe"));
            Desktop.getDesktop().open(new File("C:\\Program Files (x86)\\WeGameApps\\英雄联盟\\TCLS\\Client.exe"));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

}
