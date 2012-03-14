Chat Android
============

this repository is android application for chat,and i'm using some technology like this:
<br>
1) node.js & socket.io for the server <br>
2) java android application and  [java-socket.io.client ](<https://github.com/clwillingham/java-socket.io.client>) for the client side.<br>
3) mongodb with [mongoose](<https://github.com/LearnBoost/mongoose>)for the database<br>



Running The Application
-----------------------

* install nodejs <br>
* install npm <br>
* install soket.io with npm <br> 
* install mongoose with npm <br>
* install mongoodb <br>
* run mongoodb service <br>
	`sudo mongod;`
* run socket.io server <br> 
	`sudo node app.js;`
* change url to your server
* ```java
	TestActivityInterface tac = new TestActivityInterface() {	
		@Override
		public void TestActivitySocket(IOSocket a, MessageCallback b) {
			// TODO Auto-generated method stub
			a = new IOSocket("http://192.168.1.22:3001", b);
	    		try {
	    			a.connect();
	    			a.setSessionID("loginsession");
	    			setSocketIo(a);
	    		} catch (IOException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
			
		}
	 };
	 
* run Chat Android app in eclipse or if you already apk file just install to android smartphone.<br>
* to test select peoples who want to send and receive chat.<br>

Features
-----------------------

* send chat to another people
* group chat next release
* sms gateway next release
* broadcast message to different device(iphone,blackbery,anyone) next release

Bugging 
-----------------------

* not have notification in ui android because the layout is 60% fix
* group chat is not active
* setting in ui chat not active
* broadcast message is not active(need socket network programing if you want to use in another language programing  with same server).
* login is not have authentication

Licence 
----------------------
if you want to use this repository please  don't remove comment in each code, fork and follow this repository if any question send email to msofyancs@gmail.com , i wanna to update this code to be better.

	

