import fieldObjects.Consumable
import fieldObjects.Food
import fieldObjects.Wall
import java.awt.Color
import java.awt.Graphics
import kotlin.random.Random

const val CELL_SIZE = 60

class Field(val width : Int, val height : Int) {
    private val consumableSet = mutableSetOf<Consumable>()
    val foodCnt
        get() = consumableSet.count { it is Food }
    private val toAdd = mutableListOf<Consumable>()
    private val toDelete = mutableListOf<Consumable>()

    init {
        generateConsumables(5, "Wall")
    }

    fun getConsumable(position: IntVector) : Consumable? = consumableSet.find { it.position == position }
    fun isConsumable(position : IntVector) = getConsumable(position) != null

    private fun getFreePos() : IntVector {
        var freePos = IntVector(Random.nextInt(width - 1), Random.nextInt(height - 1))
        while (isConsumable(freePos))
            freePos = IntVector(Random.nextInt(width - 1), Random.nextInt(height - 1))
        return freePos
    }

    private fun generateConsumables(cnt : Int, type : String) {
        repeat(cnt) {
            val freePos = getFreePos()
            when (type) {
                "Food" -> {
                    addConsumable(Food(freePos))
                }
                "Wall" -> addConsumable(Wall(freePos))
            }
        }
    }

    fun isOnField(position : IntVector) =
        position.x in 0 until width && position.y in 0 until height

    fun cycleAcrossBoundary(position: IntVector) : IntVector =
        IntVector(
            if (position.x < 0) position.x + width
            else position.x % width,
            if (position.y < 0) position.y + height
            else position.y % height
        )

    fun addConsumable(obj : Consumable) = toAdd.add(obj)
    fun removeConsumable(obj : Consumable) = toDelete.add(obj)

    fun update() {
        consumableSet.removeAll(toDelete)
        toDelete.clear()
        consumableSet.addAll(toAdd)
        toAdd.clear()

        // Generating food
        if (foodCnt < 10) {
            generateConsumables(1, "Food")
        }
    }

    fun draw(g : Graphics) {
        val pixelWidth = width * CELL_SIZE
        val pixelHeight = height * CELL_SIZE

        consumableSet.forEach {it.draw(g)}

        g.color = Color.BLACK
        repeat(width) { i ->
            g.drawLine(i * CELL_SIZE, 0, i * CELL_SIZE, pixelHeight)
            g.drawLine(0, i * CELL_SIZE, pixelWidth, i * CELL_SIZE)
        }
    }
}