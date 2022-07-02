import java.awt.Color
import java.awt.Graphics
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent

class Snake(private val mScene: FieldScene, startPos: IntVector = IntVector(0,0)) {
    var headPos = startPos

    val body = mutableListOf<IntVector>(
        headPos,
        headPos + IntVector(-1, 0),
        headPos + IntVector(-2, 0)
    )
    var direction = 0

    fun update() {
        // Updating head
        val movement = getDirVector(direction)
        headPos += movement
        headPos = mScene.field.cycleAcrossBoundary(headPos)
        //Collision
        if (headPos in body) {
            GameWindow.game.gameOver()
        }
        // Updating body parts
        body.removeLast()
        body.add(0, headPos)

        // Eating consumables
        if (mScene.field.isConsumable(headPos)) {
            val consumable = mScene.field.getConsumable(headPos)!!
            consumable.action(mScene)
            println(consumable)
            mScene.field.removeConsumable(consumable)
        }
    }

    fun draw(g : Graphics) {
        val indent = 1

        g.color = Color(9, 72, 9, 150)
        body.forEach { (x, y) ->
            g.fillRect(x * CELL_SIZE + indent, y * CELL_SIZE + indent,
                CELL_SIZE - 2 * indent, CELL_SIZE - 2 * indent)
        }

        g.color = Color.RED
        g.fillRect(headPos.x * CELL_SIZE + indent, headPos.y * CELL_SIZE + indent,
            CELL_SIZE - 2 * indent, CELL_SIZE - 2 * indent)
    }

    val keyListener = object : KeyAdapter() {
        override fun keyPressed(e: KeyEvent) {
            when(e.keyCode) {
                KeyEvent.VK_UP -> changeDirection(3)
                KeyEvent.VK_DOWN -> changeDirection(1)
                KeyEvent.VK_LEFT -> changeDirection(2)
                KeyEvent.VK_RIGHT -> changeDirection(0)
            }
        }
    }

    private fun changeDirection(dir : Int) {
        if (body[1] == headPos + getDirVector(dir)) return
        direction = dir
    }
}