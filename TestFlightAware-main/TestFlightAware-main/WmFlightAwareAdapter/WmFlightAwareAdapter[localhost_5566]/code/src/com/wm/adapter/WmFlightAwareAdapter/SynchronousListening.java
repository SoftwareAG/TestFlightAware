package com.wm.adapter.WmFlightAwareAdapter;

import java.util.Locale;

import javax.resource.ResourceException;

import com.wm.adapter.WmFlightAwareAdapter.connection.FlightObject;
import com.wm.adapter.WmFlightAwareAdapter.connection.WmFlightAwareAdapterConnection;
import com.wm.adk.cci.record.WmRecord;
import com.wm.adk.cci.record.WmRecordFactory;
import com.wm.adk.error.AdapterException;
import com.wm.adk.metadata.WmTemplateDescriptor;
import com.wm.adk.notification.NotificationEvent;
import com.wm.adk.notification.NotificationResults;
import com.wm.adk.notification.SyncNotificationResults;
import com.wm.adk.notification.WmSyncListenerNotification;
import com.wm.app.b2b.server.InvokeState;
import com.wm.app.b2b.server.Service;
import com.wm.data.IData;
import com.wm.data.IDataCursor;
import com.wm.data.IDataFactory;
import com.wm.data.IDataUtil;
import com.wm.lang.ns.NSName;

public class SynchronousListening extends WmSyncListenerNotification implements WmFlightAwareConstants {



	// eventName property
	private String eventName;

	// outputParameterNames property
	private String[] outputParameterNames;

	// outputFieldNames property
	private String[] outputFieldNames;

	// outputFieldTypes property
	private String[] outputFieldTypes;

	/*
	 * Set eventName property value
	 *
	 * eventName is Sample Server service event name
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	/*
	 * Get eventName property value
	 *
	 * return Sample Server service event name
	 */
	public String getEventName() {
		return this.eventName;
	}

	/*
	 * Set outputParameterNames property value
	 *
	 * outputParameterName are Sample Server service invocation output parameter
	 * names. These parameters reflect the fully qualified structure names
	 */
	public void setOutputParameterNames(String[] outputParameterNames) {
		this.outputParameterNames = outputParameterNames;
	}

	/*
	 * Get outputParameterNames property value
	 *
	 * return Sample Server service invocation output parameter names. These
	 * parameters reflect the fully qualified structure names
	 */
	public String[] getOutputParameterNames() {
		return this.outputParameterNames;
	}

	/*
	 * Set outputFieldNames property value
	 *
	 * outputFieldNames are Sample Server service invocation output field names.
	 * These names are used to build the output signature
	 */
	public void setOutputFieldNames(String[] outputFieldNames) {
		this.outputFieldNames = outputFieldNames;
	}

	/*
	 * Get outputFieldNames property value
	 *
	 * return Sample Server service invocation output field names. These names
	 * are used to build the output signature
	 */
	public String[] getOutputFieldNames() {
		return this.outputFieldNames;
	}

	/*
	 * Set outputFieldTypes property value
	 *
	 * outputFieldTypes are Sample Server service invocation output field types.
	 * These types are used to build the output signature
	 */
	public void setOutputFieldTypes(String[] outputFieldTypes) {
		this.outputFieldTypes = outputFieldTypes;
	}

	/*
	 * Get outputFieldTypes property value
	 *
	 * return Sample Server service invocation output field types. These types
	 * are used to build the output signature
	 */
	public String[] getOutputFieldTypes() {
		return this.outputFieldTypes;
	}
	@Override
	public boolean supports(Object o) throws ResourceException {
		//WmFlightAwareAdapter.retrieveLogger().logDebug(9999,"Check point: supports_SyncListener() ");
		return true;

	}

	@Override
	public void fillWmTemplateDescriptor(WmTemplateDescriptor d, Locale l) throws ResourceException {
		// create a group, essentially a tab in Designer
		//WmFlightAwareAdapter.retrieveLogger().logDebug(9999,"Check point: fillWmTemplateDescriptor_SyncListener() ");
		d.createGroup(GROUP_SYNC_LISTENING,
				new String[] { GROUP_MEMBER_EVENT_NAME, GROUP_MEMBER_OUTPUT_PARAMETER_NAMES,
						GROUP_MEMBER_OUTPUT_FIELD_NAMES, GROUP_MEMBER_OUTPUT_FIELD_TYPES });

		// eventName property is required and must be set
		d.setRequired(GROUP_MEMBER_EVENT_NAME);

		// list output parameters, field name and data types in table format
		d.createFieldMap(new String[] { GROUP_MEMBER_OUTPUT_PARAMETER_NAMES, GROUP_MEMBER_OUTPUT_FIELD_NAMES,
				GROUP_MEMBER_OUTPUT_FIELD_TYPES }, false);

		// tie output parameter and data types together for resource domain
		// lookup
		d.createTuple(new String[] { GROUP_MEMBER_OUTPUT_PARAMETER_NAMES, GROUP_MEMBER_OUTPUT_FIELD_TYPES });

		// set resource domain for the eventName property
		d.setResourceDomain(GROUP_MEMBER_EVENT_NAME, NOTIFICATION_NAMES_RD, null);

		// set resource domain for the outputParameterNames property
		// the resource domain look up has a dependency on the eventName
		// property value
		d.setResourceDomain(GROUP_MEMBER_OUTPUT_PARAMETER_NAMES, OUTPUT_PARAMETER_NAMES_RD,
				new String[] { GROUP_MEMBER_EVENT_NAME });

		// set resource domain for the outputFieldTypes property
		// the resource domain look up has a dependency on the eventName
		// property value
		d.setResourceDomain(GROUP_MEMBER_OUTPUT_FIELD_TYPES, OUTPUT_FIELD_TYPES_RD,
				new String[] { GROUP_MEMBER_EVENT_NAME });

		// set resource domain for the outputFieldNames property
		// outputFieldNames and outputFieldTypes are used to build the output
		// signature
		// outputParameterNames is used as suggested names for outputFieldNames
		d.setResourceDomain(GROUP_MEMBER_OUTPUT_FIELD_NAMES, WmTemplateDescriptor.OUTPUT_FIELD_NAMES,
				new String[] { GROUP_MEMBER_OUTPUT_PARAMETER_NAMES, GROUP_MEMBER_OUTPUT_FIELD_TYPES });

		// Retrieves the i18n metadata information from the resource bundle and
		// replaces he non-localized metadata.
		// The metadata that needs to be internationalized (the parameter
		// display
		// name, description, group name, etc.) will populate the administrative
		// interface, Adapter Service Editor, or Adapter Notification Editor.
		d.setDescriptions(WmFlightAwareAdapter.getInstance().getAdapterResourceBundleManager(), l);

	}

	/*
	 * Process the notification event and publishes them.
	 *
	 * an AdapterException is thrown if it encounters any problem return an
	 * AsyncNotificationResults
	 */
	public NotificationResults runNotification(NotificationEvent event) throws ResourceException {

		//		WmFlightAwareAdapter.retrieveLogger().logDebug(9999,"Check point: runNotification_SyncListener() ");
		SyncNotificationResults snr = null;
		WmRecord output = null;
		try {
			//FlightObject eventDoc = (FlightObject) event.getData();
			output = WmRecordFactory.getFactory().createWmRecord("NotificationOutput");
			IData pubdoc = IDataFactory.create();
			IDataCursor mcursor = pubdoc.getCursor(); 
			IDataUtil.put(mcursor, "FlightObject", (java.lang.Object)event.getData());
			InvokeState is = InvokeState.getCurrentState();
			IData document = null;
			if(is != null) {
				IData dt = IDataFactory.create(new Object[][]{
					{"jsonString",(java.lang.Object)event.getData()},
					{"a", "a"}});

				is.setPipeline(dt);

				NSName nsname = NSName.create("pub.json:jsonStringToDocument");
				try {
					IData odt = Service.doInvoke( nsname, dt);
					IDataCursor odtc = odt.getCursor();
					document = IDataUtil.getIData(odtc, "document");
					IDataUtil.put(mcursor, "FlightObject", document);
					odtc.destroy();
					IDataUtil.put(mcursor, "FlightObject", dt);
				} catch(Exception e) {
				}


			}
			//output.setIData(outputRecord);
			output.setIData(pubdoc);
			mcursor.destroy();
		} catch (Exception e) {
			throw WmFlightAwareAdapter.getInstance().createAdapterException(LISTENER_NOTIFICATION_EXCEPTION, e);
		}

		try {
			// doNotify(output);
			snr = invokeService(output);
			WmFlightAwareAdapter.retrieveLogger().logInfo(SYNCHRONOUS_NOTIFICATION, new String[] { "End invoking service..." });
		} catch (Throwable t) {
			snr = new SyncNotificationResults(this._notificationNodeName.getFullName(), false, t);
			snr.setHadError(true);
		}

		return snr;
	}

	public SyncNotificationResults invokeService(WmRecord rec) throws AdapterException {
		//WmFlightAwareAdapter.retrieveLogger().logInfo(SYNCHRONOUS_NOTIFICATION, new String[] { "Start invoking service..." });
		//	WmFlightAwareAdapter.retrieveLogger().logDebug(9999,"Check point: invokeService_SyncListener() ");
		IDataCursor idc = null;
		try {
			idc = rec.getCursor();
			if ((idc != null) && (idc.first())) {
				do {
					String key = idc.getKey();
					Object o = idc.getValue();
					if (o == null)
						WmFlightAwareAdapter.retrieveLogger().logInfo(SYNCHRONOUS_NOTIFICATION, new String[] { "key=" + key, "is null" });
					else
						WmFlightAwareAdapter.retrieveLogger().logInfo(SYNCHRONOUS_NOTIFICATION,
								new String[] { "key=" + key, "value=" + o.toString() });
				} while (idc.next());
			} else
				WmFlightAwareAdapter.retrieveLogger().logInfo(SYNCHRONOUS_NOTIFICATION, new String[] { "idc is null, input=" + rec });
		} catch (Exception ex) {
			WmFlightAwareAdapter.retrieveLogger().logInfo(LISTENER_NOTIFICATION_EXCEPTION, new String[] { ex.getLocalizedMessage() });
		} finally {
			if (idc != null)
				idc.destroy();
		}
		return super.invokeService(rec);
	}


}
