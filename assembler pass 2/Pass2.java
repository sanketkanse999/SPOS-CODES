import java.io.*;
import java.util.*;

public class Pass2 {
    static Obj[] symb_table = new Obj[10];
    static Obj[] literal_table = new Obj[10];
    static int symb_found = 0;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("ENTER TOTAL NUMBER OF SYMBOLS: ");
        int total_symb = sc.nextInt();
        int pos, num;

        // Input for symbols
        for (int i = 0; i < total_symb; i++) {
            symb_table[i] = new Obj("", 0);
            System.out.print("ENTER SYMBOL NAME: ");
            symb_table[i].name = sc.next();
            System.out.print("ENTER SYMBOL ADDRESS: ");
            symb_table[i].addr = sc.nextInt();
        }

        System.out.print("ENTER TOTAL NUMBERS OF LITERALS: ");
        int total_ltr = sc.nextInt();

        // Input for literals
        for (int i = 0; i < total_ltr; i++) {
            literal_table[i] = new Obj("", 0);
            System.out.print("ENTER LITERAL NAME: ");
            literal_table[i].name = sc.next();
            System.out.print("ENTER LITERAL ADDRESS: ");
            literal_table[i].addr = sc.nextInt();
        }

        // Display Symbol Table
        System.out.println("************SYMBOL***********");
        System.out.println("\nSYMBOL\tADDRESS");
        for (int i = 0; i < total_symb; i++)
            System.out.println(symb_table[i].name + "\t" + symb_table[i].addr);

        // Display Literal Table
        System.out.println("************LITERAL TABLE***********");
        System.out.println("\nINDEX\tLITERAL\tADDRESS");
        for (int i = 0; i < total_ltr; i++)
            System.out.println((i + 1) + "\t" + literal_table[i].name + "\t" + literal_table[i].addr);

        // Reading from Output.txt
        BufferedReader br2 = new BufferedReader(new FileReader("output.txt"));
        String line;
        boolean symbol_error = false, undef_mnemonic = false;
        System.out.println("\n********OUTPUT FILE**********\n");

        lab:
        while ((line = br2.readLine()) != null) {
            String[] token_list = line.split("\\s+");
            symbol_error = undef_mnemonic = false;

            lab1:
            for (String token : token_list) {
                if (token.length() > 0) {
                    pos = -1;

                    if (token.matches("---")) {
                        System.out.print("\t---");
                        undef_mnemonic = true;
                    } else if (token.matches("[0-9]+")) {
                        System.out.print("\n\n" + token);
                    } else {
                        String letters = token.replaceAll("[^a-zA-Z]+", "");
                        num = Integer.parseInt(token.replaceAll("[^0-9]+", ""));

                        if (token.matches("\\([0-9]+\\)"))
                            System.out.print("\t" + num);
                        else {
                            switch (letters.toUpperCase()) {
                                case "S":
                                    if (symb_table[num - 1].addr == 0) {
                                        System.out.print("\t---");
                                        symbol_error = true;
                                    } else
                                        System.out.println("\t" + symb_table[num - 1].addr);
                                    break;

                                case "L":
                                    System.out.println("\t" + literal_table[num - 1].addr);
                                    break;

                                case "AD":
                                    System.out.print("\n");
                                    continue lab;

                                case "DL":
                                    switch (num) {
                                        case 1:
                                            System.out.print("\n");
                                            continue lab;

                                        case 2:
                                            System.out.print("\t00\t00");
                                    }
                                    continue lab1;

                                case "C":
                                    System.out.print("\t" + num);
                                    break;

                                default:
                                    System.out.print("\t" + "00" + num);
                            }
                        }
                    }
                }
            }

            if (symbol_error) {
                System.out.println("\nSYMBOL IS NOT DEFINED\n");
            }
            if (undef_mnemonic) {
                System.out.println("\n\nINVALID MNEMONIC");
            }
        }

        // Check for duplicate symbols
        int[] flag = new int[total_symb];
        for (int i = 0; i < total_symb; i++) {
            symb_found = 0;
            for (int j = 0; j < total_symb; j++) {
                if (symb_table[i].name.equalsIgnoreCase(symb_table[j].name) && flag[j] == 0) {
                    symb_found++;
                    flag[i] = flag[j] = 1;
                }
                if (symb_found > 1) {
                    System.out.println("\n\n" + symb_table[i].name + " IS A DUPLICATE SYMBOL");
                }
            }
        }

        br2.close();
        sc.close();
    }
}

class Obj {
    String name;
    int addr;

    Obj(String nm, int address) {
        this.name = nm;
        this.addr = address;
    }
}

class Pooltable {
    int first, total_literals;

    public Pooltable(int f, int l) {
        this.first = f;
        this.total_literals = l;
    }
}
