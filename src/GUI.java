import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

import static java.lang.Integer.parseInt;

public class GUI implements EventListener,ListSelectionListener {
    JFrame frame;
    JPanel panel,panel2;
    JTable j,j2;

    JTextField text1,text2,text3,text4;
    private DefaultTableModel tablemodel;
    String[][] data = new String[10][4];
    String[][] arr;
    String[][] invoiceDetailsArr = new String[10][5];
    Invoices in1;
    String id,date,name;

    String[] columnNames,rowDetails;
    public GUI() {
        try{
        frame = new JFrame();
        frame.setLayout(new GridLayout(0,2));
        panel = new JPanel();

        panel.setLayout(new GridLayout(0, 1));

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Invoice Generator");
        frame.pack();
        frame.setVisible(true);

        columnNames = new String[] { "InvoiceId" , "Invoice Date", "Customer Name" , "Total"};

        invoicDetails details = new invoicDetails();
        arr = details.getInvoiceDetail();

        in1 = new Invoices();
        data = in1.getInvoices(arr);

        tablemodel = new DefaultTableModel(data,columnNames);
        j = new JTable(tablemodel);
        j.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        j.setBounds(0, 0, 500, 500);
        JScrollPane sp = new JScrollPane(j);
        j.setRowSelectionAllowed(true);

        ListSelectionModel listModel = j.getSelectionModel();
        listModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listModel.addListSelectionListener((ListSelectionListener) this);

        JButton save = new JButton("Create New Invoice");
        save.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                if(panel2 != null){
                    frame.remove(panel2);
                }
                // check the selected row first
                panel2 = new JPanel();
                panel2.setLayout(new GridLayout(5, 1));
                frame.add(panel2, BorderLayout.CENTER);


                JLabel label1 = new JLabel("Invoice Number");
                text1 = new JTextField("");
                text1.disable();
                JLabel label2 = new JLabel("Invoice Date");
                text2 = new JTextField("");
                JLabel label3 = new JLabel("Customer Name");
                text3 = new JTextField("");
                JLabel label4 = new JLabel("Invoice Total");
                text4 = new JTextField("");



                panel2.add(label1);
                panel2.add(text1);
                panel2.add(label2);
                panel2.add(text2);
                panel2.add(label3);
                panel2.add(text3);
                panel2.add(label4);
                panel2.add(text4);

                rowDetails = new String[] { "InvoiceId" , "Item Name", "Item Price" , "Count","Item Total"};
                String[][] data2 = new String[10][5];
                j2 = new JTable(data2, rowDetails);
                j2.setBounds(0, 0, 500, 500);
                JScrollPane sp2 = new JScrollPane(j2);
                JButton update = new JButton("Save");
                update.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent ae)
                    {
                        id =  text1.getText();
                        date = text2.getText();
                        name = text3.getText();
                        System.out.println("id------"+parseInt(in1.getLastId()));
                        //create new invoice
                        id = String.valueOf(parseInt(in1.getLastId()) + 1);
                        in1.setId(id);
                        in1.setDate(date);
                        in1.setName(name);
                        in1.storeInvoice();
                        JOptionPane.showMessageDialog(null, "Saved successfully");

                    }
                });
                JButton cancel = new JButton("Cancel");
                cancel.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent ae)
                    {
                        if(panel2 != null){
                            frame.remove(panel2);
                            frame.repaint();
                        }
                        JOptionPane.showMessageDialog(null, "cancel operation!!");

                    }
                });

                panel2.add(sp2);
                panel2.add(update);
                panel2.add(cancel);
                frame.setVisible(true);
            }
        });
        JButton delete = new JButton("Delete");
        delete.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                int sel;
                // check the selected row first
                if(j.getSelectedRow() != -1)
                {
                    sel = j.getSelectedRow();
                    TableModel tm = j.getModel();
                    String value = tm.getValueAt(sel, 0).toString();
                    in1.search(value);
                    // remove the selected row from the table model
                    tablemodel.removeRow(j.getSelectedRow());

                    JOptionPane.showMessageDialog(null, j.getSelectedRow());
                }
            }
        });
        save.setBounds(0,40,30,30);
        delete.setBounds(50,100,30,30);
        panel.add(sp);
        panel.add(save);
        panel.add(delete);


//        panel2.add(sp2);

        // Frame Size
        frame.setSize(1000, 1000);
        // Frame Visible = true
        frame.setVisible(true);
//            System.out.println("selected"+row);
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(panel2 != null){
            frame.remove(panel2);
        }
        String[][] data2 = new String[10][5];
        int sel;
        if (!e.getValueIsAdjusting())
        {
            sel = j.getSelectedRow();
            TableModel tm = j.getModel();


            panel2 = new JPanel();
            panel2.setLayout(new GridLayout(5, 1));
            frame.add(panel2, BorderLayout.CENTER);


            JLabel label1 = new JLabel("Invoice Number");
            text1 = new JTextField(tm.getValueAt(sel, 0).toString());
            JLabel label2 = new JLabel("Invoice Date");
            text2 = new JTextField(tm.getValueAt(sel, 1).toString());
            JLabel label3 = new JLabel("Customer Name");
            text3 = new JTextField(tm.getValueAt(sel, 2).toString());
            JLabel label4 = new JLabel("Invoice Total");
            text4 = new JTextField(tm.getValueAt(sel, 3).toString());



            panel2.add(label1);
            panel2.add(text1);
            panel2.add(label2);
            panel2.add(text2);
            panel2.add(label3);
            panel2.add(text3);
            panel2.add(label4);
            panel2.add(text4);

            rowDetails = new String[] { "InvoiceId" , "Item Name", "Item Price" , "Count","Item Total"};
            int k=0;
            for(int i=0;i<arr.length;i++) {
                if(arr[i][0] != null) {
                    if (arr[i][0].equals(tm.getValueAt(sel, 0).toString())) {
                        data2[k][0] = arr[i][0];
                        data2[k][1] = arr[i][1];
                        data2[k][2] = arr[i][2];
                        data2[k][3] = arr[i][3];
                        data2[k][4] = String.valueOf(parseInt(arr[i][2]) * parseInt(arr[i][3]));
                        k++;
                    }
                }

            }

            j2 = new JTable(data2, rowDetails);
            j2.setBounds(0, 0, 500, 500);
            JScrollPane sp2 = new JScrollPane(j2);
            JButton update = new JButton("Save");
            JButton cancel = new JButton("Cancel");
            update.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae)
                {
                    id =  text1.getText();
                    date = text2.getText();
                    name = text3.getText();
                //update invoice
                    in1.setId(id);
                    in1.setDate(date);
                    in1.setName(name);
                    in1.storeInvoice();
                    JOptionPane.showMessageDialog(null, "Saved successfully");

                }
            });
            cancel.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae)
                {
                    if(panel2 != null) {
                        frame.remove(panel2);
                        frame.repaint();
                    }
                    JOptionPane.showMessageDialog(null, "cancel operation");

                }
            });
            panel2.add(sp2);
            panel2.add(update);
            panel2.add(cancel);
            frame.setVisible(true);

//            if (sel.length > 0)
//            {
//                for (int i=0; i < 4; i++) {
//                    // get data from JTable
//                    TableModel tm = j.getModel();
//                    value = tm.getValueAt(sel[0],i);
//                    System.out.print(value + " ");
//                }
//                System.out.println();
//            }
        }
    }

//    @Override
//    public void tableChanged(TableModelEvent e) {
//        int sel;
////        Object invoiceId,invoiceDate,customerName,total;
//        if (e.getSource()) {
//            sel = j.getSelectedRow();
//            TableModel tm = j.getModel();
//            text2 = new JTextField(tm.getValueAt(sel, 1).toString());
//            text3 = new JTextField(tm.getValueAt(sel, 2).toString());
//            text4 = new JTextField(tm.getValueAt(sel, 3).toString());
//            System.out.print(text2 + " ");
//        }
//    }
}
