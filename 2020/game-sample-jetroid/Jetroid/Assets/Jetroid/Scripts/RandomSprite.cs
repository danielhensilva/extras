using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class RandomSprite : MonoBehaviour {
    public Sprite[] sprites;
    public int currentSprite = -1;

    void Start() {
        if (currentSprite < 0 || currentSprite >= sprites.Length) {
            currentSprite = Random.Range(0, sprites.Length);
        }
        GetComponent<SpriteRenderer>().sprite = sprites[currentSprite];
    }

    void Update() {

    }
}
