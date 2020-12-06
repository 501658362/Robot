package top.chenyanjin.robot.lol.enums;

import lombok.Getter;

public enum ClientModeEnum {
    /**
     *
     */
    SOLO("单人模式", 1),
    TEAM("组队模式", 2),


    TEAM_OWNER("房主", 101),
    TEAM_MEMBER("队友", 102),

    UNKNOWN("未知", 102),
    ;


    @Getter
    private String name;
    @Getter
    private Integer mode;

    ClientModeEnum(String name, Integer mode) {
        this.name = name;
        this.mode = mode;
    }

    public static ClientModeEnum getByMode(Integer mode) {
        for (ClientModeEnum modeEnum : ClientModeEnum.values()) {
            if (mode.equals(modeEnum.getMode())) {
                return modeEnum;
            }
        }
        return null;
    }
}
