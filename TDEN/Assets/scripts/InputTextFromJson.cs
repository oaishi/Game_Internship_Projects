using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class InputTextFromJson : MonoBehaviour {

	public Text text_from_json;
	public Button save_from_json;

	// Use this for initialization
	void Start () {
		save_from_json = GetComponent<Button> ();
		save_from_json.onClick.AddListener (SaveFromJson);
	}
	
	// Update is called once per frame
	void Update () {
		
	}

	void SaveFromJson(){

		GameStatus status = loadjson.LoadSoundFromFile("test.json");
		int iter = Random.Range (0, status.statusList.Length);
		text_from_json.text =status.statusList[iter].name;

	}

}
