p2p4java
===========

An independent port of the JXSE/JXTA v2.7 peer-to-peer communication library.  It has been forked so that it can be modified to work on the Android platform in addition to general java platforms. Developed by the LUCI lab at the University of California, Irvine. http://luci.ics.uci.edu

To use this for development:
	* make sure your Eclipse has m2e, the maven plug-in for Eclipse installed
	* import p2p4java into into Eclipse
	* get the additional libraries required and put them in the /lib folder.  There is a README file there with more information.
	* execute a Maven->Update Project from within Eclipse.
	* then you should be compiling the project without errors

If you are using Windows (and maybe other OS's), it is necessary to replace the Java Cryptography Policy Files on your machine with ones provided by Oracle in order to use stronger cryptography than what is natively allowed in Java.  Make sure you install the right version for the Java version you are using (e.g., 1.6)
	This is where we got it:
		http://www.oracle.com/technetwork/java/javase/downloads/jce-6-download-429243.html
		


For an example of this library being used check out:
	https://github.com/djp3/p2pchat
