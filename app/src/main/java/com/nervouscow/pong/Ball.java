package com.nervouscow.pong;

import android.graphics.RectF;

class Ball {

    // Member variable declaration
    private RectF mRect;
    private float mXVelocity;
    private float mYVelocity;
    private float mBallWidth;
    private float mBallHeight;

    Ball (int screenX) {

        // Make the ball square and 1% of screen width
        mBallWidth = screenX/100;
        mBallHeight = screenX/100;

        // Initialise the RectF with 0,0,0,0 - we do it here as we only want to do it once
        // We will initialise the detail at the start of each game
        mRect = new RectF();

    }

    // Return a reference to mRect to PongGame
    RectF getRect(){
        return mRect;
    }

    // Update the ball position - called each frame/loop
    void update(long fps) {
        // Move the ball based up on the horizontal and vertical speed, and current frame rate

        // move the top left corner
        mRect.left = mRect.left + (mXVelocity / fps);
        mRect.top = mRect.top + (mYVelocity / fps);

        // match up the bottom right corner based on the size of the ball
        mRect.right = mRect.left + mBallWidth;
        mRect.bottom = mRect.top + mBallHeight;

    }

    // Reverse the vertical direction of travel
    void reverseYVelocity() {
        mYVelocity = -mYVelocity;
    }

    // Reverse the horizontal direction of travel
    void reverseXVelocity() {
        mXVelocity = -mXVelocity;
    }

    void reset(int x, int y) {

        // Initialise the four points of the rectangle which defines the ball
        mRect.left = x / 2;
        mRect.top = 0;
        mRect.right = x / 2 + mBallWidth;
        mRect.bottom = mBallHeight;

        // How fast will the ball travel - you could vary this to suit or increase
        // as the game progresses to make it harder
        mYVelocity = - (y / 3);
        mXVelocity = (y / 3);
    }

    void increaseVelocity(){
        // increase the speed by 10%
        mXVelocity = mXVelocity * 1.1f;
        mYVelocity = mYVelocity * 1.1f;
    }

    // Bounce the ball back based on whether it hits the left or right-hand side
    void batBounce(RectF batPosition) {

        // Detect centre of the bat
        float batCenter = batPosition.left + (batPosition.width() / 2);

        // Detect centre of the ball
        float ballCenter = mRect.left + (mBallWidth / 2);

        // Where on the abt did the ball hit?
        float relativeIntersect = (batCenter - ballCenter);

        // Pick a bonce direction
        if(relativeIntersect < 0) {
            // Go right
            mXVelocity = Math.abs(mXVelocity);
            // Math.abs is a static method that strips any negative values
        } else {
            // Go left
            mXVelocity = -Math.abs(mXVelocity);
        }

        // Having calculated left or right for horizontal direction simply reverse the
        // vertical direction to go back up the screen
        reverseYVelocity();

    }
}
