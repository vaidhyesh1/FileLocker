package encrypter;
import java.io.*;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Scanner;
import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
public class encrypter
{
	Scanner input=new Scanner(System.in);
	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	public static String randomAlphaNumeric(int count) {
	StringBuilder builder = new StringBuilder();
	while (count-- != 0) {
	int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
	builder.append(ALPHA_NUMERIC_STRING.charAt(character));
	}
	return builder.toString();
	}
	 static String s;
	public static String key;
	 
    Cipher ecipher;
    Cipher dcipher;
    byte[] salt = {
        (byte) 0xB7, (byte) 0xC5, (byte) 0xE8, (byte) 0x12,
        (byte) 0x45, (byte) 0x28, (byte) 0xA1, (byte) 0x98
    };
    int iterationCount = 19;
    public String encrypt(String secretKey, String plainText) 
            throws NoSuchAlgorithmException, 
            InvalidKeySpecException, 
            NoSuchPaddingException, 
            InvalidKeyException,
            InvalidAlgorithmParameterException, 
            UnsupportedEncodingException, 
            IllegalBlockSizeException, 
            BadPaddingException{
        KeySpec keySpec =new PBEKeySpec(secretKey.toCharArray(), salt,iterationCount);
        SecretKey key =SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);        
        AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt,iterationCount);
        ecipher = Cipher.getInstance(key.getAlgorithm());
        ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);      
        String charSet="UTF-8";       
        byte[] in = plainText.getBytes(charSet);
        byte[] out = ecipher.doFinal(in);
        String encStr=new sun.misc.BASE64Encoder().encode(out);
        return encStr;
    }   
    public static void mad() throws Exception{
    	System.out.println("Asymmetric Key Based Encryption and Authentication for File Sharing");
    	Scanner input=new Scanner(System.in);
        decrypter whatever=new decrypter(s);
        System.out.println("Do you want to Encrypt/Decrypt?");
        char ee=input.next().charAt(0);
        if(ee=='E'||ee=='e'){
       try{
        FileReader in=new FileReader(s);
        int c;
        String plain="";
        while((c=in.read())!=-1)
        {
        	plain+=""+(char)c;
        }
        in.close();
        BufferedWriter sa = new BufferedWriter(new FileWriter(s));
        (new File(s)).delete();
        sa.close();
        key=randomAlphaNumeric(8);
        String filename = "password.txt";
		String workingDirectory = System.getProperty("user.home")+"/Desktop";
		String absoluteFilePath = "";
		absoluteFilePath = workingDirectory + File.separator + filename;
		BufferedWriter sc=new BufferedWriter(new FileWriter(absoluteFilePath));
		sc.write(key);
		sc.close();
        //System.out.println(key);
        String enc=whatever.encrypt(key, plain);
        SendEmail sah=new SendEmail();
        System.out.println("Enter the gmail id:");
        String sahb=input.next();
        sah.send(sahb,key);
        System.out.println("Encryption successful!");
        BufferedWriter sb = new BufferedWriter(new FileWriter(s));
        sb.write(enc);
        sb.close();
       System.exit(0);       
       }
       catch(FileNotFoundException e)
       {
    	   e.getMessage();
       }
        }
        else if(ee=='D'||ee=='d')
        { 
        	FileReader sd=new FileReader(s);
        	int k;
        	String jl="";
        	while((k=sd.read())!=-1)
        		jl+=""+(char)k;
        	FileWriter out=new FileWriter(s);
        	String filename1 = "password.txt";
    		String workingDirectory1 = System.getProperty("user.home")+"/Desktop";
    		String absoluteFilePath1 = "";
    		absoluteFilePath1 = workingDirectory1 + File.separator + filename1;
    		FileInputStream fis = new FileInputStream(absoluteFilePath1);
       		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
       		key=br.readLine();
        	whatever.decrypts(jl,out);
        	sd.close();
        	out.close();
        }
        else
        {
        	System.out.println("Invalid character!");
        }
        input.close();
      }
}