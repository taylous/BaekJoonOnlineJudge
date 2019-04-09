import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Member implements Comparable<Member> {

    int age;
    String name;
    int priority;

    public Member(int age, String name, int priority) {
        this.age = age;
        this.name = name;
        this.priority = priority;
    }

    @Override
    public int compareTo(Member other) {

        if(this.age < other.age)
            return -1;
        else if(this.age > other.age)
            return 1;
        else {

            if(this.priority < other.priority)
                return -1;
            else if(this.priority > other.priority)
                return 1;
            return 0;
        }
    }
}

public class Main {

    static PriorityQueue<Member> members = new PriorityQueue<>();
    static int N;

	public static void main(String args[]) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
		StringTokenizer st;

		Member member;
		int priority = 0;

		N = Integer.parseInt(br.readLine());

		while(N-- > 0) {

		    st = new StringTokenizer(br.readLine());

		    members.offer(new Member(Integer.parseInt(st.nextToken()), st.nextToken(), priority++));
        }

		while(!members.isEmpty()) {

		    member = members.poll();

		    sb.append(member.age);
		    sb.append(" ");
		    sb.append(member.name);
		    sb.append("\n");
        }
        System.out.println(sb.toString());
		br.close();
	}
}