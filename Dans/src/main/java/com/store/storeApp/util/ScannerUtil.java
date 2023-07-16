package com.store.storeApp.util;

import java.util.Random;
import java.util.Scanner;

public class ScannerUtil {
	public final static String generateCode(final int totalLength) 
	{
        final int leftLimit = 48; 
        final int rightLimit = 122;
        final Random random = new Random();

        final String generatedString = random.ints(leftLimit, rightLimit + 1)
          .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
          .limit(totalLength)
          .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
          .toString();

       return generatedString.toUpperCase();
    }
	
	@SuppressWarnings("resource")
	public final static String scanStr(String msg)
	{
		System.out.print(msg);
		final Scanner scanner = new Scanner(System.in);
		final String input = scanner.nextLine();
		return input;
	}
		
	@SuppressWarnings("resource")
	public final static int scanInt(String msg)
	{
		System.out.print(msg);
		final Scanner scanner = new Scanner(System.in);
		try
		{
			final int input = scanner.nextInt();
			return input;
		}
		catch (Exception e)
		{
			scanner.nextLine();
			System.out.println("Wrong input, try again!!");
			return scanInt(msg);
		}
	}
	
	@SuppressWarnings("resource")
	public final static float scanFloat(String msg)
	{
		System.out.print(msg);
		final Scanner scanner = new Scanner(System.in);
		try
		{
			final float input = scanner.nextFloat();
			return input;
		}
		catch (Exception e)
		{
			scanner.nextLine();
			System.out.println("Wrong input, try again!!");
			return scanFloat(msg);
		}
	}
}
