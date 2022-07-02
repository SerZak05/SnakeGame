import javax.swing.JFrame

object GameWindow : JFrame() {
    val game = Game()
    init {
        add(game)
        isResizable = false
        pack()
        title = "Snaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaake"
        setLocationRelativeTo(null)
        defaultCloseOperation = EXIT_ON_CLOSE
    }
}