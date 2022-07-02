fun main() {
    println("Starting...")
    GameWindow.isVisible = true
    GameWindow.game.changeScene(FieldScene())
    while (true) {
        GameWindow.game.loop()
    }
}