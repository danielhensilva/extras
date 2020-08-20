using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class Restart : MonoBehaviour {
    public GameObject target;
    private bool triggered = false;

    void Start() {
        InvokeRepeating("CheckSplashScreen", 3f, 3f);
    }

    void Update() {
    }

    void CheckSplashScreen() {
        if (triggered) {
            LoadSplashScene();
            return;
        }

        if (target == null || !target.activeInHierarchy) {
            triggered = true;
        }
    }

    public void LoadSplashScene() {
        SceneManager.LoadScene("SplashScene");
    }

}
