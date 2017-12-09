package lab2;

public class AtomTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Atom uranium;
		uranium = new Atom(92, 146, 92);
		System.out.println(uranium.getAtomicMass());
		System.out.println(uranium.getAtomicCharge());
		uranium.decay();
		System.out.println(uranium.getAtomicMass());
		System.out.println(uranium.getAtomicCharge());
	}

}
