package com.wm.adapter.WmFlightAwareAdapter.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Locale;

import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.resource.ResourceException;
import javax.resource.spi.ConnectionRequestInfo;
import javax.security.auth.Subject;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.wm.adapter.WmFlightAwareAdapter.WmFlightAwareAdapter;
import com.wm.adapter.WmFlightAwareAdapter.WmFlightAwareConstants;
import com.wm.adk.connection.WmManagedConnection;
import com.wm.adk.connection.WmManagedConnectionFactory;
import com.wm.adk.error.AdapterConnectionException;
import com.wm.adk.error.AdapterException;
import com.wm.adk.info.ResourceAdapterMetadataInfo;
import com.wm.adk.metadata.ResourceDomainValues;
import com.wm.adk.metadata.WmAdapterAccess;
import com.wm.adk.metadata.WmDescriptor;
import com.wm.data.IData;
import com.wm.data.IDataCursor;
import com.wm.data.IDataFactory;
import com.wm.data.IDataUtil;


public class WmFlightAwareAdapterConnection extends WmManagedConnection implements WmFlightAwareConstants {

	private static OutputStreamWriter  writer = null;
	private static  BufferedReader  reader = null;
	private static InputStream  inputStream = null;
	private static  SSLSocket  ssl_socket = null;
	private String _flightAwareUserName = "";
	private String _flightAwarePassword = "";

	String initiation_command = "";
	private boolean listenerMode = false;
	String machineName = "firehose.flightaware.com";

	public WmFlightAwareAdapterConnection(String flightAwareUserName, String flightAwarePassword, String airport_filter)
	{
		_flightAwareUserName=flightAwareUserName;
		_flightAwarePassword=flightAwarePassword;
		if (airport_filter=="") {
			initiation_command = "live username "+_flightAwareUserName+" password "+_flightAwarePassword;
		}
		else {
			initiation_command = "live username "+_flightAwareUserName+" password "+_flightAwarePassword + " airport_filter \""+airport_filter+"\" ";

		}
		//WmFlightAwareAdapter.retrieveLogger().logInfo(9999,"Check point: "+ initiation_command + "machineName"+ machineName);
	}
	/*
	 * Specifies that initialization is required immediately after a connection
	 * is created for the first time.
	 */
	protected boolean initializationRequired() {
		return true;
	}
	protected void initializeConnection(Subject subject,ConnectionRequestInfo requestInfo) throws ResourceException{

		WmFlightAwareAdapter.retrieveLogger().logInfo(CONNECTION_INITILIZATION);
		//	WmFlightAwareAdapter.retrieveLogger().logInfo(9999,"Check point: initializeConnection() ");
		try {

			//SSLSocket ssl_socket;
			SSLParameters sslParams = new SSLParameters();
			sslParams.setEndpointIdentificationAlgorithm("HTTPS");
			sslParams.setProtocols(new String[] {"TLSv1.2"});
			ssl_socket = (SSLSocket) SSLSocketFactory.getDefault().createSocket(machineName, 1501);
			// enable certifcate validation:
			ssl_socket.setSSLParameters(sslParams);
			initiation_command += "\n";

			//send your initiation command
			writer = new OutputStreamWriter(ssl_socket.getOutputStream(), "UTF8");
			writer.write(initiation_command);
			writer.flush();

			inputStream = ssl_socket.getInputStream();

			// read messages from FlightAware
			reader = new BufferedReader(new InputStreamReader(inputStream));


			WmFlightAwareAdapter.retrieveLogger().logInfo(9999,"******* WmFlightAwareAdapterConnection Established"+ "**********");

		}
		catch(Exception e) {
			AdapterConnectionException ace = WmFlightAwareAdapter.getInstance().createAdapterConnectionException(
					RESOURCE_CONN_EXCEPTION, null, e);
			ace.setFatal(true);
			throw ace;
		}


	}

	/*
	 * Set the connection to listener mode
	 *
	 * flag is a boolean flag indicates it is listener mode or interaction mode
	 * an AdapterConnectionException is thrown if there is communication
	 * problem.
	 */
	public void setToListenerMode(boolean flag) throws AdapterConnectionException, IOException {

		try{if (listenerMode != flag) {
			listenerMode = flag;
			return;
		}}
		catch (Exception e) {
			AdapterConnectionException ace = WmFlightAwareAdapter.getInstance()
					.createAdapterConnectionException(RESOURCE_CONN_EXCEPTION, null, e);

			// see the comment of setFatal in initializeConnection method
			ace.setFatal(true);
			throw ace;
		}
		return;
	}

	@Override
	public Boolean adapterCheckValue(String arg0, String arg1, String[][] arg2, String arg3) throws AdapterException {

		//WmFlightAwareAdapter.retrieveLogger().logInfo(9999,"I am adapterCheckValue() ");
		return null;
	}

	public BufferedReader getReader() throws AdapterException, IOException
	{

		// WmFlightAwareAdapter.retrieveLogger().logInfo(9999,"Check point: ssl connection " +ssl_socket.isConnected());

		if(ssl_socket.isConnected()) {
			// WmFlightAwareAdapter.retrieveLogger().logInfo(9999,"Check point: ssl connection isInputShutdown " +ssl_socket.isInputShutdown() + " isBound() " + ssl_socket.isBound()+ " isClosed() " +ssl_socket.isClosed());
			return reader;
		}
		else {
			try {
				destroyConnection();
			} catch (ResourceException e1) {
				WmFlightAwareAdapter.retrieveLogger().logInfo(9999,"Error during destring the Flight Aware Connection" );
				e1.printStackTrace();
			}

			try {
				SSLParameters sslParams = new SSLParameters();
				sslParams.setEndpointIdentificationAlgorithm("HTTPS");
				sslParams.setProtocols(new String[] {"TLSv1.2"});
				ssl_socket = (SSLSocket) SSLSocketFactory.getDefault().createSocket(machineName, 1501);
				// enable certificate validation:
				ssl_socket.setSSLParameters(sslParams);
				initiation_command += "\n";

				//send your initiation command
				writer = new OutputStreamWriter(ssl_socket.getOutputStream(), "UTF8");
				writer.write(initiation_command);
				writer.flush();

				inputStream = ssl_socket.getInputStream();

				// read messages from FlightAware
				reader = new BufferedReader(new InputStreamReader(inputStream));
				// WmFlightAwareAdapter.retrieveLogger().logInfo(9999,"Stream Reader is created Reader is " +reader.ready() );
				return reader;
			}
			catch(Exception e) {
				AdapterConnectionException ace = WmFlightAwareAdapter.getInstance().createAdapterConnectionException(
						RESOURCE_CONN_EXCEPTION, null, e);
				ace.setFatal(true);
				throw ace;
			}
		}
	}


	@Override
	protected void destroyConnection() throws ResourceException {
		try {
			// WmFlightAwareAdapter.retrieveLogger().logInfo(9999,"Check point: destroy connection ");
			writer.close();
			reader.close();
			inputStream.close();
			ssl_socket.close();
		} catch (IOException e) {
			AdapterConnectionException ace = WmFlightAwareAdapter.getInstance().createAdapterConnectionException(
					RESOURCE_CONN_EXCEPTION, null, e);
			ace.setFatal(true);
			throw ace;
		}

	}

	@Override
	public void registerResourceDomain(WmAdapterAccess access) throws AdapterException {
		// added at Phase 5 to support listener notification
		try {
			access.addResourceDomainLookup(null, OUTPUT_PARAMETER_NAMES_RD, this);
			access.addResourceDomainLookup(null, OUTPUT_FIELD_TYPES_RD, this);
			access.addResourceDomainLookup(null, OUTPUT_FIELD_NAMES_RD, this);
			access.addResourceDomainLookup(null, NOTIFICATION_NAMES_RD, this);
		} catch (Exception ex) {
			throw WmFlightAwareAdapter.getInstance().createAdapterException(RESOURCE_DOMAIN_EXCEPTION, ex);
		}
	}


	@Override
	public ResourceDomainValues[] adapterResourceDomainLookup(String serviceName, String resourceDomainName, String[][] values)
			throws AdapterException {

		WmFlightAwareAdapter.retrieveLogger().logInfo(RESOURCE_DOMAIN_LOOKUP);
		//WmFlightAwareAdapter.retrieveLogger().logInfo(9999,"Check point: adapterResourceDomainLookup() ");
		String[] fieldNames = null;
		String[] fieldTypes = null;

		if (resourceDomainName.equals(NOTIFICATION_NAMES_RD)) {
			return new ResourceDomainValues[] {
					new ResourceDomainValues(resourceDomainName, new String[] {"Flight Aware Sync Listener"})};
		}
		else if (resourceDomainName.equals(OUTPUT_PARAMETER_NAMES_RD)
				|| resourceDomainName.equals(OUTPUT_FIELD_TYPES_RD)) {
			fieldNames = new String[] {"FlightObject"};
			fieldTypes = new String[] {"java.lang.Object"};

			return new ResourceDomainValues[] {
					new ResourceDomainValues(OUTPUT_FIELD_TYPES_RD, fieldTypes),
					new ResourceDomainValues(OUTPUT_FIELD_NAMES_RD, fieldNames),
					new ResourceDomainValues(OUTPUT_PARAMETER_NAMES_RD, fieldNames)};

		} 

		else	
			return null;	
	}

	public String receiveFlightAllowTimeout() throws JsonSyntaxException, IOException {
		//	WmFlightAwareAdapter.retrieveLogger().logInfo(9999,"Check point: receiveFlightAllowTimeout() ");
		String message = null;

		int limit = 10; //limit number messages for testing

		//WmFlightAwareAdapter.retrieveLogger().logDebug(9999,"Check point: size of reader " + reader.ready());

		String JsonString="";

		if((message = reader.readLine()) != null) {
			WmFlightAwareAdapter.retrieveLogger().logDebug(9999,"msg: " + message + " flight Nr: "+ limit);
			JsonString=message;
		}

		return JsonString;
	}
}
