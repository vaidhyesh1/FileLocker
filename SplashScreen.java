package encrypter;
import java.util.concurrent.TimeUnit;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import javax.swing.*;
public class SplashScreen extends JWindow {
    static boolean isRegistered;
    private static JProgressBar progressBar = new JProgressBar();
    private static SplashScreen execute;
    private static int count;
    private static Timer timer1;
    public SplashScreen() throws MalformedURLException {
        Container container = getContentPane();
        container.setLayout(null);
        JPanel panel = new JPanel();
        panel.setBorder(new javax.swing.border.EtchedBorder());
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(10, 10, 348, 150);
        panel.setLayout(null);
        container.add(panel);

        JLabel label = new JLabel("File Locker V.1.0");
        label.setFont(new Font("Verdana", Font.CENTER_BASELINE, 20));
        label.setBounds(85, 25, 280, 30);
        label.setForeground(Color.BLACK);
        panel.add(label);
        JLabel label2 = new JLabel("Author: Team nofsgiven");
        label2.setFont(new Font("Times New Roman", Font.BOLD, 11));
        label2.setBounds(110, 60, 265, 90);
        label2.setForeground(Color.BLUE);
        panel.add(label2);
        progressBar.setMaximum(100);
        progressBar.setBounds(55, 180, 250, 15);
        container.add(progressBar);
        loadProgressBar();
        setSize(370, 215);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void loadProgressBar() {
        ActionListener al = new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                count++;
                progressBar.setValue(count);
                if (count == 100) {
                    execute.setVisible(false);
                    timer1.stop();
                }
            }
        };
        timer1 = new Timer(30, al);
        timer1.start();
    }
    public static void main(String[] args) throws Exception {
        execute = new SplashScreen();
        TimeUnit.SECONDS.sleep(3);
               Scanner in=new Scanner(System.in);
		lets n=new lets();
		n.gram();
		in.close();
		System.exit(0);
    }
};