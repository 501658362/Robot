package top.chenyanjin.robot.lol;

import top.chenyanjin.robot.lol.thread.LoginGame;
import top.chenyanjin.robot.lol.thread.PlayGame;
import top.chenyanjin.robot.lol.thread.WindowDetection;

/**
 * @author CHEN
 */
public class Main {

    public static void main(String[] args) {
        Thread wd = new WindowDetection();
        Thread login = new LoginGame();
        Thread play = new PlayGame();
        wd.start();
        login.start();
        play.start();


    }
}
