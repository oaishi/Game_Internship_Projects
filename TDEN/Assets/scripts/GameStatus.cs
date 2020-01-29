using UnityEngine;
using System;
using System.Collections;

[Serializable]
public class GameStatus
{
    public Refencenes[] statusList;
}

[Serializable]
public class TextPair
{
	public string name;
	public string type;
}

[Serializable]
public class Refencenes
{
    public Refencenes()
    {
        name = "";
        url = "";
    }

    public string name;
	public string url;
}

