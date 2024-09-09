package warshall;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Test1 {
    // Field members
    static JPanel panel = new JPanel();
    //static List<JTextField> listOfTextFields = new ArrayList<JTextField>();
    static JTextField jtb1;
    public static JTextField edges[][];
    public static Double[][] edgesValue = new Double[6][6];
    public static int rowCnt = 0, i, j;
    public static JButton addButton;


    public static void main(String[] args) {

// Construct frame
         JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setPreferredSize(new Dimension(990, 990));
        frame.setTitle("Matrix Input");
        frame.getContentPane().setBackground(new java.awt.Color(182, 202, 212));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel jlb = new JLabel("                        " + "Implementation of All Pairs Shortest Path" + "                        ", SwingConstants.CENTER);
        JLabel jlb1 = new JLabel("Enter Number Of Nodes:", SwingConstants.LEFT);
        jlb.setFont(new Font("Britannic Bold", Font.BOLD, 23));
        jlb1.setFont(new Font("Britannic Bold", Font.BOLD, 15));
        frame.add(jlb);
        frame.add(jlb1);

        jtb1 = new JTextField("", 30);
        jtb1.setFont(new Font("Britannic Bold", Font.BOLD, 20));



        // Construct button
         addButton = new JButton("Generate I/P Matrix");
        addButton.addActionListener(new ButtonListener());


        // Add button to frame

        frame.add(jtb1);
        frame.add(addButton);


        JButton addButton1 = new JButton("Apply");
        addButton1.addActionListener(new ButtonListener1());


        frame.add(addButton1);

        // Construct panel
        panel.setPreferredSize(new Dimension(600, 600));
        panel.setLayout(new GridBagLayout());




        frame.add(panel);

        // Pack frame
        frame.pack();

        // Make frame visible
        frame.setVisible(true);


    }

    public static class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {



            panel.removeAll();
            GridBagConstraints textFieldConstraints = new GridBagConstraints();


            try {
                rowCnt = Integer.parseInt(jtb1.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Input must be a number");
            }
            edges= new JTextField[rowCnt][rowCnt];

            for (i = 0; i < rowCnt; i++) {
                for (j = 0; j < rowCnt; j++) {
                    // JTextField g = new JTextField();
                    edges[i][j]=new JTextField();
                    // edges[i][j].setText("0");
                    textFieldConstraints.gridx = i;
                    textFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
                    textFieldConstraints.weightx = 0.5;
                    textFieldConstraints.insets = new Insets(20, 20, 20, 20);
                    textFieldConstraints.gridy = j;
                    textFieldConstraints.ipadx = 20;
                    textFieldConstraints.ipady = 20;


                    panel.add(edges[i][j], textFieldConstraints);

                }
            }

            panel.updateUI();


        }
    }

    public static class ButtonListener1 implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {

            for(int i = 0; i < rowCnt; i++){
                for(int j = 0; j < rowCnt; j++){

                    String num = edges[j][i].getText();

                    if(num.equals("")){
                        JOptionPane.showMessageDialog(null,"Edge field can not be Empty!");
                        break;
                    }

                    else if("inf".equals(num) || "i".equals(num)){
                        edgesValue[i][j] = Double.POSITIVE_INFINITY;
                    }

                    else if(i==j)
                    {
                        if(Integer.parseInt(num)<0)
                        {
                            JOptionPane.showMessageDialog(null,"Negative Weight Cycle Found");
                            break;
                        }
                    }


                    else{
                        try{
                            edgesValue[i][j] = Double.parseDouble(num);
                        }catch(Exception e){
                            JOptionPane.showMessageDialog(null,"One or more fields are empty!");
                            break;
                        }
                    }
                }
            }
        for(int i=0;i<rowCnt;i++)
        {
            for (int j = 0; j < rowCnt; j++)
            {
                if (i == j)
                {
                    edgesValue[i][j] = 0.0;
                    continue;
                }
                if (edgesValue[i][j] == 0 && i != j)
                {
                    edgesValue[i][j] = Double.POSITIVE_INFINITY;
                }
            }
        }

            APSP a = new APSP();

            // Print the solution
            a.floydWarshall(edgesValue);


            panel.updateUI();
        }

    }
}
