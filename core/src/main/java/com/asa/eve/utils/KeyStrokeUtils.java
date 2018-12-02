package com.asa.eve.utils;

import com.asa.third.org.apache.commons.lang3.SystemUtils;
import com.asa.utils.StringUtils;

import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * @author andrew_asa
 * @date 2018/11/27.
 */
public class KeyStrokeUtils {

    private final static String SHIFT_MODIFIER_STRING = getKeyModifiersText(KeyEvent.SHIFT_MASK);

    private final static String CTRL_MODIFIER_STRING = getKeyModifiersText(KeyEvent.CTRL_MASK);

    private final static String ALT_MODIFIER_STRING = getKeyModifiersText(KeyEvent.ALT_MASK);

    private final static String META_MODIFIER_STRING = getKeyModifiersText(KeyEvent.META_MASK);

    private final static String CONNECTION_SYMBOL = "+";

    public static String getKeyStrokeRepresentation(KeyStroke ks) {

        return ks.toString().replaceFirst("(released )|(pressed )|(typed )", "");
    }

    /**
     * 获取ks显示名
     *
     * @param ks
     * @return
     */
    public static String getKeyStrokeDisplayableRepresentation(KeyStroke ks) {

        if (ks == null) {
            return null;
        }

        int modifiers = ks.getModifiers();
        String keyText = getKeyText(ks.getKeyCode());
        if (modifiers != 0) {
            return getModifiersDisplayableRepresentation(modifiers) + CONNECTION_SYMBOL + keyText;
        }
        return keyText;
    }

    /**
     * 获取辅助建显示
     *
     * @param modifiers
     * @return
     */
    public static String getModifiersDisplayableRepresentation(int modifiers) {

        String modifiersString = StringUtils.EMPTY;

        if ((modifiers & KeyEvent.SHIFT_MASK) != 0) {
            modifiersString += SHIFT_MODIFIER_STRING;
        }
        if ((modifiers & KeyEvent.CTRL_MASK) != 0) {
            modifiersString += (modifiersString.equals(StringUtils.EMPTY) ? StringUtils.EMPTY : CONNECTION_SYMBOL) + CTRL_MODIFIER_STRING;
        }
        if (SystemUtils.IS_OS_MAC) {
            if ((modifiers & KeyEvent.ALT_MASK) != 0) {
                modifiersString += (modifiersString.equals(StringUtils.EMPTY) ? StringUtils.EMPTY : CONNECTION_SYMBOL) + ALT_MODIFIER_STRING;
            }
            if ((modifiers & KeyEvent.META_MASK) != 0) {
                modifiersString += (modifiersString.equals(StringUtils.EMPTY) ? StringUtils.EMPTY : CONNECTION_SYMBOL) + META_MODIFIER_STRING;
            }
        } else {
            if ((modifiers & KeyEvent.META_MASK) != 0) {

                modifiersString += (modifiersString.equals(StringUtils.EMPTY) ? StringUtils.EMPTY : CONNECTION_SYMBOL) + META_MODIFIER_STRING;
            }
            if ((modifiers & KeyEvent.ALT_MASK) != 0) {
                modifiersString += (modifiersString.equals(StringUtils.EMPTY) ? StringUtils.EMPTY : CONNECTION_SYMBOL) + ALT_MODIFIER_STRING;
            }
        }
        return modifiersString;
    }

    private static String getKeyModifiersText(int modifiers) {

        StringBuilder buf = new StringBuilder();
        if ((modifiers & InputEvent.META_MASK) != 0) {
            buf.append("Meta");
            buf.append(CONNECTION_SYMBOL);
        }
        if ((modifiers & InputEvent.CTRL_MASK) != 0) {
            buf.append("Ctrl");
            buf.append(CONNECTION_SYMBOL);
        }
        if ((modifiers & InputEvent.ALT_MASK) != 0) {
            buf.append("Alt");
            buf.append(CONNECTION_SYMBOL);
        }
        if ((modifiers & InputEvent.SHIFT_MASK) != 0) {
            buf.append("Shift");
            buf.append(CONNECTION_SYMBOL);
        }
        if ((modifiers & InputEvent.ALT_GRAPH_MASK) != 0) {
            buf.append("Alt Graph");
            buf.append(CONNECTION_SYMBOL);
        }
        if ((modifiers & InputEvent.BUTTON1_MASK) != 0) {
            buf.append("Button1");
            buf.append(CONNECTION_SYMBOL);
        }
        if (buf.length() > 0) {
            buf.setLength(buf.length() - 1);
        }
        return buf.toString();
    }

    public static String getKeyText(int keyCode) {

        if (keyCode >= KeyEvent.VK_0 && keyCode <= KeyEvent.VK_9 ||
                keyCode >= KeyEvent.VK_A && keyCode <= KeyEvent.VK_Z) {
            return String.valueOf((char) keyCode);
        }

        switch (keyCode) {
            case KeyEvent.VK_ENTER:
                return "Enter";
            case KeyEvent.VK_BACK_SPACE:
                return "Backspace";
            case KeyEvent.VK_TAB:
                return "Tab";
            case KeyEvent.VK_CANCEL:
                return "Cancel";
            case KeyEvent.VK_CLEAR:
                return "Clear";
            case KeyEvent.VK_COMPOSE:
                return "Compose";
            case KeyEvent.VK_PAUSE:
                return "Pause";
            case KeyEvent.VK_CAPS_LOCK:
                return "Caps Lock";
            case KeyEvent.VK_ESCAPE:
                return "Escape";
            case KeyEvent.VK_SPACE:
                return "Space";
            case KeyEvent.VK_PAGE_UP:
                return "Page Up";
            case KeyEvent.VK_PAGE_DOWN:
                return "Page Down";
            case KeyEvent.VK_END:
                return "End";
            case KeyEvent.VK_HOME:
                return "Home";
            case KeyEvent.VK_LEFT:
                return "Left";
            case KeyEvent.VK_UP:
                return "Up";
            case KeyEvent.VK_RIGHT:
                return "Right";
            case KeyEvent.VK_DOWN:
                return "Down";
            case KeyEvent.VK_BEGIN:
                return "Begin";

            // modifiers
            case KeyEvent.VK_SHIFT:
                return "Shift";
            case KeyEvent.VK_CONTROL:
                return "Control";
            case KeyEvent.VK_ALT:
                return "Alt";
            case KeyEvent.VK_META:
                return "Meta";
            case KeyEvent.VK_ALT_GRAPH:
                return "Alt Graph";

            // punctuation
            case KeyEvent.VK_COMMA:
                return "Comma";
            case KeyEvent.VK_PERIOD:
                return "Period";
            case KeyEvent.VK_SLASH:
                return "Slash";
            case KeyEvent.VK_SEMICOLON:
                return "Semicolon";
            case KeyEvent.VK_EQUALS:
                return "Equals";
            case KeyEvent.VK_OPEN_BRACKET:
                return "Open Bracket";
            case KeyEvent.VK_BACK_SLASH:
                return "Back Slash";
            case KeyEvent.VK_CLOSE_BRACKET:
                return "Close Bracket";

            // numpad numeric keys handled below
            case KeyEvent.VK_MULTIPLY:
                return "NumPad *";
            case KeyEvent.VK_ADD:
                return "NumPad +";
            case KeyEvent.VK_SEPARATOR:
                return "NumPad ,";
            case KeyEvent.VK_SUBTRACT:
                return "NumPad -";
            case KeyEvent.VK_DECIMAL:
                return "NumPad .";
            case KeyEvent.VK_DIVIDE:
                return "NumPad /";
            case KeyEvent.VK_DELETE:
                return "Delete";
            case KeyEvent.VK_NUM_LOCK:
                return "Num Lock";
            case KeyEvent.VK_SCROLL_LOCK:
                return "Scroll Lock";

            case KeyEvent.VK_WINDOWS:
                return "Windows";
            case KeyEvent.VK_CONTEXT_MENU:
                return "Context Menu";

            case KeyEvent.VK_F1:
                return "F1";
            case KeyEvent.VK_F2:
                return "F2";
            case KeyEvent.VK_F3:
                return "F3";
            case KeyEvent.VK_F4:
                return "F4";
            case KeyEvent.VK_F5:
                return "F5";
            case KeyEvent.VK_F6:
                return "F6";
            case KeyEvent.VK_F7:
                return "F7";
            case KeyEvent.VK_F8:
                return "F8";
            case KeyEvent.VK_F9:
                return "F9";
            case KeyEvent.VK_F10:
                return "F10";
            case KeyEvent.VK_F11:
                return "F11";
            case KeyEvent.VK_F12:
                return "F12";
            case KeyEvent.VK_F13:
                return "F13";
            case KeyEvent.VK_F14:
                return "F14";
            case KeyEvent.VK_F15:
                return "F15";
            case KeyEvent.VK_F16:
                return "F16";
            case KeyEvent.VK_F17:
                return "F17";
            case KeyEvent.VK_F18:
                return "F18";
            case KeyEvent.VK_F19:
                return "F19";
            case KeyEvent.VK_F20:
                return "F20";
            case KeyEvent.VK_F21:
                return "F21";
            case KeyEvent.VK_F22:
                return "F22";
            case KeyEvent.VK_F23:
                return "F23";
            case KeyEvent.VK_F24:
                return "F24";

            case KeyEvent.VK_PRINTSCREEN:
                return "Print Screen";
            case KeyEvent.VK_INSERT:
                return "Insert";
            case KeyEvent.VK_HELP:
                return "Help";
            case KeyEvent.VK_BACK_QUOTE:
                return "Back Quote";
            case KeyEvent.VK_QUOTE:
                return "Quote";

            case KeyEvent.VK_KP_UP:
                return "Up";
            case KeyEvent.VK_KP_DOWN:
                return "Down";
            case KeyEvent.VK_KP_LEFT:
                return "Left";
            case KeyEvent.VK_KP_RIGHT:
                return "Right";

            case KeyEvent.VK_DEAD_GRAVE:
                return "Dead Grave";
            case KeyEvent.VK_DEAD_ACUTE:
                return "Dead Acute";
            case KeyEvent.VK_DEAD_CIRCUMFLEX:
                return "Dead Circumflex";
            case KeyEvent.VK_DEAD_TILDE:
                return "Dead Tilde";
            case KeyEvent.VK_DEAD_MACRON:
                return "Dead Macron";
            case KeyEvent.VK_DEAD_BREVE:
                return "Dead Breve";
            case KeyEvent.VK_DEAD_ABOVEDOT:
                return "Dead Above Dot";
            case KeyEvent.VK_DEAD_DIAERESIS:
                return "Dead Diaeresis";
            case KeyEvent.VK_DEAD_ABOVERING:
                return "Dead Above Ring";
            case KeyEvent.VK_DEAD_DOUBLEACUTE:
                return "Dead Double Acute";
            case KeyEvent.VK_DEAD_CARON:
                return "Dead Caron";
            case KeyEvent.VK_DEAD_CEDILLA:
                return "Dead Cedilla";
            case KeyEvent.VK_DEAD_OGONEK:
                return "Dead Ogonek";
            case KeyEvent.VK_DEAD_IOTA:
                return "Dead Iota";
            case KeyEvent.VK_DEAD_VOICED_SOUND:
                return "Dead Voiced Sound";
            case KeyEvent.VK_DEAD_SEMIVOICED_SOUND:
                return "Dead Semivoiced Sound";

            case KeyEvent.VK_AMPERSAND:
                return "Ampersand";
            case KeyEvent.VK_ASTERISK:
                return "Asterisk";
            case KeyEvent.VK_QUOTEDBL:
                return "Double Quote";
            case KeyEvent.VK_LESS:
                return "Less";
            case KeyEvent.VK_GREATER:
                return "Greater";
            case KeyEvent.VK_BRACELEFT:
                return "Left Brace";
            case KeyEvent.VK_BRACERIGHT:
                return "Right Brace";
            case KeyEvent.VK_AT:
                return "At";
            case KeyEvent.VK_COLON:
                return "Colon";
            case KeyEvent.VK_CIRCUMFLEX:
                return "Circumflex";
            case KeyEvent.VK_DOLLAR:
                return "Dollar";
            case KeyEvent.VK_EURO_SIGN:
                return "Euro";
            case KeyEvent.VK_EXCLAMATION_MARK:
                return "Exclamation Mark";
            case KeyEvent.VK_INVERTED_EXCLAMATION_MARK:
                return "Inverted Exclamation Mark";
            case KeyEvent.VK_LEFT_PARENTHESIS:
                return "Left Parenthesis";
            case KeyEvent.VK_NUMBER_SIGN:
                return "Number Sign";
            case KeyEvent.VK_MINUS:
                return "Minus";
            case KeyEvent.VK_PLUS:
                return "Plus";
            case KeyEvent.VK_RIGHT_PARENTHESIS:
                return "Right Parenthesis";
            case KeyEvent.VK_UNDERSCORE:
                return "Underscore";

            case KeyEvent.VK_FINAL:
                return "Final";
            case KeyEvent.VK_CONVERT:
                return "Convert";
            case KeyEvent.VK_NONCONVERT:
                return "No Convert";
            case KeyEvent.VK_ACCEPT:
                return "Accept";
            case KeyEvent.VK_MODECHANGE:
                return "Mode Change";
            case KeyEvent.VK_KANA:
                return "Kana";
            case KeyEvent.VK_KANJI:
                return "Kanji";
            case KeyEvent.VK_ALPHANUMERIC:
                return "Alphanumeric";
            case KeyEvent.VK_KATAKANA:
                return "Katakana";
            case KeyEvent.VK_HIRAGANA:
                return "Hiragana";
            case KeyEvent.VK_FULL_WIDTH:
                return "Full-Width";
            case KeyEvent.VK_HALF_WIDTH:
                return "Half-Width";
            case KeyEvent.VK_ROMAN_CHARACTERS:
                return "Roman Characters";
            case KeyEvent.VK_ALL_CANDIDATES:
                return "All Candidates";
            case KeyEvent.VK_PREVIOUS_CANDIDATE:
                return "Previous Candidate";
            case KeyEvent.VK_CODE_INPUT:
                return "Code Input";
            case KeyEvent.VK_JAPANESE_KATAKANA:
                return "Japanese Katakana";
            case KeyEvent.VK_JAPANESE_HIRAGANA:
                return "Japanese Hiragana";
            case KeyEvent.VK_JAPANESE_ROMAN:
                return "Japanese Roman";
            case KeyEvent.VK_KANA_LOCK:
                return "Kana Lock";
            case KeyEvent.VK_INPUT_METHOD_ON_OFF:
                return "Input Method On/Off";

            case KeyEvent.VK_AGAIN:
                return "Again";
            case KeyEvent.VK_UNDO:
                return "Undo";
            case KeyEvent.VK_COPY:
                return "Copy";
            case KeyEvent.VK_PASTE:
                return "Paste";
            case KeyEvent.VK_CUT:
                return "Cut";
            case KeyEvent.VK_FIND:
                return "Find";
            case KeyEvent.VK_PROPS:
                return "Props";
            case KeyEvent.VK_STOP:
                return "Stop";
        }

        if (keyCode >= KeyEvent.VK_NUMPAD0 && keyCode <= KeyEvent.VK_NUMPAD9) {
            String numpad = "NumPad";
            char c = (char) (keyCode - KeyEvent.VK_NUMPAD0 + '0');
            return numpad + "-" + c;
        }

        if ((keyCode & 0x01000000) != 0) {
            return String.valueOf((char) (keyCode ^ 0x01000000));
        }
        String unknown = "Unknown";
        return unknown + " keyCode: 0x" + Integer.toString(keyCode, 16);
    }
}
