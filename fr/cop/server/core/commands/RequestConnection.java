package fr.cop.server.core.commands;

import fr.cop.common.network.params.MainParam;
import fr.cop.common.network.params.MainParamList;
import fr.cop.server.core.client_connection.ClientThread;

public class RequestConnection extends MainCommand{
	public RequestConnection() {
		super(MainCommand.TYPE_CLIENT, "request_connection", new MainParam[]{MainParamList.PLAYER_UUID, MainParamList.PLAYER_ID, MainParamList.PLAYER_PW});
	}

	@Override
	public void action(ClientThread ct) {
		ct.send("client:connect aaaabbbbaaaabbbb Gagoi");
	}
	
	@Override
	public void printHelp() {
		System.out.println("  - [" + getTotalName() + "] : This command send the UUID and the ID to the client which send the request.");
	}
}
