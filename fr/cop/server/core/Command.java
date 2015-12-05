package fr.cop.server.core;

import fr.cop.common.Game;
import fr.cop.common.Param;

public class Command {

	private String name;
	private Param[] params;

	public static Command[] commands = { new Command("game:start_timer") {
		public void use() {
			super.use();
		};
	}, new Command("game:stop_timer") {
		public void use() {
			super.use();
		};
	}, new Command("game:reset_timer") {
		public void use() {
			super.use();
		};
	}, new Command("game:send_money") {
		public void use() {
			super.use();
		};
	}, new Command("game:send_mmr") {
		public void use() {
			super.use();
		};
	}, new Command("game:buy_item", Param.ITEM_ID) {
		public void use() {
			super.use();
		};
	}, new Command("client:request_connection", Param.PLAYER_UUID) {
		public void use() {
			super.use();
		};
	}

	};

	public Command(String name, Param[] params) {
		this.name = name;
		this.params = params;
	}

	public Command(String name, Param[] params, boolean clientSide) {
		this.name = name;
		this.params = params;
	}

	public Command(String name) {
		this.name = name;
		this.params = null;
	}

	public Command(String name, Param param) {
		this.name = name;
		this.params = new Param[] { param };
	}

	public boolean test(String input) {
		String[] values = input.split(" ");

		if (values[0].equals(name)) {
			if (params == null) return true;
			if (params.length == values.length - 1) {
				for (int i = 1; i < values.length; i++) {
					if (!params[i - 1].test(values[i])) return false;
				}
				return true;
			}
		}
		return false;
	}

	public void use() {
		Game.logger.logTxt(name, "Command used.");
	}

}
