package fieldObjects

import CELL_SIZE
import FieldScene
import Game
import IntVector
import java.awt.Color
import java.awt.Graphics

class Food(position: IntVector) : Consumable(position,
    {fs: FieldScene -> fs.snake.body.add(fs.snake.body.last())}
) {
    override fun draw(g: Graphics) {
        val indent = 1
        g.color = Color.GREEN
        g.fillRect(position.x * CELL_SIZE + indent, position.y * CELL_SIZE + indent,
            CELL_SIZE - 2 * indent, CELL_SIZE - 2 * indent)
    }
}