import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Vinay Vasant More
 */
public class Client extends Frame implements KeyListener {
   TextArea ta;
    TextField tf;
    PrintWriter pw;

    public Client() {
        ta = new TextArea();
        ta.setEditable(false);
        tf = new TextField();
        tf.addKeyListener(this);

        add(ta);
        add(tf,BorderLayout.SOUTH);
        setSize(300, 300);
        setTitle("Client");
        setVisible(true);

        try {
            Socket s = new Socket("localhost" ,9999);

            pw = new PrintWriter(s.getOutputStream());

            InputStream is = s.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            while (true) {
                String str = br.readLine();
                ta.append(str + "\n");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    public static void main(String[] args) {
        new Client();
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();
        if(code == KeyEvent.VK_ENTER)
        {
            String str = tf.getText();
            str = "Client : " + str;
            System.out.println(str);
            ta.append(str + "\n");
            tf.setText("");

            pw.println(str);
            pw.flush();
        }
    }

    public void keyReleased(KeyEvent e) {
    }

}
