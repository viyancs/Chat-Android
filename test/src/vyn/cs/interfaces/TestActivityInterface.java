/**
 * Test Activity Interface - Chat-Android
 *
 * interface to connect to server
 *
 * @author    M Sofiyan
 * @email     msofyancs@gmail.com
 * @skypeid   viyancs
 * please don't remove this comment if you want to using part of full this code
 * 
 **/
package vyn.cs.interfaces;

import vyn.socket.IOSocket;
import vyn.socket.MessageCallback;

public interface TestActivityInterface {
	void TestActivitySocket(IOSocket a,MessageCallback b);
}

