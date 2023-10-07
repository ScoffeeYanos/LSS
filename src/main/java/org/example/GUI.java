package org.example;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.desktop.SystemSleepEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends Thread implements ActionListener {
    byte debugLevel;
    boolean pause;
    Config config;
    Bot bot;
    JFrame mainFrame;
    JMenuItem safeExit;
    JMenuItem changeDebugLevel;
    JMenuItem reloadMissions;
    JMenuItem switchPause;
    JMenuItem printWache;
    JOptionPane optionPane;
    JPanel pausePanel;
    public static void main(String[] args){
        byte var = 2;
        GUI gui = new GUI(var);
    }
    public GUI(byte debugLevel){
        this.debugLevel = debugLevel;
        init();
    }
    public GUI(byte debugLevel, Bot bot, Config config,boolean pause){
        this.debugLevel = debugLevel;
        this.bot = bot;
        this.config = config;
        this.pause = pause;
        init();
    }
    public void init(){
        mainFrame = new JFrame("LSS_BOT");
        mainFrame.setSize(300,100);
        Border border = new LineBorder(Color.YELLOW);
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBorder(border);
        JMenu Menu = new JMenu("Menu");
        safeExit = new JMenuItem("SafeExit");
        safeExit.addActionListener(this);
        Menu.add(safeExit);
        changeDebugLevel = new JMenuItem("Change Debug Level");
        changeDebugLevel.addActionListener(this);
        Menu.add(changeDebugLevel);
        reloadMissions = new JMenuItem("Reload Missions");
        reloadMissions.addActionListener(this);
        Menu.add(reloadMissions);
        switchPause = new JMenuItem("Switch Pause");
        switchPause.addActionListener(this);
        Menu.add(switchPause);
        printWache = new JMenuItem("Print Wache");
        printWache.addActionListener(this);
        Menu.add(printWache);
        menuBar.add(Menu);
        mainFrame.setJMenuBar(menuBar);
        pausePanel = new JPanel();
        pausePanel.setBackground(Color.green);
        mainFrame.add(pausePanel);
        mainFrame.setVisible(true);
    }

    @Override
    public void run() {
    }
    public void safeExit(){
        System.out.println("SafeExit");
        System.out.println(debugLevel);
    }
    public void ChangeDebugLevel(){
        optionPane = new JOptionPane("Change Debug Level");
        optionPane.setSize(200,200);
        debugLevel = (byte) optionPane.showInputDialog(null,"Input new Debug Level","Change Debug Level",JOptionPane.QUESTION_MESSAGE,null,new Byte[]{0,1,2,3,4,5},""+ debugLevel);
        optionPane.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("EVENT");
        if(e.getSource() == safeExit){
            safeExit();
        }
        if(e.getSource() == changeDebugLevel){
            ChangeDebugLevel();
        }
        if(e.getSource() == optionPane){

        }
        if(e.getSource() == reloadMissions){
            try {
                config.switchPause();
                Util.sleep(300);
                if(pause){
                    pausePanel.setBackground(Color.red);
                    mainFrame.add(pausePanel);
                }else{
                    pausePanel.setBackground(Color.green);
                    mainFrame.add(pausePanel);
                }
                config.reloadMissions();
                Util.sleep(500);
                config.switchPause();
                Util.sleep(300);
                if(pause){
                    pausePanel.setBackground(Color.red);
                    mainFrame.add(pausePanel);
                }else{
                    pausePanel.setBackground(Color.green);
                    mainFrame.add(pausePanel);
                }
            } catch (UnknownMission ex) {
                System.out.println("Error while reloading");
            }
        }
        if(e.getSource() == switchPause){
            config.switchPause();
            Util.sleep(300);
            if(pause){
                pausePanel.setBackground(Color.red);
                mainFrame.add(pausePanel);
            }else{
                pausePanel.setBackground(Color.green);
                mainFrame.add(pausePanel);
            }
        }
        if(e.getSource()==printWache){
            config.wachen[0].print();
        }
    }
}
