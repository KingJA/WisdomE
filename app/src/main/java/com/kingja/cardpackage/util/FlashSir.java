package com.kingja.cardpackage.util;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;

import com.kingja.flashlighthelper.FlashlightFactory;
import com.kingja.flashlighthelper.FlashlightService;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:TODO
 * Create Time:2017/9/8 16:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class FlashSir {
    private Map<String, Boolean[]> morses;
    private static final boolean DOT = true;
    private static final boolean DASH = false;
    private static volatile FlashSir flashSir;
    private Handler handler;
    private static final long T = 40;
    public static final long TIME_DOT = 1 * T;
    public static final long TIME_DASH = 2 * T;
    public static final long TIME_DOT_DASH = 1 * T;
    public static final long TIME_LETTER_LETTER = 2 * T;
    public static final long TIME_WORD_WORD = 7 * T;
    public static final String[] MORSE_CHAR = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B",
            "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z", "{", "}"};
    private FlashlightService flashlightService;

    private FlashSir() {
        initMorse();
    }

    private void initMorse() {
        morses = new HashMap<>();
        morses.put("0", new Boolean[]{DASH, DASH, DASH, DASH, DASH}); //0
        morses.put("1", new Boolean[]{DOT, DASH, DASH, DASH, DASH}); //1
        morses.put("2", new Boolean[]{DOT, DOT, DASH, DASH, DASH}); //2
        morses.put("3", new Boolean[]{DOT, DOT, DOT, DASH, DASH}); //3
        morses.put("4", new Boolean[]{DOT, DOT, DOT, DOT, DASH}); //4
        morses.put("5", new Boolean[]{DOT, DOT, DOT, DOT, DOT}); //5
        morses.put("6", new Boolean[]{DASH, DOT, DOT, DOT, DOT}); //6
        morses.put("7", new Boolean[]{DASH, DASH, DOT, DOT, DOT}); //7
        morses.put("8", new Boolean[]{DASH, DASH, DASH, DOT, DOT}); //8
        morses.put("9", new Boolean[]{DASH, DASH, DASH, DASH, DOT}); //9
        morses.put("A", new Boolean[]{DOT, DASH}); //A
        morses.put("B", new Boolean[]{DASH, DOT, DOT, DOT}); //B
        morses.put("C", new Boolean[]{DASH, DOT, DASH, DOT}); //C
        morses.put("D", new Boolean[]{DASH, DOT, DOT}); //D
        morses.put("E", new Boolean[]{DOT}); //E
        morses.put("F", new Boolean[]{DOT, DOT, DASH, DOT}); //F
        morses.put("G", new Boolean[]{DASH, DASH, DOT}); //G
        morses.put("H", new Boolean[]{DOT, DOT, DOT, DOT}); //H
        morses.put("I", new Boolean[]{DOT, DOT}); //I
        morses.put("J", new Boolean[]{DOT, DASH, DASH, DASH}); //J
        morses.put("K", new Boolean[]{DASH, DOT, DASH}); //K
        morses.put("L", new Boolean[]{DOT, DASH, DOT, DOT}); //L
        morses.put("M", new Boolean[]{DASH, DASH}); //M
        morses.put("N", new Boolean[]{DASH, DOT}); //N
        morses.put("O", new Boolean[]{DASH, DASH, DASH}); //O
        morses.put("P", new Boolean[]{DOT, DASH, DASH, DOT}); //P
        morses.put("Q", new Boolean[]{DASH, DASH, DOT, DASH}); //Q
        morses.put("R", new Boolean[]{DOT, DASH, DOT}); //R
        morses.put("S", new Boolean[]{DOT, DOT, DOT}); //S
        morses.put("T", new Boolean[]{DASH}); //T
        morses.put("U", new Boolean[]{DOT, DOT, DASH}); //U
        morses.put("V", new Boolean[]{DOT, DOT, DOT, DASH}); //V
        morses.put("W", new Boolean[]{DOT, DASH, DASH}); //W
        morses.put("X", new Boolean[]{DASH, DOT, DOT, DASH}); //X
        morses.put("Y", new Boolean[]{DASH, DOT, DASH, DASH}); //Y
        morses.put("Z", new Boolean[]{DASH, DASH, DOT, DOT}); //Z
        morses.put("{", new Boolean[]{DASH, DOT, DASH, DOT, DASH}); //{
        morses.put("}", new Boolean[]{DOT, DOT, DOT, DASH, DOT}); //}
    }

    public void createCamera(Context context, Handler handler) {
        this.handler = handler;
        flashlightService = FlashlightFactory.getFlashlight(context);

    }

    public static FlashSir getInstance() {
        if (flashSir == null) {
            synchronized (FlashSir.class) {
                if (flashSir == null) {
                    flashSir = new FlashSir();
                }
            }
        }
        return flashSir;
    }

    private void openFlashLight() {
        flashlightService.openFlashlight();
    }

    private void closeFlashLight() {
        flashlightService.closeFlashlight();
    }

    public void closeCamera() {
        flashlightService.releaseFlashlight();
    }

    private void sleep(long millions) {
        SystemClock.sleep(millions);
    }

    private void sleepDoit() {
        sleep(TIME_DOT);
    }

    private void sleepBetweenDoits() {
        sleep(TIME_DOT_DASH);
    }

    private void sleepDash() {
        sleep(TIME_DASH);
    }

    private void sleepBetweenLetters() {
        sleep(TIME_LETTER_LETTER);
    }

    private void sleepBetweenWords() {
        sleep(TIME_WORD_WORD);
    }

    public void sendDot() {
        openFlashLight();
        sleepDoit();
    }

    public void sendDash() {
        openFlashLight();
        sleepDash();
    }

    public void sendGapInDoits() {
        closeFlashLight();
        sleepBetweenDoits();
    }

    public void sendGapInLetters() {
        closeFlashLight();
        sleepBetweenLetters();
    }

    public void sendGapInWords() {
        closeFlashLight();
        sleepBetweenWords();
    }

    public void sendLetter(String character) {
        if (!morses.containsKey(character)) {
            throw new IllegalArgumentException("未识别字符");
        }
        Boolean[] actions = morses.get(character);
        for (int i = 0; i < actions.length; i++) {
            if (actions[i]) {
                sendDot();
            } else {
                sendDash();
            }
            if (i != actions.length - 1) {
                sendGapInDoits();
            } else {
                closeFlashLight();
            }
        }
    }

    public void sendWord(String morseStr) {
        for (int i = 0; i < morseStr.length(); i++) {
            String character = morseStr.charAt(i) + "";
            sendLetter(character);
            if (i != morseStr.length() - 1) {
                sendGapInLetters();
            } else {
                closeFlashLight();
            }
        }
    }

    public void sendWordTimes(final String moreStr, final int times, final long delayTime) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < times; i++) {
                    sendWord(moreStr);
                    if (i != times - 1) {
                        SystemClock.sleep(delayTime);
                    }
                }
                if (handler != null) {
                    handler.sendEmptyMessage(0);
                }
            }
        });
        thread.setPriority(10);
        thread.start();

    }


}
