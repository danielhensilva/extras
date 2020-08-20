using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MoveForward : MonoBehaviour {
    public float speed = 10.0f;
    private Rigidbody2D body2D;

    void Start() {
        body2D = GetComponent<Rigidbody2D>();
    }

    void Update() {
        body2D.velocity = new Vector2(transform.localScale.x, 0) * speed;
    }
}
