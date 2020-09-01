using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class TextTest : MonoBehaviour {

    public string message;
    private Text textInstance;

    void Start() {
        textInstance = GetComponent<Text>();
    }

    void Update() {
        textInstance.text = message;
    }
}
