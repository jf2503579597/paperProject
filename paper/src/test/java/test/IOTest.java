package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class IOTest {
	public static void main(String[] args) {
		String s = "12345678901asdfghjjdsadasdasfasdasd";
		byte[] bytes = new byte[1024];
		try {
			FileInputStream fileInputStream = new FileInputStream(new File("b.txt"));
			int result = fileInputStream.read(bytes, 0, 1);
			System.out.println(result);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
