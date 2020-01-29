using System.Collections;
using System.Collections.Generic;
using UnityEngine;

using System.IO;
using System.Runtime.Serialization.Formatters.Binary;

public class loadjson : MonoBehaviour {

	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
		
	}
	
	public static TextPair LoadJsonFromFile(string filepath)
    {
        
		if (!File.Exists(Application.dataPath + "/Resources/" + filepath))
        {
            return null;
        }

		StreamReader sr = new StreamReader(Application.dataPath + "/Resources/" + filepath);

        if (sr == null)
        {
            return null;
        }

        string json = sr.ReadToEnd();
        //Debug.Log(json);
        if (json.Length > 0)
        {
			return JsonUtility.FromJson<TextPair>(json);
        }

        return null;
    }

	public static GameStatus LoadSoundFromFile(string filepath)
	{

		if (!File.Exists(Application.dataPath + "/Resources/" + filepath))
		{
			return null;
		}

		StreamReader sr = new StreamReader(Application.dataPath + "/Resources/" + filepath);

		if (sr == null)
		{
			return null;
		}

		string json = sr.ReadToEnd();
		//Debug.Log(json);
		if (json.Length > 0)
		{
			return JsonUtility.FromJson<GameStatus>(json);
		}

		return null;
	}
}
