package lab3;

public class PersonTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person p = new Person("Dave", 12); 
		System.out.println(p.getAge());
		//0
		System.out.println(p.getName());
		//null
		System.out.println(p.getNameLength());
		//error
	}

}
