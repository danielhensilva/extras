using System.Collections;
using System.Collections.Generic;
using System.Linq;
using UnityEngine;
using UnityEngine.UI;

public class CrystalsCounter : MonoBehaviour {
    public Text textObject;
    public int total = 1;

    void Start() {
        UpdateText();
    }

    void Update() {
    }

    public void Decrement() {
        total--;

        if (total == 0) {
            Invoke("Restart", 5f);
        }

        UpdateText();
    }

    void UpdateText() {
        if (total == 0) {
            textObject.text = "You win!";
        }
        else if (total == 1) {
            textObject.text = "1 crystal left";
        }
        else {
            textObject.text = total + " crystals left";
        }
    }

    void Restart() {
        GetComponent<Restart>().LoadSplashScene();
    }

}
