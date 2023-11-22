package com.wm.adapter.WmFlightAwareAdapter;

import com.wm.adapter.WmFlightAwareAdapter.connection.WmFlightAwareAdapterConnectionFactory;
import com.wm.adapter.WmFlightAwareAdapter.connection.WmFlightAwareListener;

public interface WmFlightAwareConstants {
	static final int ADAPTER_MAJOR_CODE = 9002;
    static final String ADAPTER_JCA_VERSION = "1.0";
	static final String ADAPTER_NAME = "WmFlightAwareAdapter";
	static final String ADAPTER_VERSION = "9.12";
			
	//Using next statement creates cyclic class loading dependency issue
	//therefore, the resource bundle class name is fully spelled out
	//static final String ADAPTER_SOURCE_BUNDLE_NAME = MyAdapterResource.class.getName();
 static final String ADAPTER_SOURCE_BUNDLE_NAME =
		"com.wm.adapter.WmFlightAwareAdapter.WmFlightAwareAdapterResourceBundle";
 
 static final String CONNECTION_TYPE =WmFlightAwareAdapterConnectionFactory.class.getName(); 
 
	// added at Phase 5 to support listener notification
	static final String LISTENER_TYPE = WmFlightAwareListener.class.getName();
	static final String ASYNCHRONOUS_NOTIFICATION_TEMPLATE = AsyncListening.class.getName();
	static final String SYNCHRONOUS_NOTIFICATION_TEMPLATE = SynchronousListening.class.getName();

 
 public static final String FLIGHTAWARE_CONNECTION = "FlightAwareConnection";
 public static final String FLIGHTAWARE_USER_NAME = "flightAwareUserName";
 public static final String FLIGHTAWARE_PASSWORD = "flightAwarePassword";
 public static final String FLIGHTAWARE_AIRPORT_FILTER = "airport_filter";
 
	// added at Phase 3 to support service template
	// properties for both the adapter service
	// and the message polling notification
	static final String GROUP_MEMBER_INPUT_PARAMETER_NAMES = "inputParameterNames";
	static final String GROUP_MEMBER_INPUT_FIELD_NAMES = "inputFieldNames";
	static final String GROUP_MEMBER_INPUT_FIELD_TYPES = "inputFieldTypes";
	static final String GROUP_MEMBER_HIDDEN_INPUT_FIELD_NAMES = "hiddenInputFieldNames";
	static final String GROUP_MEMBER_OUTPUT_PARAMETER_NAMES = "outputParameterNames";
	static final String GROUP_MEMBER_OUTPUT_FIELD_NAMES = "outputFieldNames";
	static final String GROUP_MEMBER_OUTPUT_FIELD_TYPES = "outputFieldTypes";
	
	// added at Phase 3 to support service template
	// resource domain, names for both the adapter service
	// and the message polling notification configuration
	static final String INPUT_PARAMETER_NAMES_RD = "inputParameterNamesRD";
	static final String INPUT_FIELD_NAMES_RD = "inputFieldNamesRD";
	static final String INPUT_FIELD_TYPES_RD = "inputFieldTypesRD";
	static final String OUTPUT_PARAMETER_NAMES_RD = "outputParameterNamesRD";
	static final String OUTPUT_FIELD_NAMES_RD = "outputFieldNamesRD";
	static final String OUTPUT_FIELD_TYPES_RD = "outputFieldTypesRD";
 
//added at Phase 5 to support listener notification
	// group and properties for listener notification template
	static final String GROUP_ASYNC_LISTENING = "AsynchronousListening";
	static final String GROUP_SYNC_LISTENING = "SynchronousListening";
	static final String GROUP_MEMBER_EVENT_NAME = "eventName";

	// added at Phase 5 to support listener notification
	// resource domain, names for listener notification configuration
	static final String NOTIFICATION_NAMES_RD = "notificationNamesRD";

 
 	//Logging key
	public static final int CONNECTION_INITILIZATION = 101;
	public static final int CONNECTION_DESTROY = 102;
	public static final int CLOSE_CONNECTION_EXCEPTION = 103;
	public static final int ERROR = 104;
	 	
	// added at Phase 2 to support the connection
	public static final int RESOURCE_CONN_EXCEPTION = 105;
	// added at Phase 3 to support adapter services
	public static final int RESOURCE_DOMAIN_EXCEPTION = 1001;
	public static final int INVALID_INPUT_PARAMETER = 1002;
	public static final int RESOURCE_DOMAIN_LOOKUP = 1003;
	public static final int SERVICE_EXCEPTION = 2001;
		
	// added at Phase 4 to support polling notification
	public static final int POLLING_NOTIFICATION_EXCEPTION = 3001;
		
		
	// added at Phase 5 to support listener notification
	public static final int LISTENER_CONN_EXCEPTION = 4001;
	public static final int LISTENER_NOTIFICATION_EXCEPTION = 5001;
	public static final int SYNCHRONOUS_NOTIFICATION = 5002;
	public static final int ASYNCHRONOUS_NOTIFICATION = 5004;
	public static final int LISTENER_NOTIFICATION_NOT_AVAILABLE = 5003;
}
