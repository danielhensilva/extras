using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class GameManager : MonoBehaviour
{
    public static GameManager instance;

    public Text scoreText;

    public GameObject groupStart;

    public GameObject groupPoints;

    private int score;

    private bool gameStarted;

    private void Awake()
    {
        groupPoints.SetActive(false);
        groupStart.SetActive(true);
        instance = this;   
    }

    void Start()
    {
        
    }

    void Update()
    {
        
    }

    public void Restart()
    {
        SceneManager.LoadScene("Game");

        gameStarted = false;
        groupPoints.SetActive(false);
        groupStart.SetActive(true);
    }

    public void ScoreUp()
    {
        scoreText.text = (++score).ToString();
    }

    public bool IsGameStarted()
    {
        return gameStarted;
    }

    public void StartGame()
    {
        gameStarted = true;
        groupPoints.SetActive(true);
        groupStart.SetActive(false);
    }
}
