package encrypter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Scanner;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
public class decrypter extends encrypter{
	public decrypter(String s)
	{
		this.s=s;
	}
	 public String decrypt(String secretKey, String encryptedText)
		     throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException,InvalidAlgorithmParameterException, UnsupportedEncodingException,  IllegalBlockSizeException, BadPaddingException,  IOException{
		        KeySpec keySpec = new PBEKeySpec(secretKey.toCharArray(), salt, iterationCount);
		        SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);        
		        AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
		        dcipher=Cipher.getInstance(key.getAlgorithm());
		        dcipher.init(Cipher.DECRYPT_MODE, key,paramSpec);
		        byte[] enc = new sun.misc.BASE64Decoder().decodeBuffer(encryptedText);
		        byte[] utf8 = dcipher.doFinal(enc);
		        String charSet="UTF-8";     
		        String plainStr = new String(utf8, charSet);
		        return plainStr;
		    }   
	 void decrypts(String enc,FileWriter out) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, IOException
	 {
		 Scanner input=new Scanner(System.in);
		 System.out.println("Enter the password:");
		 String y="";
		 int count2=1;
		 while(!key.equals(y)&&count2<=5){
	         y=input.next();
	        if(key.equals(y)){
	        String plainAfter=decrypt(key, enc);
	        System.out.println("Decryption successful!");
	        out.write("");
	        out.write(plainAfter);
	        out.close();
	        }
	        else
	        {
	        	if(count2<5)
	        	System.out.println("Wrong Password try again! "+(5-count2)+" try/tries left!");
	        	else
	        	{
	        		System.out.println("Sorry the file will be cleared of the contents!");	
	        	}
	        }
        	count2++;
	 }
		 input.close();
		 }
}
