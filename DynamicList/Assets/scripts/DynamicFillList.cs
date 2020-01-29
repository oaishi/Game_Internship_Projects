using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class DynamicFillList : MonoBehaviour {

	public ScrollRect scrollview;
	public GameObject scrollcontent;
	public GameObject scrollItemPrefab; 

	// Use this for initialization
	void Start () {
		for (int a = 1; a <= 20; a++) {
			GenerateItem ( "itemcur_" + a.ToString ());
		}

		scrollview.verticalNormalizedPosition = 1;
	}
	
	// Update is called once per frame
	void Update () {
		
	}

	void GenerateItem(string itemvar){
		GameObject scrollitemobj = Instantiate (scrollItemPrefab);
		scrollitemobj.transform.SetParent (scrollcontent.transform, false);
		scrollitemobj.transform.Find ("num").GetComponent<Text> ().text = itemvar;
	}
}
