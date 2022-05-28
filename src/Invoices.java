import java.io.*;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Invoices {
    public String invoiceId;
    public String invoiceDate;
    public String customerName;
    public String total;
    private String lastId;

    String[][] data = new String[10][4];

    public Invoices(){}

    public void setId(String id){
        this.invoiceId = id;
    }

    public String getId(){
        return invoiceId;
    }

    public void setDate(String date){
        this.invoiceDate = date;
    }

    public String getInvoiceDate(){
        return invoiceDate;
    }

    public void setName(String name){
        this.customerName = name;
    }

    public String getName(){
        return customerName;
    }

    public String[][] getInvoices(String[][] arr) {
        try {
            File myObj = new File("InvoiceHeader.csv");
            Scanner myReader = new Scanner(myObj);

            invoicDetails detail = new invoicDetails();
            int i = 0;
            while (myReader.hasNextLine()) {
                // use data here to display in table
                String line = myReader.nextLine();
                String[] res = line.split(",");
                data[i][0] = res[0];
                data[i][1] = res[1];
                data[i][2] = res[2];

                int total = detail.getInvoiceTotal(data[i][0],arr);

                data[i][3] = String.valueOf(total);
            System.out.println(data[i][0]);
                i++;
            }
            myReader.close();
        }catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return data;
    }

    public String getLastId(){
        try {
            File myObj = new File("InvoiceHeader.csv");
            Scanner myReader = new Scanner(myObj);

            invoicDetails detail = new invoicDetails();
            int i = 0;
            while (myReader.hasNextLine()) {
                // use data here to display in table
                String line = myReader.nextLine();
                String[] res = line.split(",");
                data[i][0] = res[0];
                data[i][1] = res[1];
                data[i][2] = res[2];
                System.out.println(data[i][0]);
                i++;
                lastId = res[0];
            }
            myReader.close();
        }catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return lastId;
    }
    public void storeInvoice(){
        try {

            System.out.println("Hello");
            BufferedWriter myWriter = new BufferedWriter(new FileWriter("InvoiceHeader.csv", true));
            myWriter.write(invoiceId);
            myWriter.write(",");
            myWriter.write(invoiceDate);
            myWriter.write(",");
            myWriter.write(customerName);
            myWriter.write("\n");

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void search (String s)
    {
        String buffer = "";
        try {

            Scanner scan = new Scanner (new File ("InvoiceHeader.csv"));
            while (scan.hasNext())
            {
                buffer = scan.nextLine();

                String [] splittedLine = buffer.split(",");
                if (splittedLine[0].equals(s))
                {
                    buffer = "";
                }
                else
                {
                    //print some message that tells you that the string not found


                }
            }
            scan.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occured while searching in file!");
        }

    }
}
