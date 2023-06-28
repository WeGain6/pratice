package test;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @ClassName: TestBtn
 * @Description:
 * @Author: XuWei
 * @Date: 2022-10-30 20:21
 */
public class TestBtn {

    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;


    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        JButton btn1 = new JButton("上");
        JButton btn2 = new JButton("下");
        JButton btn3 = new JButton("左");
        JButton btn4 = new JButton("右");

        frame.add(panel);

        panel.add(btn1);
        panel.add(btn2);
        panel.add(btn3);
        panel.add(btn4);

        btn1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                move(UP);
            }
        });

        btn2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                move(DOWN);
            }
        });

        btn3.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                move(LEFT);
            }
        });

        btn4.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                move(RIGHT);
            }
        });


    }



    private static void move(int flag){
        switch (flag){
            case UP:
                System.out.println("向上移动了一格");
                break;
            case DOWN:
                System.out.println("向下移动了一格");
                break;
            case LEFT:
                System.out.println("向左移动了一格");
                break;
            case RIGHT:
                System.out.println("向右移动了一格");
                break;
            default:
                System.out.println("请进行正常操作！");
                break;
        }
    }
}
