using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.Networking;

using System.IO;
using System.Runtime.Serialization.Formatters.Binary;
using System.Text;

public class PlayAudio : MonoBehaviour {

	public Button play_audio_button;
	public InputField file_name;

	// Use this for initialization
	void Start () {
		
		play_audio_button = GetComponent<Button> ();
		play_audio_button.onClick.AddListener (Downloadandplay);


		/*GameStatus gameStatus;
		gameStatus = new GameStatus();
		gameStatus.statusList = new Refencenes[2];

		for (int i = 0; i < 2 ; i++)
		{
			gameStatus.statusList[i] = new Refencenes(); 
			gameStatus.statusList[i].url = "https://www.kozco.com/tech/piano2.wav";
			gameStatus.statusList[i].name = "oaishi";
		}

		string json = JsonUtility.ToJson(gameStatus);
		string savePath = Application.dataPath + "/Resources/sounds.json";
		File.WriteAllText(savePath, json, Encoding.UTF8);*/

	}

	// Update is called once per frame
	void Update () {
		
	}

	void Downloadandplay(){

		string url = "https://www.kozco.com/tech/piano2.wav";
		string name = file_name.text;
		Debug.Log (name);

		GameStatus status = loadjson.LoadSoundFromFile("sounds.json");

		for (int i = 0; i < status.statusList.Length; i++) {

			if (status.statusList [i].name == name) {
				url = status.statusList [i].url;
				break;
			}
			//Debug.Log (status.statusList [i].url);
		}
		
		StartCoroutine (GetAudioClip (url));
	}

	IEnumerator GetAudioClip(string url)
	{
		using (UnityWebRequest www = UnityWebRequestMultimedia.GetAudioClip( url, AudioType.WAV))
		{
			yield return www.SendWebRequest();

            if (www.isNetworkError)
            {
                Debug.Log(www.error);
            }
            else
            {
                AudioClip clip = DownloadHandlerAudioClip.GetContent(www);
				
				Debug.Log( clip + " length: " + clip.length );
				if (clip)
				{
					GetComponent<AudioSource>().clip = clip;
					GetComponent<AudioSource>().Play();
				}
				
            }
        }
		
	}
}
	