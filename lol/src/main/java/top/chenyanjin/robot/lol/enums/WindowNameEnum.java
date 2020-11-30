package top.chenyanjin.robot.lol.enums;

import lombok.Getter;

public enum WindowNameEnum {
    /**
     *
     */
    LOGIN("登录器", "TWINCONTROL", "英雄联盟登录程序", 3),
    CLIENT("客户端", "RCLIENT", "League of Legends", 2),
    GAME("对局中", "RiotWindowClass", "League of Legends (TM) Client", 1),
    ;


    @Getter
    private String name;
    @Getter
    private String className;
    @Getter
    private String titleName;
    @Getter
    private Integer mode;

    WindowNameEnum(String name, String className, String titleName, Integer mode) {
        this.name = name;
        this.className = className;
        this.titleName = titleName;
        this.mode = mode;
    }
}
