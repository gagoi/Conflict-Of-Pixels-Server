package fr.cop.server.core.commands;

import fr.cop.server.core.client_connection.ClientThread;
import fr.cop.server.core.commands.params.MainParam;
import fr.cop.server.core.commands.params.MainParamList;

public class RequestConnection extends MainCommand{
	public RequestConnection() {
		super(MainCommand.TYPE_CLIENT, "request_connection", new MainParam[]{MainParamList.PLAYER_UUID, MainParamList.PLAYER_ID, MainParamList.PLAYER_PW});
	}

	@Override
	public void action(ClientThread ct) {
		ct.send("client:connect TOTO");
	}
}
