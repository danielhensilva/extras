using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Player : MonoBehaviour {
    public float speed = 150.0f;
    public Vector2 maxVelocity = new Vector2(60, 100);
    public float jetSpeed = 200.0f;
    public bool standing = false;
    public float standingThreshold = 0.1f;
    public float airSpeedMultiplier = 0.3f;

    private Rigidbody2D body2D;
    private SpriteRenderer renderer2D;
    private PlayerController controller;
    private Animator animator;

    void Start() {
        body2D = GetComponent<Rigidbody2D>();
        renderer2D = GetComponent<SpriteRenderer>();
        controller = GetComponent<PlayerController>();
        animator = GetComponent<Animator>();
    }

    void Update() {
        var absVelX = Mathf.Abs(body2D.velocity.x);
        var absVelY = Mathf.Abs(body2D.velocity.y);

        standing = absVelY <= standingThreshold;

        var forceX = 0.0f;
        var forceY = 0.0f;

        if (controller.moving.x != 0) {
            if (absVelX < maxVelocity.x) {
                var newSpeed = speed * controller.moving.x;
                forceX = standing ? newSpeed : (newSpeed * airSpeedMultiplier);
                renderer2D.flipX = forceX < 0;
            }
            animator.SetInteger("AnimState", 1);
        }
        else {
            animator.SetInteger("AnimState", 0);
        }

        if (controller.moving.y > 0) {
            if (absVelY < maxVelocity.y) {
                forceY = jetSpeed * controller.moving.y;
            }
            animator.SetInteger("AnimState", 2);
        }
        else if (absVelY > 0 && !standing) {
            animator.SetInteger("AnimState", 3);
        }

        body2D.AddForce(new Vector2(forceX, forceY));
    }
}
