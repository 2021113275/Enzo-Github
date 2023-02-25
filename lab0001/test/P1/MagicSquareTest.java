package P1;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class MagicSquareTest {
	
	/*
	 * Testing strategy
	 * ���ն����ļ����֣�ֻ��Ҫ������ļ������뼴��
	 */
	@Test
	public void isLegalMagicSquaretest1() throws IOException
	{
		assertEquals(true, MagicSquare.isLegalMagicSquare("src/P1/txt/1.txt"));
		assertEquals(true, MagicSquare.isLegalMagicSquare("src/P1/txt/2.txt"));
		assertEquals(false, MagicSquare.isLegalMagicSquare("src/P1/txt/3.txt"));
		assertEquals(false, MagicSquare.isLegalMagicSquare("src/P1/txt/4.txt"));
		assertEquals(false, MagicSquare.isLegalMagicSquare("src/P1/txt/5.txt"));
	}
	
	
	@Test
	public void isLegalMagicSquaretest2() throws IOException{
		int[] array = {4,-3,12,-6};
	    for(int i=0;i<4;i++)
	    {
	    	String filename = "src/p1/txt/6.txt";
	    	MagicSquare.writeSquare(MagicSquare.generateMagicSquare(array[i]), filename);
	    	assertEquals(false, MagicSquare.isLegalMagicSquare(filename));
	    }
	}
	
	@Test
	public void isLegalMagicSquaretest3() throws IOException{
		int[] array = {7,5,13,9};
	    for(int i=0;i<4;i++)
	    {
	    	String filename = "src/p1/txt/6.txt";
	    	MagicSquare.writeSquare(MagicSquare.generateMagicSquare(array[i]), filename);
	    	assertEquals(false, MagicSquare.isLegalMagicSquare(filename));
	    }
	}
}
