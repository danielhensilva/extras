using UnityEngine;
using System.Collections;
using System.Collections.Generic;

public class PaddleController : MonoBehaviour
{
    private Rigidbody2D body;

    public float moveSpeed;

    private void Awake()
    {
        this.body = GetComponent<Rigidbody2D>();
    }

    void Start()
    {
        
    }

    void Update()
    {
        
    }

    private void FixedUpdate()
    {
        TouchMove();
    }

    void TouchMove()
    {
        // Mouse left button pressed
        if (Input.GetMouseButton(0))
        {
            Vector3 mouseScreenPosition = Input.mousePosition;
            Vector2 mouseWorldPosition = Camera.main.ScreenToWorldPoint(mouseScreenPosition);

            if (mouseWorldPosition.x == 0)
            {
                // Center screen, do nothing
                body.velocity = Vector2.zero;
            }
            else if (mouseWorldPosition.x < 0)
            {
                // Move left
                body.velocity = Vector2.left * moveSpeed;
            }
            else if (mouseWorldPosition.x > 0)
            {
                // Move right
                body.velocity = Vector2.right * moveSpeed;
            }
        }
        else
        {
            // Out of screen
            body.velocity = Vector2.zero;
        }
    }
}
