package com.asa.eve.internalimp.ui.support.swing;


import com.asa.eve.constant.AppConstant;
import com.asa.eve.internalimp.ui.UIManager;
import com.asa.eve.internalimp.ui.support.swing.font.FontManager;
import com.asa.eve.structure.app.InputChangeDispatch;
import com.asa.eve.structure.app.InputKeyPressDispatch;
import com.asa.eve.structure.app.action.Model;
import com.asa.log.LoggerFactory;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Collections;

/**
 * @author andrew_asa
 * @date 2018/11/24.
 */
public class SwingInputPanel {

    private JTextComponent input;

    private JPanel panel;

    private SwingWindow window;

    private JPanel currentOutputPanel;

    private InputChangeDispatch inputChangeDispatch;

    private InputKeyPressDispatch inputKeyPressDispatch;

    public SwingInputPanel(SwingWindow window) {

        this.window = window;
        init();
    }

    private void init() {

        this.panel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxLayout);
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.panel.setPreferredSize(new Dimension(AppConstant.UI.DEFAULT_INPUT_PANEL_WIDTH, AppConstant.UI.DEFAULT_INPUT_PANEL_HEIGHT));
        //this.panel.setLayout(new GridBagLayout());
        this.panel.setOpaque(false);
        initInput();
        inputChangeDispatch = UIManager.getInstance().createDefaultInputChangeDispatch();
        inputKeyPressDispatch = UIManager.getInstance().createInputKeyPressDispatch();
    }

    private void initInput() {

        JTextField input = new JTextField();
        input.setPreferredSize(new Dimension((int) (AppConstant.UI.DEFAULT_INPUT_PANEL_WIDTH * 0.9), (int) (AppConstant.UI.DEFAULT_INPUT_PANEL_HEIGHT * 0.9)));
        input.setBorder(BorderFactory.createCompoundBorder(
                input.getBorder(),
                BorderFactory.createEmptyBorder(AppConstant.UI.DEFAULT_INPUT_PANEL_PADDING, AppConstant.UI.DEFAULT_INPUT_PANEL_PADDING, AppConstant.UI.DEFAULT_INPUT_PANEL_PADDING, AppConstant.UI.DEFAULT_INPUT_PANEL_PADDING)));
        input.setFont(new Font(FontManager.getInstance().getCurrentFontName(), Font.PLAIN, AppConstant.UI.DEFAULT_INPUT_PANEL_FONT_SIZE));
        //limitInputCharacterNum(input);
        //listenInputMethod(input);
        listenInput(input);
        //disposeKeybinds(input);

        //input.setPreferredSize(new Dimension((inputWidth), inputHeight));
        panel.setBackground(Color.decode("#333333"));
        input.setBackground(Color.decode("#616161"));
        input.setForeground(Color.decode("#ffffff"));
        input.setCaretColor(Color.decode("#ffffff"));

        this.input = input;
        this.panel.add(input);
    }

    public JPanel getPanel() {

        return panel;
    }

    private void listenInput(final JTextComponent input) {

        input.getDocument()
                .addDocumentListener(new DocumentListener() {

                    @Override
                    public void insertUpdate(DocumentEvent e) {

                        try {
                            fireTextChangedEvent(e.getDocument());
                        } catch (BadLocationException e1) {
                        }
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {

                        try {
                            fireTextChangedEvent(e.getDocument());
                        } catch (BadLocationException e1) {
                        }
                    }

                    private void fireTextChangedEvent(Document document) throws BadLocationException {

                        SwingUtilities.invokeLater(() -> {
                            try {
                                String text = document.getText(0,
                                                               document.getLength());
                                inputChangeDispatch.fireTextChangedEvent(text);
                            } catch (Exception e) {
                                LoggerFactory.getLogger().error(e.getMessage(), e);
                            }
                        });
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {

                    }
                });
        // 确保tab键的事件起作用
        input.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        input.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        input.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                inputKeyPressDispatch.keyPressed(e);
            }
        });
        input.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {

                window.setModel(Model.INPUT);
            }

            @Override
            public void focusLost(FocusEvent e) {

                window.setModel(Model.GLOBAL);
            }
        });
    }

    //private void listenInputMethod(final JTextComponent input) {
    //
    //    input.addInputMethodListener(new InputMethodListener() {
    //
    //        @Override
    //        public void inputMethodTextChanged(InputMethodEvent event) {
    //
    //            AttributedCharacterIterator text = event.getText();
    //            if (text != null) {
    //                int committedCharacterCount = event.getCommittedCharacterCount();
    //                char c = text.first();
    //                StringBuilder textBuffer = new StringBuilder();
    //                JPanel panel = new JPanel();
    //                BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
    //                panel.setLayout(boxLayout);
    //                while (committedCharacterCount-- > 0) {
    //                    textBuffer.append(c);
    //                    c = text.next();
    //                    JLabel label = new JLabel(new String(new char[]{c}));
    //                    panel.add(label);
    //                }
    //                SwingOutputPanel outputPanel = new SwingOutputPanel(window, panel);
    //                window.updateOutput(outputPanel);
    //            }
    //        }
    //
    //        @Override
    //        public void caretPositionChanged(InputMethodEvent event) {
    //
    //        }
    //
    //    });
    //}
}
