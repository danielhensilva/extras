using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Debris : MonoBehaviour {
    private SpriteRenderer renderer2D;
    private Color start;
    private Color end;
    private float t;

    void Start() {
        renderer2D = GetComponent<SpriteRenderer>();
        start = renderer2D.color;
        end = new Color(start.r, start.g, start.b, 0f);
        t = 0.0f;
    }

    void Update() {
        t += Time.deltaTime;
        renderer2D.material.color = Color.Lerp(start, end, t/2);
        if (renderer2D.material.color.a <= 0f) {
            Destroy(gameObject);
        }
    }
}
