using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CameraFollow : MonoBehaviour {
    public GameObject target;
    public float scale = 16.0f;
    private Transform t;

    void Awake() {
        var cam = GetComponent<Camera>();
        cam.orthographicSize = (Screen.height / 2f) / scale;
    }

    void Start() {
        t = target.transform;
    }

    void Update() {
        if (target != null) {
            transform.position = new Vector3(t.position.x, t.position.y, transform.position.z);
        }
    }
}
