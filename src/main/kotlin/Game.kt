import java.awt.Dimension
import java.awt.Graphics
import javax.swing.JPanel
import javax.swing.SwingUtilities
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

class Game : JPanel() {
    val WINDOW_WIDTH = 600
    val WINDOW_HEIGHT = 600
    val snake = Snake(this, IntVector(5, 5))

    init {
        preferredSize = Dimension(WINDOW_WIDTH, WINDOW_HEIGHT)
        addKeyListener(snake.keyListener)
    }

    val FIELD_WIDTH = 10
    val FIELD_HEIGHT = 10
    val field = Field(FIELD_WIDTH, FIELD_HEIGHT)

    private var accumulatedTime = 0.0
    private val tickTime = 0.5 // in seconds
    fun update(timePassed : Double) {
        accumulatedTime += timePassed
        if (accumulatedTime < tickTime) return
        accumulatedTime -= tickTime

        if (isRunning) {
            field.update()
            snake.update()
        }
    }

    fun gameOver() {
        isRunning = false
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        field.draw(g)
        snake.draw(g)
    }

    private var isRunning = true
    private var lastFrameLength = 0.0

    @OptIn(ExperimentalTime::class)
    fun loop() {
        val duration = measureTime {
            SwingUtilities.invokeLater {
                update(lastFrameLength)
                repaint()
            }
            Thread.sleep(1L)
        }
        lastFrameLength = duration.toDouble(DurationUnit.SECONDS)
    }
}