package com.innoveworkshop.gametest.assets

import com.innoveworkshop.gametest.GameActivity
import com.innoveworkshop.gametest.engine.Rectangle
import com.innoveworkshop.gametest.engine.Vector

class Husky(
    position: Vector,
    width: Float,
    height: Float,
    val mass: Float,
    color: Int,
    private val gameActivity: GameActivity
) : Rectangle(position, width, height, color) {

    private var speed = 6f // Default walking speed

    init {
        // Set movement direction based on spawn position
        if (position.x > 0f) {
            speed = -6f // Move left if spawned on the right
        }
    }

    fun onCollision(rectangle: DroppingRectangle) {
        println("Husky collided with DroppingRectangle")
        this.destroy() // remove the Husky
    }


    override fun onFixedUpdate() {
        if (gameActivity.isPaused) return // Skip updates if paused
        super.onFixedUpdate()


        position.x += speed
    }

    // Check if the Husky has moved off the screen
    fun isOutOfBounds(surfaceWidth: Float): Boolean {
        return position.x + width < 0 || position.x > surfaceWidth
    }
}
