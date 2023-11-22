package wm.WmFlightAwareAdapter;
//--- <<IS-START-IMPORTS>> ---
import com.wm.adapter.WmFlightAwareAdapter.*;
import com.wm.adk.admin.AdapterAdmin;
import com.wm.app.b2b.server.ServiceException;
import com.wm.data.IData;
//--- <<IS-END-IMPORTS>> ---

public class WmFlightAwareAdapterAdmin {
    public static final void startUp (IData pipeline)
            throws ServiceException
    {
        // --- <<IS-START(startUp)>> ---
        AdapterAdmin.registerAdapter(WmFlightAwareAdapter.getInstance());
        // --- <<IS-END>> ---         
    }
    public static final void shutDown (IData pipeline)
            throws ServiceException
    {
        // --- <<IS-START(shutDown)>> ---
    	WmFlightAwareAdapter instance = WmFlightAwareAdapter.getInstance();
        instance.cleanup();
        AdapterAdmin.unregisterAdapter(instance);
        // --- <<IS-END>> ---                
    }
}
