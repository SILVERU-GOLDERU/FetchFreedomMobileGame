package com.innoveworkshop.gametest.assets

import com.innoveworkshop.gametest.GameActivity
import com.innoveworkshop.gametest.engine.Rectangle
import com.innoveworkshop.gametest.engine.Vector

class Dogs(
    position: Vector,
    width: Float,
    height: Float,
    val mass: Float,
    color: Int,
    private val gameActivity: GameActivity
) : Rectangle(position, width, height, color) {

    private var speed = 4f // Default walking speed

    init {
        // Set movement direction based on spawn position
        if (position.x > 0f) {
            speed = -4f // Move left if spawned on the right
        }
    }

    fun onCollision(rectangle: DroppingRectangle) {
        println("Dogs collided with DroppingRectangle")
        this.destroy() // remove the Dogs
    }


    override fun onFixedUpdate() {
        if (gameActivity.isPaused) return // Skip updates if paused
        super.onFixedUpdate()

        // Move the Dogs
        position.x += speed
    }

    // Check if the Dogs has moved off the screen
    fun isOutOfBounds(surfaceWidth: Float): Boolean {
        return position.x + width < 0 || position.x > surfaceWidth
    }
}
