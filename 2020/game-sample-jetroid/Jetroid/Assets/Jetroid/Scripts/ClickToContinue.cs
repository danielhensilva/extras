using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class ClickToContinue : MonoBehaviour {
    public string scene;
    private bool loadLock = false;

    void Start() {
    }

    void Update() {
        if ((Input.GetMouseButtonDown(0) || Input.GetKeyDown(KeyCode.Return)) && !loadLock) {
            LoadScene();
        }
    }

    void LoadScene() {
        loadLock = true;
        SceneManager.LoadScene(scene);
    }
}
