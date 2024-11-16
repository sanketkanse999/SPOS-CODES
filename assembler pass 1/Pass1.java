import java.io.*;

// Class to represent objects in tables
class Obj {
    String name;
    int addr;

    Obj(String name, int addr) {
        this.name = name;
        this.addr = addr;
    }
}

// Class to represent the pool table
class Pooltable {
    int first;
    int total_literals;

    Pooltable(int first, int total_literals) {
        this.first = first;
        this.total_literals = total_literals;
    }
}

// Main class to handle the assembly process
public class Pass1 {
    public static void main(String[] args) {
        String[] REG = {"ax", "bx", "cx", "dx"};
        String[] IS = {"stop", "add", "sub", "mult", "mover", "movem", "comp", "be", "div", "read"};
        String[] DL = {"ds", "dc"};

        int temp1 = 0;
        int f = 0;

        int total_symb = 0, total_ltr = 0, optab_cnt = 0, pooltab_cnt = 0, loc = 0, temp, pos;
        boolean start = false, end = false, fill_addr = false, ltorg = false;

        Obj[] literal_table = new Obj[10];
        Obj[] symb_table = new Obj[10];
        Obj[] optab = new Obj[60];
        Pooltable[] pooltab = new Pooltable[5];

        String line;

        try {
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));

            while ((line = br.readLine()) != null && !end) {
                String[] tokens = line.split(" ", 4);
                if (loc != 0 && !ltorg) {
                    if (f == 1) {
                        ltorg = false;
                        loc = loc + temp1 - 1;
                        bw.write("\n" + loc);
                        f = 0;
                        loc++;
                    } else {
                        bw.write("\n" + loc);
                        ltorg = false;
                        loc++;
                    }
                }
                ltorg = fill_addr = false;

                for (int k = 0; k < tokens.length; k++) {
                    pos = -1;
                    if (start) {
                        loc = Integer.parseInt(tokens[k]);
                        start = false;
                    }
                    switch (tokens[k]) {
                        case "start":
                            start = true;
                            pos = 1;
                            bw.write("\t(AD, " + pos + ")");
                            break;
                        case "end":
                            end = true;
                            pos = 2;
                            bw.write("\t(AD, " + pos + ")\n");
                            for (temp = 0; temp < total_ltr; temp++) {
                                if (literal_table[temp].addr == 0) {
                                    literal_table[temp].addr = loc - 1;
                                    bw.write("\t(DL, 2) \t (C, " + literal_table[temp].name + ")" + "\n" + loc++);
                                }
                            }
                            if (pooltab_cnt == 0) {
                                pooltab[pooltab_cnt++] = new Pooltable(0, temp);
                            } else {
                                pooltab[pooltab_cnt] = new Pooltable(pooltab[pooltab_cnt - 1].first + pooltab[pooltab_cnt - 1].total_literals, total_ltr - pooltab[pooltab_cnt - 1].first - 1);
                                pooltab_cnt++;
                            }
                            break;
                        case "origin":
                            pos = 3;
                            bw.write("\t(AD, " + pos + ")");
                            pos = search(tokens[++k], symb_table, total_symb);
                            if (pos != -1) {
                                bw.write("\t(C, " + (symb_table[pos].addr) + ")");
                                loc = symb_table[pos].addr;
                            } else {
                                System.out.println("Error: Symbol " + tokens[k] + " not found in symbol table.");
                            }
                            break;
                        case "ltorg":
                            ltorg = true;
                            pos = 5;
                            bw.write("\t(AD, " + pos + ")\n");
                            for (temp = 0; temp < total_ltr; temp++) {
                                if (literal_table[temp].addr == 0) {
                                    literal_table[temp].addr = loc - 1;
                                    bw.write("\t(DL, 2) \t (C, " + literal_table[temp].name + ")" + "\n" + loc++);
                                }
                            }
                            if (pooltab_cnt == 0) {
                                pooltab[pooltab_cnt++] = new Pooltable(0, temp);
                            } else {
                                pooltab[pooltab_cnt] = new Pooltable(pooltab[pooltab_cnt - 1].first + pooltab[pooltab_cnt - 1].total_literals, total_ltr - pooltab[pooltab_cnt - 1].first - 1);
                                pooltab_cnt++;
                            }
                            break;
                        case "equ":
                            pos = 4;
                            bw.write("\t(AD, " + pos + ")");
                            String prev_token = tokens[k - 1];
                            int posi = search(prev_token, symb_table, total_symb);
                            pos = search(tokens[++k], symb_table, total_symb);
                            if (pos != -1) {
                                // Handle EQU operation logic here
                            } else {
                                System.out.println("Error: Symbol " + tokens[k] + " not found in symbol table.");
                            }
                            break;
                        default:
                            // Handle other cases
                            break;
                    }
                }
            }

            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to search for a symbol in the symbol table
    static int search(String name, Obj[] table, int total) {
        for (int i = 0; i < total; i++) {
            if (table[i].name.equals(name)) {
                return i;
            }
        }
        return -1; // Not found
    }
}
