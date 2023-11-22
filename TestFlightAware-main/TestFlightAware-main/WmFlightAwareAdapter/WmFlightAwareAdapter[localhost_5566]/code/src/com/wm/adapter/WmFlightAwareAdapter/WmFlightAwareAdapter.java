package com.wm.adapter.WmFlightAwareAdapter;

import java.util.Locale;

import com.wm.adapter.WmFlightAwareAdapter.connection.WmFlightAwareAdapterConnectionFactory;
import com.wm.adapter.WmFlightAwareAdapter.connection.WmFlightAwareListener;
import com.wm.adk.WmAdapter;
import com.wm.adk.error.AdapterException;
import com.wm.adk.info.AdapterTypeInfo;
import com.wm.adk.log.ARTLogger;

public class WmFlightAwareAdapter extends WmAdapter implements WmFlightAwareConstants {

	private static ARTLogger _logger;
	private static WmFlightAwareAdapter _instance = null;
	
	public WmFlightAwareAdapter() throws AdapterException {
		super();
	}

	public static WmFlightAwareAdapter getInstance() {
        synchronized(WmFlightAwareAdapter.class) {
            if (_instance != null) {
                return _instance;
            }
            
            try {
                _instance = new WmFlightAwareAdapter();
                return _instance;
            } catch (Throwable t) {
                t.printStackTrace();
                return null;
            }
        }
	}

	public static ARTLogger retrieveLogger()
	    {
	        return _logger;
	    }	
	
	public void cleanup() {
        if (_logger != null)
            _logger.close();
		
	}

	public void fillAdapterTypeInfo(AdapterTypeInfo info, Locale locale) {
		//WmFlightAwareAdapter.retrieveLogger().logDebug(9999,"Check point: fillAdapterTypeInfo() ");
		info.addConnectionFactory(WmFlightAwareAdapterConnectionFactory.class.getName());
		info.addListenerType(WmFlightAwareListener.class.getName());
		info.addNotificationType(SynchronousListening.class.getName());
		info.addNotificationType(AsyncListening.class.getName());		
	}

	public String getAdapterJCASpecVersion() {
		return ADAPTER_JCA_VERSION;
	}


	public int getAdapterMajorCode() {
		return ADAPTER_MAJOR_CODE;
	}


	public String getAdapterName() {
		return ADAPTER_NAME;
	}


	public String getAdapterResourceBundleName() {
		return ADAPTER_SOURCE_BUNDLE_NAME;
	}


	public String getAdapterVersion() {
		return ADAPTER_VERSION;
	}


	public void initialize() throws AdapterException {
		_logger = new ARTLogger(getAdapterMajorCode(), 
                getAdapterName(), 
                getAdapterResourceBundleName());
		
	}

}
