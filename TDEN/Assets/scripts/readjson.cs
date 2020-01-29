using System.Collections;
using System.Collections.Generic;
using UnityEngine;

using System;
using System.Runtime.Serialization.Formatters.Binary;
using System.IO;

public class readjson : MonoBehaviour {

	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
		if(Input.GetKeyDown(KeyCode.S))
        {
            writejson obj = this.GetComponent<writejson>();

			TextPair textpair = new TextPair ();

			textpair.name = "oaishi" ;
			textpair.type = "input" ;

			obj.SaveJson("Test01.json", textpair);
            
        }

        if (Input.GetKeyDown(KeyCode.L))
        {
			TextPair status = loadjson.LoadJsonFromFile("Test01.json");
            //Debug.Log(status);
        }
	}
}
