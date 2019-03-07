package logicaJogo;

import java.util.ArrayList;
import java.util.List;

public class Logs {
	List<String> logs;
	
	public Logs() {
		logs = new ArrayList<String>();
	}

	public List<String> getLogs() {
		return logs;
	}
	public boolean adicionaLog(String sucedido) {
		return logs.add(sucedido);
	}
	
}
