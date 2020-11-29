package top.chenyanjin.robot.lol.constant;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public enum WindowNameEnum {
    /**
     *
     */
    ShellTabWindowClass("ShellTabWindowClass", "英雄联盟"),
    LOGIN("TWINCONTROL", "英雄联盟登录程序"),
    CLIENT("RCLIENT", "League of Legends")
    ;


    @Getter
    private String className;
    @Getter
    private String titleName;

    WindowNameEnum(String className, String titleName) {
        this.className = className;
        this.titleName = titleName;
    }
}
