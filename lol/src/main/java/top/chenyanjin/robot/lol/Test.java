package top.chenyanjin.robot.lol;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;
import lombok.extern.slf4j.Slf4j;

import java.awt.event.KeyEvent;

@Slf4j
public class Test implements HotkeyListener {
    public static void main(String[] args) {
        JIntellitype.getInstance().addHotKeyListener(new Test());
        JIntellitype.getInstance().registerHotKey(1, 0, KeyEvent.VK_F2);

    }

    /**
     * Event fired when a WM_HOTKEY message is received that was initiated
     * by this application.
     * <p>
     *
     * @param identifier the unique Identifer the Hotkey was assigned
     */
    @Override
    public void onHotKey(int identifier) {
        System.out.println(identifier);
    }

///*
//        *//**
//         * Invoked when a key has been typed.
//         * See the class description for {@link KeyEvent} for a definition of
//         * a key typed event.
//         *
//         * @param e
//         *//*
//        public void keyTyped(KeyEvent e) {
//            switch (e.getKeyCode()) {
//                case KeyEvent.VK_F2:
//                    System.out.println(1);
//                    break;
//            }
//        }
//
//        *//**
//         * Invoked when a key has been pressed.
//         * See the class description for {@link KeyEvent} for a definition of
//         * a key pressed event.
//         *
//         * @param e
//         *//*
//        public void keyPressed(KeyEvent e) {
//            switch (e.getKeyCode()) {
//                case KeyEvent.VK_F2:
//                    System.out.println(2);
//                    break;
//            }
//        }
//
//        *//**
//         * Invoked when a key has been released.
//         * See the class description for {@link KeyEvent} for a definition of
//         * a key released event.
//         *
//         * @param e
//         *//*
//        public void keyReleased(KeyEvent e) {
//            switch (e.getKeyCode()) {
//                case KeyEvent.VK_F2:
//                    System.out.println(3);
//                    break;
//            }
//        }*/
}
