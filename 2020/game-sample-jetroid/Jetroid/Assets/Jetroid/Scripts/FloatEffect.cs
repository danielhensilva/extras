using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class FloatEffect : MonoBehaviour {
    private float startY = 0f;
    private float duration = 1f;
    private RectTransform rectTransform;

    void Start() {
        rectTransform = GetComponent<RectTransform>();
        startY = rectTransform.anchoredPosition.y;
    }

    void Update() {
        var newY = startY + (startY + Mathf.Cos(Time.time/duration * 2)) / .1f;
        rectTransform.anchoredPosition = new Vector2(rectTransform.anchoredPosition.x, newY);
    }
}
