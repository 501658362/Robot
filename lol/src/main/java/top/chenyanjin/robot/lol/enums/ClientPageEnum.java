package top.chenyanjin.robot.lol.enums;

import lombok.Getter;

public enum ClientPageEnum {
    /**
     *
     */
    INDEX("首页", 1),
    ROOM("房间", 2),
    ;


    @Getter
    private String name;
    @Getter
    private Integer mode;

    ClientPageEnum(String name, Integer mode) {
        this.name = name;
        this.mode = mode;
    }
}
