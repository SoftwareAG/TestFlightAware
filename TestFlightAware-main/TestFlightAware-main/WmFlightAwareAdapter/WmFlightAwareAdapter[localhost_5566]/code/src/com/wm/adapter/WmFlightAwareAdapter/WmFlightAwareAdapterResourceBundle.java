package com.wm.adapter.WmFlightAwareAdapter;

import java.util.ListResourceBundle;

import com.wm.adk.ADKGLOBAL;

public class WmFlightAwareAdapterResourceBundle extends ListResourceBundle implements WmFlightAwareConstants{

	static final String IS_PKG_NAME = "/WmFlightAwareAdapter/";
	private static final String OH_DEFAULT="doc/OnlineHelp/AdapterOH.html";
	
	 static final Object[][] _contents = {
			   // adapter type display name.
			   {ADAPTER_NAME + ADKGLOBAL.RESOURCEBUNDLEKEY_DISPLAYNAME, "FlightAware Adapter"}
			   // adapter type descriptions.
			   ,{ADAPTER_NAME + ADKGLOBAL.RESOURCEBUNDLEKEY_DESCRIPTION,
			    "Adapter for Client connection to Flight Aware System "}
			   // adapter type vendor.
			   ,{ADAPTER_NAME + ADKGLOBAL.RESOURCEBUNDLEKEY_VENDORNAME, "Software AG"}
			   //Copyright URL Page
			   ,{ADAPTER_NAME + ADKGLOBAL.RESOURCEBUNDLEKEY_THIRDPARTYCOPYRIGHTURL, 
			    IS_PKG_NAME + "copyright.html"}
			   //Copyright Encoding
			   ,{ADAPTER_NAME + ADKGLOBAL.RESOURCEBUNDLEKEY_COPYRIGHTENCODING, "UTF-8"}
					 //About URL Page
			  // ,{ADAPTER_NAME + ADKGLOBAL.RESOURCEBUNDLEKEY_ABOUT, IS_PKG_NAME + "About.html"}
				// The online help link to the adapter's "about" information.
				, { ADAPTER_NAME + ADKGLOBAL.RESOURCEBUNDLEKEY_ABOUT + ADKGLOBAL.RESOURCEBUNDLEKEY_HELPURL,
						IS_PKG_NAME + OH_DEFAULT }
			   //Release Notes URL Page
			   ,{ADAPTER_NAME + ADKGLOBAL.RESOURCEBUNDLEKEY_RELEASENOTEURL, IS_PKG_NAME + "ReleaseNotes.html"}
		        ,{ADAPTER_NAME + ADKGLOBAL.RESOURCEBUNDLEKEY_HELPURL, 
		            IS_PKG_NAME + OH_DEFAULT}
		        

		        // online help link to the connection types supported. 
		        ,{ADAPTER_NAME + ADKGLOBAL.RESOURCEBUNDLEKEY_LISTCONNECTIONTYPES + ADKGLOBAL.RESOURCEBUNDLEKEY_HELPURL, 
		          IS_PKG_NAME + OH_DEFAULT}
		        // online help link to adapter's list of connections (backend resource)
		        ,{ADAPTER_NAME + ADKGLOBAL.RESOURCEBUNDLEKEY_LISTRESOURCES + ADKGLOBAL.RESOURCEBUNDLEKEY_HELPURL, 
		          IS_PKG_NAME + OH_DEFAULT}
				,
				{ ADAPTER_NAME + ADKGLOBAL.RESOURCEBUNDLEKEY_LISTLISTENERNOTIFICATIONS
						+ ADKGLOBAL.RESOURCEBUNDLEKEY_HELPURL,
						IS_PKG_NAME + OH_DEFAULT }
				// online help link to adapter's list of listener
				, { ADAPTER_NAME + ADKGLOBAL.RESOURCEBUNDLEKEY_LISTLISTENERS + ADKGLOBAL.RESOURCEBUNDLEKEY_HELPURL,
						IS_PKG_NAME + OH_DEFAULT }
				// online help link to adapter's list of listener types
				, { ADAPTER_NAME + ADKGLOBAL.RESOURCEBUNDLEKEY_LISTLISTENERTYPES + ADKGLOBAL.RESOURCEBUNDLEKEY_HELPURL,
						IS_PKG_NAME + OH_DEFAULT }
		      
		        // the connection type display name.
		        ,{CONNECTION_TYPE + ADKGLOBAL.RESOURCEBUNDLEKEY_DISPLAYNAME,
		          "Flight Aware  Server Connection"}
		        // the connection type description.
		        ,{CONNECTION_TYPE + ADKGLOBAL.RESOURCEBUNDLEKEY_DESCRIPTION,
		          "connection for Flight Aware server"}
		        // connection type online help link. 
		        ,{CONNECTION_TYPE + ADKGLOBAL.RESOURCEBUNDLEKEY_HELPURL,
		          IS_PKG_NAME + OH_DEFAULT}
		        
			   // added at Phase 2 to support connector
		        // for connector properties,
		        ,{FLIGHTAWARE_CONNECTION + ADKGLOBAL.RESOURCEBUNDLEKEY_GROUP, "Flight Aware Connection" }
		        ,{FLIGHTAWARE_CONNECTION + ADKGLOBAL.RESOURCEBUNDLEKEY_DESCRIPTION, "Connection to a Flight Aware Server" }
		        
		        ,{FLIGHTAWARE_USER_NAME + ADKGLOBAL.RESOURCEBUNDLEKEY_DISPLAYNAME, "fightAwareUserName" }
		        ,{FLIGHTAWARE_USER_NAME + ADKGLOBAL.RESOURCEBUNDLEKEY_DESCRIPTION, "fightAwareUserName" }
		        ,{FLIGHTAWARE_PASSWORD + ADKGLOBAL.RESOURCEBUNDLEKEY_DISPLAYNAME, "fightAwarePassword" }
		        ,{FLIGHTAWARE_PASSWORD + ADKGLOBAL.RESOURCEBUNDLEKEY_DESCRIPTION, "fightAwarePassword" }
		        ,{FLIGHTAWARE_AIRPORT_FILTER + ADKGLOBAL.RESOURCEBUNDLEKEY_DISPLAYNAME, "Airport Filter" }
		        ,{FLIGHTAWARE_AIRPORT_FILTER + ADKGLOBAL.RESOURCEBUNDLEKEY_DESCRIPTION, "airport filter" }
		        
		        , { LISTENER_TYPE + ADKGLOBAL.RESOURCEBUNDLEKEY_DISPLAYNAME, "Flight Aware Listener" },
				{ LISTENER_TYPE + ADKGLOBAL.RESOURCEBUNDLEKEY_DESCRIPTION, "listener for Flight Aware notification" },
				{ LISTENER_TYPE + ADKGLOBAL.RESOURCEBUNDLEKEY_HELPURL,
						IS_PKG_NAME + OH_DEFAULT }

				
				// for event listening notification template-REMOVED FUNCTIONALITY
				,
				{ ASYNCHRONOUS_NOTIFICATION_TEMPLATE + ADKGLOBAL.RESOURCEBUNDLEKEY_DISPLAYNAME,
						"Asynchronous Listener Notification" },
				{ ASYNCHRONOUS_NOTIFICATION_TEMPLATE + ADKGLOBAL.RESOURCEBUNDLEKEY_DESCRIPTION,
						"Asynchronously process the Flight Aware server listener notification message" },
				{ ASYNCHRONOUS_NOTIFICATION_TEMPLATE + ADKGLOBAL.RESOURCEBUNDLEKEY_HELPURL,
						IS_PKG_NAME + OH_DEFAULT },
				{ SYNCHRONOUS_NOTIFICATION_TEMPLATE + ADKGLOBAL.RESOURCEBUNDLEKEY_DISPLAYNAME,
						"Synchronous Listener Notification" },
				{ SYNCHRONOUS_NOTIFICATION_TEMPLATE + ADKGLOBAL.RESOURCEBUNDLEKEY_DESCRIPTION,
						"Synchronously process the Flight Aware server listener notification message" },
				{ SYNCHRONOUS_NOTIFICATION_TEMPLATE + ADKGLOBAL.RESOURCEBUNDLEKEY_HELPURL,
						IS_PKG_NAME + OH_DEFAULT }
				// for asynchronous listener notification group
				, { GROUP_ASYNC_LISTENING + ADKGLOBAL.RESOURCEBUNDLEKEY_GROUP, "Listener Notification" },
				{ GROUP_ASYNC_LISTENING + ADKGLOBAL.RESOURCEBUNDLEKEY_DESCRIPTION, "Listener notification" }

				// properties for asynchronous listener notification template
				, { GROUP_MEMBER_EVENT_NAME + ADKGLOBAL.RESOURCEBUNDLEKEY_DISPLAYNAME, "Monitor" },
				{ GROUP_MEMBER_EVENT_NAME + ADKGLOBAL.RESOURCEBUNDLEKEY_DESCRIPTION,
						"Sample server notification event name" }

		        
		        // adapter logging message
		        , { Integer.toString(CONNECTION_INITILIZATION), "Initializing Flight Aware Connection" },
				{ Integer.toString(CONNECTION_DESTROY), "Destroying Flight Aware Connection" },
				{ Integer.toString(CLOSE_CONNECTION_EXCEPTION), "Error while closing Flight Aware Connection: \"{0}\"." },
				{ Integer.toString(ERROR), "Error: \"{0}\"." }

				// adapter exception messages
				
				, { Integer.toString(RESOURCE_CONN_EXCEPTION), "Resource Connection Exception:" }
				 ,{"9999", "Flight Aware Adapter Info: \"{0}\"."},
				 { Integer.toString(INVALID_INPUT_PARAMETER), "Invalid Input Parameters" },
					{ Integer.toString(RESOURCE_DOMAIN_LOOKUP), "Look up the resource domain \"{0}\" : \"{1}\"." }

					, { Integer.toString(SERVICE_EXCEPTION), "Service Exception: \"{0}\"." }

					// added at Phase 4 to support polling notification_REMOVED FUNCTIONALITY
					, { Integer.toString(POLLING_NOTIFICATION_EXCEPTION), "Message Polling Exception: \"{0}\"." }

				 , { Integer.toString(LISTENER_CONN_EXCEPTION), "Resource Listener Connection Exception:" },
					{ Integer.toString(LISTENER_NOTIFICATION_EXCEPTION), "Listener Notification Exception:" },
					{ Integer.toString(SYNCHRONOUS_NOTIFICATION), "Synchronous notification: \"{0}\"." },
					{ Integer.toString(ASYNCHRONOUS_NOTIFICATION), "Asynchronous notification: \"{0}\"." },
					{ Integer.toString(LISTENER_NOTIFICATION_NOT_AVAILABLE),
							"There is no enabled notification for listener: \"{0}\"." } 
	 
			  };
	
	protected Object[][] getContents() {
		// TODO Auto-generated method stub
		return _contents;
	}

}
