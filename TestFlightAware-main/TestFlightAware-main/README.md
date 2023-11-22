# TestFlightAware
Both WmFlightAdapter Package and Designer Project have been uploaded. 


Integrate FlightAware-Firehose Streaming API to webMethods Integration Server.

Capabilities:

+ Synchronous  Listening
- Asynchronous  Listening

# Create Connection

1. Choose Package & Folder Name and Connection Name. 
2. use the username and password (provided by flightaware email)
3. Airport filter can be added.
4. Set the connection pool parameters (!read below!)

!Warning!

For trial accounts, FlightAware Server limits user access by 1. 
In that case, you must set 1 to maximum pool size.

![image](https://media.github.softwareag.com/user/3346/files/0cfcd943-3199-4b0a-bd33-d4110039c5d5)

# Troubleshooting #

If you are getting the following error, when you enable listener notification.
"[ART.116.3008] Adapter Runtime (Notification): Error during notification event. Error: flightaware_test:FA_Async_Not. [ART.118.5053] Adapter Runtime (Connection): Unable to get a connection to resource flightaware_test:FlightAwareConnectionSid. 
A connection was not available for request in pool flightaware_test:FlightAwareConnectionSid, error code: 107"

Please follow the following steps to fix the problem:

1. Disable Listener
2. Enable Listener Notification
3. Enable the Listener

![image](https://media.github.softwareag.com/user/3346/files/320e48dd-ea86-4111-b062-244a662c9d53)






