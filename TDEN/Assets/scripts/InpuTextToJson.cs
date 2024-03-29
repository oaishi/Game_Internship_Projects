using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class InpuTextToJson : MonoBehaviour {

	public InputField text_to_json;
	public Button save_to_json;

	// Use this for initialization
	void Start () {
		save_to_json = GetComponent<Button> ();
		save_to_json.onClick.AddListener (SavetoJson);
	}
	
	// Update is called once per frame
	void Update () {
		
	}

	void SavetoJson(){
		Debug.Log ("saving value " + text_to_json.text + " to user.json");

		writejson obj = this.GetComponent<writejson>();

		TextPair textpair = new TextPair ();
		textpair.name = text_to_json.text ;
		textpair.type = "input" ;

		obj.SaveJson("user.json", textpair);

		TextPair status = loadjson.LoadJsonFromFile("user.json");
		Debug.Log(status.name);

	}
}
