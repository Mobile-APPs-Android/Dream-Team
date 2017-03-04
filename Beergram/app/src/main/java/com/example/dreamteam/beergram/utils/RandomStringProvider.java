package com.example.dreamteam.beergram.utils;

import java.util.Random;

import javax.inject.Inject;

public class RandomStringProvider implements IRandomStringProvider{

    private static final char[] symbols;
    private static final int STRING_LENGTH = 20;

    static {
        StringBuilder tmp = new StringBuilder();
        for (char ch = '0'; ch <= '9'; ++ch)
            tmp.append(ch);
        for (char ch = 'a'; ch <= 'z'; ++ch)
            tmp.append(ch);
        for (char ch = 'A'; ch <= 'Z'; ++ch)
            tmp.append(ch);
        symbols = tmp.toString().toCharArray();
    }

    private final Random random = new Random();

    private final char[] buf;

    @Inject
    public RandomStringProvider() {
        buf = new char[STRING_LENGTH];
    }

    public String nextString() {
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        return new String(buf);
    }
}
