This is what is in this directory on a working distribution

	jars from other places:
		http://www.bouncycastle.org/
			Bouncy Castle Cryptography Library
			Android provides Bouncy Castle 1.47 natively (as of today anyway 7/5/2013)
			bcprov-ext-jdk15on-147.jar

		Netty-HttpTunnel
			This is getting harder to find. 
			Apparently the functionality of this and netty were merged in Netty
			4.0.0, but the interface changed sufficiently to make it work to
			upgrade

			I built it from source
            https://bitbucket.org/iainmcgin/netty-httptunnel/raw/tip/repo
			httptunnel-1.0.jar


		FindBugs (To make annotations compile)
			annotations.jar
			jsr305.jar

		luci-utility.jar
			A collection of utilities from the LUCI lab

		Processing
			These are all jars that are required to run the graphics intensive
			examples
			processing/*
				controlP5.jar
				core.jar
				gluegen-rt-natives-macosx-universal.jar
				gluegen-rt.jar
				jogl-all-natives-macosx-universal.jar
				jogl-all.jar

			
	jars without a well described pedigree:
		json-smart-1.1.1.jar
