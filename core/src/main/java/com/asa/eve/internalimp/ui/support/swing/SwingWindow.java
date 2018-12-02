package com.asa.eve.internalimp.ui.support.swing;

import com.asa.eve.structure.app.ApplicationProperties;
import com.asa.eve.structure.app.action.Model;
import com.asa.eve.structure.ui.OutputPanel;
import com.asa.eve.structure.ui.Window;
import com.asa.eve.utils.ScreenUtils;
import com.asa.log.LoggerFactory;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Rectangle;

/**
 * @author andrew_asa
 * @date 2018/11/24.
 * 窗口
 */
public class SwingWindow implements Window {

    private JFrame frame;

    private JPanel contentPane;

    private SwingInputPanel inputPane;

    private SwingOutputPanel currentOutputPanel;

    private static final String WINDOW_TITLE = "EVE";

    private boolean isMoved;

    private Point prePoint;

    private Point endPoint;

    private Model model;

    @Override
    public void init(ApplicationProperties properties) {

        frame = new JFrame();
        // 去掉修饰之后不能够移动窗体，所以需要另外写移动时间
        frame.setUndecorated(true);
        // 鼠标移动事件
        setMousedListener();

        frame.setAlwaysOnTop(true);
        frame.setLocationByPlatform(true);
        frame.setBackground(Color.black);
        frame.setResizable(false);
        frame.setTitle(WINDOW_TITLE);
        frame.setType(java.awt.Window.Type.UTILITY);

        this.contentPane = new JPanel();
        BoxLayout boxLayout = new BoxLayout(contentPane, BoxLayout.Y_AXIS);
        this.contentPane.setLayout(boxLayout);
        contentPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.inputPane = new SwingInputPanel(this);
        this.contentPane.add(inputPane.getPanel());

        frame.getContentPane().add(contentPane);

        this.currentOutputPanel = new SwingOutputPanel(this);


        //cont.add(scrl);
        //JScrollPane pane = new JScrollPane(currentOutputPanel.getPanel());
        contentPane.add(currentOutputPanel.getComponent());
        model = Model.GLOBAL;
    }

    /**
     * 设置窗体可以进行移动
     */
    private void setMousedListener() {

        frame.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {

                // 鼠标释放了以后，是不能再拖拽的了
                isMoved = false;
                frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {

                isMoved = true;
                prePoint = new Point(e.getX(), e.getY());
                // 得到按下去的位置
                frame.setCursor(new Cursor(Cursor.MOVE_CURSOR));
            }
        });
        //拖动时当前的坐标减去鼠标按下去时的坐标，就是界面所要移动的向量。
        frame.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {

            @Override
            public void mouseDragged(java.awt.event.MouseEvent e) {

                if (isMoved) {
                    // 判断是否可以拖拽
                    endPoint = new Point(frame.getLocation().x + e.getX() - prePoint.x,
                                         frame.getLocation().y + e.getY() - prePoint.y);
                    frame.setLocation(endPoint);
                }
            }
        });
    }

    public void centerOnScreen() {

        Rectangle currentScreen = getCurrentScreen();
        double width = currentScreen.getWidth();
        int x = (int) (currentScreen.getX() + ((width - getWindowWidth()) / 2));
        int y = getY(currentScreen.getHeight());
        frame.setLocation(x, y);
    }

    private int getY(double screenHeight) {

        return (int) ((screenHeight - getWindowWidth()) / 2) + 130;
    }

    private int getWindowWidth() {

        return frame.getWidth();
    }

    private Rectangle getCurrentScreen() {

        PointerInfo pointerInfo = MouseInfo.getPointerInfo();
        Rectangle curRect = ScreenUtils.getScreenBoundsAt(pointerInfo.getLocation());
        return curRect;
    }

    @Override
    public void start() {

        show();
    }

    @Override
    public void stop() {

        frame.setVisible(false);
    }

    @Override
    public void show() {

        frame.setVisible(true);
        frame.pack();
        centerOnScreen();
    }

    @Override
    public boolean isShow() {

        return frame.isVisible();
    }

    @Override
    public void hide() {

        frame.setVisible(false);
    }

    @Override
    public void resize() {

        frame.pack();
    }

    @Override
    public OutputPanel getOutputPanel() {

        return currentOutputPanel;
    }

    @Override
    public Model getModel() {

        return model;
    }

    public JFrame getFrame() {

        return frame;
    }

    public void setModel(Model model) {

        LoggerFactory.getLogger().debug("app module change from {},to {}", this.model, model);
        this.model = model;
    }
}
