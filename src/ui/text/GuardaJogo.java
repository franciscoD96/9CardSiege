package ui.text;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import logicaJogo.ModelGame;

public class GuardaJogo {

    static String nomeFicheiro = "JogoGuardado.txt";
	
	public static void guardarJogo(ModelGame este) {

		FileOutputStream fout = null;
		ObjectOutputStream oos = null;

		try {

			fout = new FileOutputStream("JogoGuardado.txt");
			oos = new ObjectOutputStream(fout);
			oos.writeObject(este);
			System.out.println("Jogo Guardado com sucesso!");

		} catch (Exception ex) {

			ex.printStackTrace();

		} finally {

			if (fout != null) {
				try {
					fout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static ModelGame carregarJogo() {
		FileInputStream fin;
		ObjectInputStream oin;
		ModelGame carregado = null;
		
		try {
			fin = new FileInputStream("JogoGuardado.txt");
			oin = new ObjectInputStream(fin);
			carregado = (ModelGame) oin.readObject();
			oin.close();
			fin.close();
		} catch (IOException e) {
			System.out.println("Erro no carregamento do jogo!");
		} catch (ClassNotFoundException e) {
			System.out.println("Erro no carregamento do jogo!");
			e.printStackTrace();
		}
		
		return carregado;
	}
	
}
