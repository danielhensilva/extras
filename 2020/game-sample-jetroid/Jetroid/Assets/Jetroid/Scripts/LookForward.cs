using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class LookForward : MonoBehaviour {
    public string layer = "Solid";
    public bool needsCollision = true;

    public Transform sightStart;
    public Transform sightEnd;

    private bool collision;

    void Start() {
    }

    void Update() {
        collision = Physics2D.Linecast(sightStart.position, sightEnd.position, 1 << LayerMask.NameToLayer(layer));
        Debug.DrawLine(sightStart.position, sightEnd.position, Color.green);

        if (collision == needsCollision) {
            transform.localScale = new Vector3(transform.localScale.x == 1 ? -1 : 1, 1, 1);
        }
    }
}
