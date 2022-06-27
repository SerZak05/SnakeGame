fun main() {
    println("Starting...")
    val gameWindow = GameWindow(Game())
    gameWindow.isVisible = true
    while (true) {
        gameWindow.game.loop()
    }
}