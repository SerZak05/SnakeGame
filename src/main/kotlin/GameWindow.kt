import javax.swing.JFrame

class GameWindow(val game: Game) : JFrame() {
    init {
        add(game)
        addKeyListener(game.snake.keyListener)
        isResizable = false
        pack()
        title = "Snaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaake"
        setLocationRelativeTo(null)
        defaultCloseOperation = EXIT_ON_CLOSE
    }
}