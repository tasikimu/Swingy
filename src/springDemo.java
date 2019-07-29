import javax.swing.*;
import java.awt.*;

public class springDemo extends JFrame {
    public springDemo(){
        Container container = new Container();
        SpringLayout layout = new SpringLayout();
        container.setLayout(layout);
        container.setSize(200, 200);
        container.add(new JLabel("Label: "));
        container.add(new JTextField("Text field", 15));

        pack();
        setVisible(true);
    }
}
