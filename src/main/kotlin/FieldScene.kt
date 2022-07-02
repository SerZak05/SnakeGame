import java.awt.Graphics
import java.awt.event.KeyListener
import java.awt.event.MouseAdapter

class FieldScene : Scene {
    val FIELD_WIDTH = 10
    val FIELD_HEIGHT = 10
    val field = Field(FIELD_WIDTH, FIELD_HEIGHT)
    val snake = Snake(this, IntVector(5, 5))

    override fun update() {
        field.update()
        snake.update()
    }

    override fun draw(g: Graphics) {
        field.draw(g)
        snake.draw(g)
    }

    override val keyListener: KeyListener
        get() = snake.keyListener
    override val mouseListener: MouseAdapter?
        get() = null
}