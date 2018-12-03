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
package com.asa.eve.utils;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author andrew_asa
 * @date 2018/11/24.
 */
public final class ImageUtils {

    private ImageUtils() {

    }

    /**
     * 图片变形
     *
     * @param inputStream
     * @param width
     * @param height
     * @return
     * @throws IOException
     */
    public static ImageIcon scale(InputStream inputStream, int width, int height) throws IOException {

        BufferedImage newIcon = ImageIO.read(inputStream);
        ImageIcon imageIcon = new ImageIcon(newIcon);
        // load the image to a imageIcon
        Image image = imageIcon.getImage();
        // transform it
        // scale it the smooth way
        Image newImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImage);
        // transform it back
        return imageIcon;
    }
}

