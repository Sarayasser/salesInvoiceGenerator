import java.io.*;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class invoicDetails {
    public String invoiceId;
    public String productName;
    public String price;
    public String quantity;
    public String[][] invoiceDetailsArr = new String[10][5];

    public String[][] getInvoiceDetail(){
        try {
            File invoiceDetails = new File("InvoiceLine.csv");
            Scanner invoiceDetailsReader = new Scanner(invoiceDetails);

            int i2 = 0;
            while (invoiceDetailsReader.hasNextLine()) {
                // get data of invoice details
                String line = invoiceDetailsReader.nextLine();
                String[] res = line.split(",");

                this.invoiceDetailsArr[i2][0] = res[0];
                this.invoiceDetailsArr[i2][1] = res[1];
                this.invoiceDetailsArr[i2][2] = res[2];
                this.invoiceDetailsArr[i2][3] = res[3];


                System.out.println("in " + this.invoiceDetailsArr[0][0]);
                i2++;
            }
        }catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return invoiceDetailsArr;
    }

    public int getInvoiceTotal(String id,String[][] arr){
        int total = 0;
        System.out.println("in"+arr[0][0]);
        for (int k = 0; k < arr.length; k++) {
            if (arr[k][0] != null) {
                if (parseInt(arr[k][0]) == parseInt(id)) {
                    total += parseInt(arr[k][2]);
                }
            }
        }
        return total;
    }

    public void storeInvoiceDetail(){
        try {
            BufferedWriter myWriter = new BufferedWriter(new FileWriter("InvoiceLine.csv", true));
            myWriter.write(invoiceId);
            myWriter.write(",");
            myWriter.write(productName);
            myWriter.write(",");
            myWriter.write(price);
            myWriter.write("\n");
            myWriter.write(quantity);
            myWriter.write("\n");

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
