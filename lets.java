package encrypter;
import java.awt.Frame;
import java.io.File;
import java.util.Scanner;
import javax.swing.JFileChooser;
public class lets extends JFileChooser {
 public void gram() throws Exception{
	JFileChooser fileChooser = new JFileChooser();
	String workingDirectory3 = System.getProperty("user.home")+"/Desktop";
	fileChooser.setCurrentDirectory(new File(workingDirectory3));
	int result = fileChooser.showOpenDialog(this);
	if (result == JFileChooser.APPROVE_OPTION) {
	    File selectedFile = fileChooser.getSelectedFile();
		File ffile = selectedFile;
		String uu="";
uu+=ffile;
decrypter n=new decrypter(uu);
	n.mad();
}}}
