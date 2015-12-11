package fr.cop.server.core.commands;

import fr.cop.server.core.commands.params.MainParam;
import fr.cop.server.core.commands.params.MainParamList;

public class RequestConnection extends MainCommand{
	public RequestConnection() {
		super("client", "request_connection", new MainParam[]{MainParamList.PLAYER_UUID, MainParamList.PLAYER_ID, MainParamList.PLAYER_PW});
	}

}
