import java.util.Comparator;

public class OrderLettersByFrequency<K,V> implements Comparator<AbstractMap.MapEntry<K,V>> {
	public int compare(AbstractMap.MapEntry<K,V> a, AbstractMap.MapEntry<K,V> b) throws ClassCastException{
		int aFre = (int) a.getValue();
		int bFre = (int) b.getValue();
		
		char aName = (char) a.getKey();
		char bName = (char) b.getKey();
		int result = Integer.compare(aFre, bFre);
		
		if(result==0) result = Character.compare(aName,bName);
		return result;
	}
}
