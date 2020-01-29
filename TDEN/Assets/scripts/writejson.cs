using System.Collections;
using System.Collections.Generic;
using UnityEngine;

using System.IO;
using System.Runtime.Serialization.Formatters.Binary;
using System.Text;

public class writejson : MonoBehaviour {
	
	//public GameStatus gameStatus;
    //public GameObject[] objects;

	// Use this for initialization
	void Start () {
		/*gameStatus = new GameStatus();
        gameStatus.statusList = new Refencenes[objects.Length];
        gameStatus.gameName = "JSON Write Test";

        for (int i = 0; i < objects.Length; i++)
        {
            gameStatus.statusList[i] = new Refencenes(); 
            gameStatus.statusList[i].id = i;
            gameStatus.statusList[i].name = objects[i].name;
        }*/

	}
	
	// Update is called once per frame
	void Update () {
		
	}

	public void SaveJson(string filepath , TextPair textpair)
    {
		string json = JsonUtility.ToJson(textpair);
		string savePath = Application.dataPath + "/Resources/" + filepath;
        File.WriteAllText(savePath, json, Encoding.UTF8);        
        
        //Debug.Log("save:::" + savePath);
    }
}
