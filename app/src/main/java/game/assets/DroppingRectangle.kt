package com.innoveworkshop.gametest.assets

import com.innoveworkshop.gametest.GameActivity
import com.innoveworkshop.gametest.engine.Rectangle
import com.innoveworkshop.gametest.engine.Vector

class DroppingRectangle(
    position: Vector?,
    width: Float,
    height: Float,
    val mass: Float,
    color: Int,
    private val gameActivity: GameActivity
) : Rectangle(position, width, height, color) {
    private val gravity = 9.8f
    private var acceleration = Vector(0f, 0f)
    private val worldScale = 50f


    fun collidesWith(dogs: Dogs): Boolean {
        return position.x < dogs.position.x + dogs.width &&
                position.x + width > dogs.position.x &&
                position.y < dogs.position.y + dogs.height &&
                position.y + height > dogs.position.y
    }

    fun collidesWith(husky: Husky): Boolean {
        return position.x < husky.position.x + husky.width &&
                position.x + width > husky.position.x &&
                position.y < husky.position.y + husky.height &&
                position.y + height > husky.position.y
    }

    fun onCollision(dogs: Dogs) {
        println("DroppingRectangle collided with Dogs")
        this.destroy()
    }

    fun onCollision(husky: Husky) {
        println("DroppingRectangle collided with husky")
        this.destroy()
    }

    override fun onFixedUpdate() {
        if (gameActivity.isPaused) return
        super.onFixedUpdate()

        if (!isFloored) {
            val gravityForce = gravity * mass // F = g * m
            acceleration.y += gravityForce / mass // a = F/m
            position.y += acceleration.y / worldScale
        }
    }
    fun isOutOfBounds(surfaceHeight: Float): Boolean {
        return position.y > surfaceHeight - height
    }
}
