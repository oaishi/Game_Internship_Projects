using UnityEngine;
using System.Collections;

[System.Serializable]  // to make this class visible in inspector
public class Boundary
{
    public float xMin, xMax, zMin, zMax;
}

public class PlayerController : MonoBehaviour
{
    public float speed;
    public float tilt;
    private Rigidbody rb;
    public Boundary boundary;
	public GameObject shot;
	public Transform shotSpawn;
	public float fireRate;
	private float nextFire;

	void Update ()
	{
		if (Input.GetButton("Fire1") && Time.time > nextFire)
		{
			nextFire = Time.time + fireRate;
			Instantiate(shot, shotSpawn.position, shotSpawn.rotation);
			GetComponent<AudioSource>().Play ();
		}
	}

    void Start()
    {
        rb = GetComponent<Rigidbody>();
    }
    //collider can be visible when convex is on the collider component
    void FixedUpdate()
    {

        float moveHorizontal = Input.GetAxis("Horizontal");
        float moveVertical = Input.GetAxis("Vertical");
		Vector3 movement = new Vector3(moveHorizontal, 0.0f, moveVertical);
        rb.velocity = movement * speed;
        rb.position = new Vector3
       (
           Mathf.Clamp(rb.position.x, boundary.xMin, boundary.xMax),
           0.0f,
           Mathf.Clamp(rb.position.z, boundary.zMin, boundary.zMax)
       );

        rb.rotation = Quaternion.Euler(0.0f, 0.0f, rb.velocity.x * -tilt);
    }
}