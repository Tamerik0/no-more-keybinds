package tamerlan.fabric.keyemulation;

import com.sun.jna.Native;
import com.sun.jna.win32.StdCallLibrary;

public class JNAKeyEmulator {
    public interface User32 extends StdCallLibrary {
        User32 INSTANCE = Native.load("user32", User32.class);
        void keybd_event(byte bVk, byte bScan, int dwFlags, int dwExtraInfo);
    }
    public static void key(byte keyCode, int action){
        User32.INSTANCE.keybd_event(keyCode, (byte) 0, action, 0);
    }
}