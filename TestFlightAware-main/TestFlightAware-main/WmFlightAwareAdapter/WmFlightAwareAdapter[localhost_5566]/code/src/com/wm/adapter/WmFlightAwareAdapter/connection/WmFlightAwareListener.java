package com.wm.adapter.WmFlightAwareAdapter.connection;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.resource.ResourceException;

import com.google.gson.JsonSyntaxException;
import com.wm.adapter.WmFlightAwareAdapter.WmFlightAwareAdapter;
import com.wm.adapter.WmFlightAwareAdapter.WmFlightAwareConstants;
import com.wm.adk.metadata.WmDescriptor;
import com.wm.adk.notification.ClusterAware;
import com.wm.adk.notification.WmConnectedListener;
import com.wm.adk.notification.WmNotification;
import com.wm.adk.error.AdapterException;

public class WmFlightAwareListener extends WmConnectedListener implements WmFlightAwareConstants, ClusterAware {


	public WmFlightAwareListener() {
		super();
	}

	@Override
	public void fillWmDescriptor(WmDescriptor d, Locale l) throws ResourceException {
		d.setDescriptions(WmFlightAwareAdapter.getInstance().getAdapterResourceBundleManager(), l);

	}

	@Override
	public void listenerShutdown() {
		// notify Flight Aware Server to switch client connection to interaction
		// mode
	//	WmFlightAwareAdapter.retrieveLogger().logDebug(9999, "Check point: listenerShutdown() ");
		try {
			((WmFlightAwareAdapterConnection) retrieveConnection()).setToListenerMode(false);
		} catch (ResourceException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void listenerStartup() throws ResourceException {
		try {
			//	WmFlightAwareAdapter.retrieveLogger().logDebug(9999, "Check point: listenerStartup() ");
			((WmFlightAwareAdapterConnection) retrieveConnection()).setToListenerMode(true);
		} catch (AdapterException | IOException e) {
			WmFlightAwareAdapter.retrieveLogger().logDebug(9999, "Cant Start up Flight Aware Listener ");
			e.printStackTrace();
		}

	}

	@Override
	public Object waitForData() throws ResourceException {
		//	WmFlightAwareAdapter.retrieveLogger().logDebug(9999, "Check point: in waitForData() ");
		List notifications = getRegisteredNotifications();
		int numberOfNotifications = notifications.size();
		//	WmFlightAwareAdapter.retrieveLogger().logDebug(9999, "Check point: waitForData() numberOfNotifications " + numberOfNotifications);
		while (numberOfNotifications > 0) {
			// Get last notification in list
			WmNotification last = (WmNotification) notifications.get(numberOfNotifications - 1);
			if (((WmNotification) last).enabled())
				break;
			numberOfNotifications--;
		}

		if (numberOfNotifications == 0) {
			WmFlightAwareAdapter.retrieveLogger().logInfo(LISTENER_NOTIFICATION_NOT_AVAILABLE,
					new String[] { this._listenerNodeName.getFullName() });
			try {
				Thread.sleep(5000);
			} catch (InterruptedException ie) {
			}
			return null;
		}

		//FlightObject output=null;
		String output=null;
		try {
			output = ((WmFlightAwareAdapterConnection) retrieveConnection()).receiveFlightAllowTimeout();
		} catch (JsonSyntaxException | IOException e) {
			e.printStackTrace();
		}
		return output;
	}

}
