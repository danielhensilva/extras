using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Collectable : MonoBehaviour
{
    public GameObject callback;
    private CrystalsCounter counter;

    // Start is called before the first frame update
    void Start()
    {
        counter = callback.GetComponent<CrystalsCounter>();
    }

    // Update is called once per frame
    void Update()
    {

    }

    void OnTriggerEnter2D(Collider2D target) {
        if (target.gameObject.tag == "Player") {
            counter.Decrement();
            Destroy(this.gameObject);
        }
    }
}
