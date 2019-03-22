import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int mushRoom = 0, approximateValue = 0;

		for (int idx = 0; idx < 10; idx++) {

			mushRoom += Integer.parseInt(br.readLine());

			if (Math.abs(100 - mushRoom) < Math.abs(100 - approximateValue))
				approximateValue = mushRoom;
			else if (Math.abs(100 - mushRoom) == Math.abs(100 - approximateValue))
				approximateValue = Math.max(approximateValue, mushRoom);
		}
		System.out.println(approximateValue);
		br.close();
	}
}