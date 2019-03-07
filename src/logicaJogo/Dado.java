package logicaJogo;

import java.io.Serializable;

class Dado implements Serializable{
	static int face;
	public static int lancamento() {
		face = (int)(Math.random()*6) + 1;
		return face;
	}
	public static int getUltimo() { return face; }
}