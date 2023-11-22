package com.wm.adapter.WmFlightAwareAdapter.connection;

import java.util.Locale;

import javax.resource.ResourceException;
import javax.resource.spi.ConnectionRequestInfo;
import javax.security.auth.Subject;

import com.wm.adapter.WmFlightAwareAdapter.WmFlightAwareAdapter;
import com.wm.adapter.WmFlightAwareAdapter.WmFlightAwareConstants;
import com.wm.adk.connection.WmManagedConnection;
import com.wm.adk.connection.WmManagedConnectionFactory;
import com.wm.adk.error.AdapterException;
import com.wm.adk.info.ResourceAdapterMetadataInfo;
import com.wm.adk.metadata.WmDescriptor;

public class WmFlightAwareAdapterConnectionFactory extends WmManagedConnectionFactory implements WmFlightAwareConstants{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String _flightAwareUserName = "aysuyanar";
	private String _flightAwarePassword = "98769bc3cdf001e1cc5ddf1be0436794c6b5ae3d";
	private String _airport_filter = "";

	public String getFlightAwareUserName() {
		return _flightAwareUserName;
	}
	
	public void setFlightAwareUserName(String flightAwareUserName) {
		_flightAwareUserName=flightAwareUserName;
	}

	public String getFlightAwarePassword() {
		return _flightAwarePassword;
	}
	
	public void setFlightAwarePassword(String flightAwarePassword) {
		_flightAwarePassword=flightAwarePassword;
	}

	public String getAirport_filter() {
		return _airport_filter;
	}
	
	public void setAirport_filter(String airport_filter) {
		_airport_filter=airport_filter;
	}

	public WmManagedConnection createManagedConnectionObject(Subject subject, ConnectionRequestInfo connectionRequestInfo)
			throws ResourceException {
		//WmFlightAwareAdapter.retrieveLogger().logDebug(9999, "Check point: createManagedConnectionObject() ");
		return new WmFlightAwareAdapterConnection(_flightAwareUserName, _flightAwarePassword,_airport_filter);
	}


	public void fillResourceAdapterMetadataInfo(ResourceAdapterMetadataInfo info, Locale locale) throws AdapterException {
		//WmFlightAwareAdapter.retrieveLogger().logDebug(9999, "Check point: fillResourceAdapterMetadataInfo() ");
		WmFlightAwareAdapter flightAwareAdapterInstance = WmFlightAwareAdapter.getInstance();
		WmFlightAwareAdapter.getInstance().fillResourceAdapterMetadataInfo(info, locale);
		info.setAdapterName(flightAwareAdapterInstance.getAdapterName());
		info.setAdapterVersion(flightAwareAdapterInstance.getAdapterVersion());

	}


	public void fillWmDescriptor(WmDescriptor d, Locale l) throws AdapterException {
		
	//	WmFlightAwareAdapter.retrieveLogger().logDebug(9999, "Check point: fillWmDescriptor() " + FLIGHTAWARE_USER_NAME);
		d.createGroup(FLIGHTAWARE_CONNECTION,new String[] {FLIGHTAWARE_USER_NAME, FLIGHTAWARE_PASSWORD,FLIGHTAWARE_AIRPORT_FILTER});

		d.setMinStringLength(FLIGHTAWARE_USER_NAME, 1);
		d.setMinStringLength(FLIGHTAWARE_PASSWORD, 1);
		d.setPassword(FLIGHTAWARE_PASSWORD,true);
        d.setDescriptions(
                WmFlightAwareAdapter.getInstance().getAdapterResourceBundleManager(), l); 

	}

}
