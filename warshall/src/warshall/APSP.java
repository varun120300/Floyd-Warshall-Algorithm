package warshall;

import java.awt.*;
import java.lang.*;
import javax.swing.*;

import static warshall.Test1.rowCnt;


public class APSP
{
    static String[] columns = new String[Test1.rowCnt];
    static Object [][] data= new Object[Test1.rowCnt][Test1.rowCnt];

    void floydWarshall(Double graph[][])
    {
        Double dist[][] = new Double[Test1.rowCnt][Test1.rowCnt];
        int i, j, k;

        for (i = 0; i < rowCnt; i++)
            for (j = 0; j < rowCnt; j++)
                dist[i][j] = graph[i][j];

        for (k = 0; k < rowCnt; k++)
        {
            // Pick all vertices as source one by one
            for (i = 0; i < rowCnt; i++)
            {
                // Pick all vertices as destination for the
                // above picked source
                for (j = 0; j < rowCnt; j++)
                {
                    // If vertex k is on the shortest path from
                    // i to j, then update the value of dist[i][j]
                    if (dist[i][k] + dist[k][j] < dist[i][j])
                    {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }

                for(int l=0;i<rowCnt;i++)
                {
                    if(dist[l][l]<0)
                    {
                        JOptionPane.showMessageDialog(null,"Negative Weight Cycle Found");
                        break;
                    }
                }
            }
        }

        // Print the shortest distance matrix
        //printSolution(dist);
        display(dist);
    }

    void display(Double dist[][]) {

        for (int i = 0; i < rowCnt; i++) {
            columns[i] = "col" + " " + (i + 1);

        }

        for (int i = 0; i< rowCnt; ++i)
        {
            for (int j = 0; j< rowCnt; ++j)
            {
                if (dist[i][j]==Double.POSITIVE_INFINITY)
                {
                    data[i][j]="∞";
                }
                else
                    data[i][j]=/*(int)Math.round*/(dist[i][j]);

            }
        }

        JFrame frame= new JFrame("Output of in matrix form");
        JLabel label = new JLabel("The following matrix shows the shortest distances between every pair of vertices");
        label.setFont(new Font("Arial",Font.TRUETYPE_FONT,24));

        JTable table = new JTable(data,columns);
        frame.add(table);
        frame.add(new JScrollPane(table));
       table.setRowHeight(table.getRowHeight() + 50);
        table.setFont(new Font("Britannic Bold",Font.BOLD,22));
        table.setForeground(new java.awt.Color(236, 28, 28));





        frame.getContentPane().add(label,BorderLayout.PAGE_START);

        frame.setTitle("Output of in matrix form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        table.updateUI();
    }
    // Driver program to test above function

}
