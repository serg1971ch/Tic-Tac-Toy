package game.view;

import game.model.Field;
import game.model.Game;
import game.model.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {
    private static final int SIZE = 3;
    private JButton jButtonRestart;
    private JButton[][] buttons;
    private InfoGrid gridInfo;
    private JButton jButton;
    private JPanel jPanel;
    Game game;

    public JButton[][] getButtons() {
        return buttons;
    }

    public GameWindow(Game game) {
        this.game = game;
        JPanel jPanel = new JPanel();
        JButton jButtonRestart = new JButton("RESTART");
        jButtonRestart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int action = JOptionPane.showConfirmDialog(GameWindow.this,
                        "Вы хотите перезапустить игру?", "Exit from XO", JOptionPane.OK_CANCEL_OPTION);
                if (action != JOptionPane.OK_CANCEL_OPTION) {
                    end();
                    Game.stopGame();
                    Game newGame = new Game();
                    newGame.start();
                }
            }
        });
        gridInfo = new InfoGrid(this);
        add(jPanel, BorderLayout.CENTER);
        add(gridInfo, BorderLayout.SOUTH);
        add(jButtonRestart, BorderLayout.NORTH);
        setSize(400, 400);
        setJMenuBar(createMenuBar());
        setTitle("XO Game");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jPanel.setLayout(new GridLayout(SIZE, SIZE));
        buttons = new JButton[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                jButton = new JButton(" . ");
                buttons[i][j] = jButton;
                jPanel.add(jButton);
            }
        }
        setVisible(true);
    }

    public JButton getjButton() {
        return jButton;
    }

    public JPanel getjPanel() {
        return jPanel;
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        menuBar.add(file);
        JMenuItem restartGame = new JMenuItem("Restart");
        JMenuItem exitMenu = new JMenuItem("Exit");
        file.add(restartGame);
        file.add(exitMenu);
        exitMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int action = JOptionPane.showConfirmDialog(GameWindow.this,
                        "Вы хотите выйти из приложения?", "Exit from XO", JOptionPane.OK_CANCEL_OPTION);
                if (action != JOptionPane.OK_CANCEL_OPTION) {
                    System.exit(0);
                }
            }
        });
        restartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int action = JOptionPane.showConfirmDialog(GameWindow.this,
                        "Вы хотите перезапустить игру?", "Exit from XO", JOptionPane.OK_CANCEL_OPTION);
                if (action != JOptionPane.OK_CANCEL_OPTION) {
                    restartPaintField();
                    game.restartGame();
//                    нужно перересовать поле
//                    restartPaintField();
//                    game.getField().init();

                }
            }
        });
        BorderFactory.createBevelBorder(3, Color.black, Color.cyan);
        return menuBar;
    }

    public void showField(Field.Type[][] cells, Point point, Field.Type who) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                switch (cells[i][j]) {
                    case NONE:
                        System.out.print(" . ");
                        break;
                    case X:
                        System.out.print(" X ");
                        break;
                    case O:
                        System.out.print(" O ");
                        break;
                }
                buttons[i][j].setText(cells[i][j].toString());
            }
            System.out.println();
        }
        System.out.println();
    }

    public void end() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                buttons[i][j].setEnabled(false);
    }

    public static void restart() {
        GameWindow newGameWindow = Game.getGameWindow();
//        Field.Type[][] retype = game.getField().getCells();
//        for (int i = 0; i < SIZE; i++) {
//            for (int j = 0; j < SIZE; j++) {
//                retype[i][j] = Field.Type.NONE;
//            }
//        }
    }

    public void restartPaintField() {
        game.restartGame();
    }
}
