import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class Board extends JPanel implements ActionListener {
    private final int TILE_SIZE = 25;
    private final int BOARD_WIDTH = 600;
    private final int BOARD_HEIGHT = 600;
    private final int NUM_TILES_X = BOARD_WIDTH / TILE_SIZE;
    private final int NUM_TILES_Y = BOARD_HEIGHT / TILE_SIZE;

    private ArrayList<Snake> snake;
    private Point food;
    private boolean isGameOver;
    private boolean isGamePaused;
    private Timer timer;
    private char direction;

    public Board() {
        this.snake = new ArrayList<>();
        this.direction = 'R'; // Initial direction: Right
        this.isGameOver = false;
        this.isGamePaused = false;
        this.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT && direction != 'R') {
                    direction = 'L';
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT && direction != 'L') {
                    direction = 'R';
                }
                if (e.getKeyCode() == KeyEvent.VK_UP && direction != 'D') {
                    direction = 'U';
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN && direction != 'U') {
                    direction = 'D';
                }
            }
        });
    }

    public void startGame() {
        snake.clear();
        snake.add(new Snake(5, 5));
        generateFood();

        timer = new Timer(100, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isGameOver) {
            moveSnake();
            checkCollisions();
            repaint();
        }
    }

    public void moveSnake() {
        Snake head = snake.get(0);
        int newX = head.getX();
        int newY = head.getY();

        switch (direction) {
            case 'U': newY--; break;
            case 'D': newY++; break;
            case 'L': newX--; break;
            case 'R': newX++; break;
        }

        // Move the snake
        snake.add(0, new Snake(newX, newY));

        // If snake eats food
        if (newX == food.x && newY == food.y) {
            generateFood();
        } else {
            snake.remove(snake.size() - 1);
        }
    }

    public void checkCollisions() {
        Snake head = snake.get(0);
        // Check if snake hits the wall
        if (head.getX() < 0 || head.getX() >= NUM_TILES_X || head.getY() < 0 || head.getY() >= NUM_TILES_Y) {
            isGameOver = true;
        }

        // Check if snake collides with itself
        for (int i = 1; i < snake.size(); i++) {
            if (head.getX() == snake.get(i).getX() && head.getY() == snake.get(i).getY()) {
                isGameOver = true;
            }
        }
    }

    public void generateFood() {
        Random rand = new Random();
        food = new Point(rand.nextInt(NUM_TILES_X), rand.nextInt(NUM_TILES_Y));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (isGameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Game Over", BOARD_WIDTH / 4, BOARD_HEIGHT / 2);
            return;
        }

        // Draw food
        g.setColor(Color.GREEN);
        g.fillRect(food.x * TILE_SIZE, food.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);

        // Draw snake
        g.setColor(Color.YELLOW);
        for (Snake s : snake) {
            g.fillRect(s.getX() * TILE_SIZE, s.getY() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }
    }
}