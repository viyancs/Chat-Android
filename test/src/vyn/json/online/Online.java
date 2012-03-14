/**
 * Online - Chat-Android
 *
 * class to parse json users who has been online from server 
 *
 * @author    M Sofiyan
 * @email     msofyancs@gmail.com
 * @skypeid   viyancs
 * please don't remove this comment if you want to using part of full this code
 * 
 **/
package vyn.json.online;
import com.google.gson.annotations.SerializedName;
public class Online {
	@SerializedName("_id")
	public String _id;
	
	@SerializedName("uid")
	public String uid;
	
	@SerializedName("username")
	public String username;
}
