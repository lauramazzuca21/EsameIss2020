package connQak

import org.json.JSONObject
import java.io.File
import java.net.InetAddress
//import java.nio.charset.Charset
//import org.apache.commons.io.Charsets
  

object configurator{
//Page
	@JvmStatic public var pageTemplate		= "client-view-main"

//MQTT broker	
//	@JvmStatic var mqtthostAddr    	= "broker.hivemq.com"
	@JvmStatic var mqtthostAddr    	= "localhost"
	@JvmStatic var mqttport    		= "1883"
//
	@JvmStatic var stepsize			= "350" 
	
//Basicrobot application
	@JvmStatic var hostAddr   	    = "localhost"; 	
	@JvmStatic var port    			= "8072";
	@JvmStatic var qakdest	     	= "smartbell";
	@JvmStatic var ctxqadest 		= "ctxtearoom";
	
	@JvmStatic	//to be used by Java
	fun configure(){
		try{
			val configfile =   File("pageConfig.json")
			val config     =   configfile.readText()	//charset: Charset = Charsets.UTF_8
			//println( "		--- configurator | config=$config" )
			val jsonObject	=  JSONObject( config )			
			pageTemplate 	=  jsonObject.getString("page") 
			hostAddr    	=  jsonObject.getString("host") 
			port    		=  jsonObject.getString("port")
			qakdest         =  jsonObject.getString("qakdest")
			ctxqadest		=  jsonObject.getString("ctxqadest")
			stepsize		=  jsonObject.getString("stepsize")
			System.out.println("System IP Address : " + (InetAddress.getLocalHost().getHostAddress()).trim()); 
			System.out.println( "		--- configurator | configfile path=${configfile.getPath()} pageTemplate=$pageTemplate hostAddr=$hostAddr port=$port stepsize=$stepsize" )
		}catch(e:Exception){
			System.out.println( " &&& SORRY pageConfig.json NOT FOUND ")
			pageTemplate 	=  "client-view-main"   
			hostAddr    	=  "localhost"        
			port    		= "8072"              
			qakdest         = "smartbell"        
			ctxqadest		= "ctxtearoom"     
			stepsize		= "350"               
			System.out.println( "		--- configurator | pageTemplate=$pageTemplate hostAddr=$hostAddr port=$port stepsize=$stepsize" )
	}
		
		
	}//configure
	
	
}

