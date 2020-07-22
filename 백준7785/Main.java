package 백준7785회사에있는사람;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        IHashMap iHashMap = new IHashMap(N / 2);

        while (N-- > 0) {

            st = new StringTokenizer(br.readLine());
            iHashMap.put(st.nextToken(), st.nextToken().equals("enter"));
        }
        String[] list = iHashMap.getList();
        Arrays.sort(list, Collections.reverseOrder());

        for (String name : list) {
            sb.append(name);
            sb.append("\n");
        }
        System.out.print(sb.toString());
        br.close();
    }
}

class IHashMap {

    private static class Employee {

        String name;
        boolean isWork;
        Employee next;

        public Employee(String name, boolean isWork) {
            this.name = name;
            this.isWork = isWork;
            this.next = null;
        }
    }

    private final Employee[] table;
    private final int M;
    private int N;

    public IHashMap() {
        this(1000000);
    }

    public IHashMap(int M) {
        this.M = M;
        this.N = 0;
        this.table = new Employee[M + 1];
    }

    private int hash(String name) {

        int hash = 0;
        for (char ch : name.toCharArray())
            hash = (31 * hash) + ch;
        return hash;
    }

    private int hashCode(String name) {
        return (hash(name) & 0x7fffffff) % this.M;
    }

    public void put(String name, boolean isWork) {

        int hash = hashCode(name);

        if (this.table[hash] == null) {

            this.table[hash] = new Employee(name, isWork);
            N += isWork ? 1 : 0;
            return;
        }
        Employee employee = this.table[hash];
        Employee newEmployee = new Employee(name, true);

        while (employee.next != null & !employee.name.equals(name)) {
            employee = employee.next;
        }

        if (employee.name.equals(name)) {

            if (employee.isWork && !isWork)
                N--;
            else if (!employee.isWork && isWork)
                N++;

            employee.isWork = isWork;
            return;
        }
        N += isWork ? 1 : 0;
        employee.next = newEmployee;
    }

    public String[] getList() {

        String[] list = new String[this.N];
        int index = 0;

        for (Employee employees : this.table) {

            while (employees != null) {

                if (employees.isWork)
                    list[index++] = employees.name;
                employees = employees.next;
            }
        }
        return list;
    }
}