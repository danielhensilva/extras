using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Ball : MonoBehaviour
{
    public float bounceForce;

    private Rigidbody2D body;

    void Start()
    {
        body = GetComponent<Rigidbody2D>();
    }

    void Update()
    {
        if (!GameManager.instance.IsGameStarted() && Input.anyKeyDown)
        {
            GameManager.instance.StartGame();
            StartBounce();
        }
    }

    void StartBounce()
    {
        float x;
        do
        {
            x = Random.Range(-1f, 1f);
        }
        while (Mathf.Abs(x) < 0.2);

        float y = 1f;
        Vector2 randomDirection = new Vector2(x, y);
        body.AddForce(randomDirection * bounceForce, ForceMode2D.Impulse);
    }
     
    private void OnCollisionEnter2D(Collision2D collision)
    {
        if (collision.gameObject.tag == "FallCheck")
        {
            GameManager.instance.Restart();
        }
        else if (collision.gameObject.tag == "Paddle")
        {
            GameManager.instance.ScoreUp();

            // Add random push
            // body.AddForce(new Vector2(Random.Range(-2f, 2f), 0), ForceMode2D.Impulse);

            var force = 2f / (collision.gameObject.transform.localScale.x / 2f);
            
            var contactPoint = collision.contacts[0].point.x;
            var paddlePosition = collision.gameObject.transform.position.x;

            var distance = 0f;

            // hit on the left -> push to the right
            if (paddlePosition > contactPoint)
            {
                distance -= contactPoint - paddlePosition;
            }
            
            // hit on the right -> push to the left
            else if (paddlePosition < contactPoint)
            {
                // right
                distance += paddlePosition - contactPoint;
            }

            body.AddForce(new Vector2(distance * force, 0), ForceMode2D.Impulse);
        }
    }
}
