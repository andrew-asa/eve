/**
 * MIT License
 * <p>
 * Copyright (c) 2017 zgqq
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.asa.eve.internalimp.ui.support.swing.font;

import com.asa.eve.exception.UIStartException;
import com.asa.log.LoggerFactory;
import com.asa.utils.StringUtils;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author andrew_asa
 * @date 2018/11/24.
 */
public class FontManager {

    private static final FontManager INSTANCE = new FontManager();

    private BuiltinFont currentFont;

    private BuiltinFont defaultFont;

    private final Map<String, BuiltinFont> availbleFonts = new HashMap<>();

    private FontManager() {

        loadFont();
    }

    private void loadFont() {

        registerFonts();
        listFont();
    }

    private void registerFonts() {

        final BuiltinFont.Builder zenhei = new BuiltinFont.Builder("eve-zenhei.ttc", "normal", "WenQuanYi Zen Hei");
        //final BuiltinFont.Builder comic = new BuiltinFont.Builder("comic.ttf", "comic", "Comic Sans MS");
        final List<BuiltinFont.Builder> fonts = Arrays.asList(zenhei);
        final GraphicsEnvironment ge =
                GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            for (BuiltinFont.Builder fontBuilder : fonts) {
                InputStream is = FontManager.class.getClassLoader().getResourceAsStream(fontBuilder.getPath());
                Font font = Font.createFont(Font.TRUETYPE_FONT, is);
                ge.registerFont(font);
                fontBuilder.font(font);
                availbleFonts.put(fontBuilder.getName(), fontBuilder.build());
            }
            defaultFont = availbleFonts.get("normal");
            currentFont = defaultFont;
        } catch (Exception e) {
            throw new UIStartException(StringUtils.messageFormat("error load builtin font"));
        }
    }

    private void listFont() {

        final String[] fonts =
                GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        for (int i = 0; i < fonts.length; i++) {
            LoggerFactory.getLogger().info("Found font :{}", fonts[i]);
        }
    }

    public String getCurrentFontName() {

        return currentFont.getRealName();
    }

    public static FontManager getInstance() {

        return INSTANCE;
    }
}
