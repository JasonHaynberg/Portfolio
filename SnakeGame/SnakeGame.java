import javax.swing.*;

public class SnakeGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game");
        Board board = new Board();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.add(board);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        board.startGame();
    }
}