package game.model;

import game.controller.GameController;
import game.view.GameWindow;

public class Game {
    private Field field;
    public User user;
    Computer computer;
    GameController controller;
    private static GameWindow gameWindow;
    static CurrentCountWinner countWinner;
    private Point lastPointX;
    private Point lastPointO;

    public Game() {
        user = new User();
        computer = new Computer(this);
        gameWindow = new GameWindow(this);
    }

    public static void setCurrentWinnerCount(CurrentCountWinner currentWinnerCount) {
        countWinner = currentWinnerCount;
    }

    public Field.Type[][] getFieldCells() {
        return field.getCells();
    }

    public static GameWindow getGameWindow() {
        return gameWindow;
    }

    public Field getField() {
        return field;
    }

    public GameController getController() {
        return controller;
    }

    public void start() {
        controller = new GameController(gameWindow, user, computer);
        controller.initGameController();
        field = new Field(controller);
        computer.setField(field);
        computer.setController(controller);
        field.init();
        user.existPoint(controller);
        while (true) {
            field.shoot(user.getShootPoint(), Field.Type.X);
            lastPointX = field.getPoint();
            if (field.checkDraw()) {
                countWinner.setCurrentDraw();
                continue;
            }
            if (field.whoIsWinner() == Field.Type.X) {
                countWinner.setCurrentWinX();
                System.out.println("Победил " + Field.Type.X);
                break;
            }
            controller.doShoot(computer.getComputerPoint(), Field.Type.O);
            field.shoot(computer.getComputerPoint(), Field.Type.O);
            lastPointO = field.getPoint();
            if (field.whoIsWinner() == Field.Type.O) {
                countWinner.setCurrentWinY();
                System.out.println("Победил " + Field.Type.O);
                break;
            }
        }
    }

    public Point getLastPointX() {
        return lastPointX;
    }

    public Point getLastPointO() {
        return lastPointO;
    }

    public static void stopGame() {
        getGameWindow().setVisible(false);
    }

    public Field.Type[][] restartGame() {
        Field.Type[][] returnCells = this.getFieldCells();
        return returnCells;
    }
}
