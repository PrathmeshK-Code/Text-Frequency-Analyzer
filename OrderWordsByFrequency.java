import java.util.Comparator;

public class OrderWordsByFrequency<K,V> implements Comparator<AbstractMap.MapEntry<K,V>> {
	public int compare(AbstractMap.MapEntry<K,V> a, AbstractMap.MapEntry<K,V> b) throws ClassCastException{
		int aFre = (int) a.getValue();
		int bFre = (int) b.getValue();
		
		String aName = (String) a.getKey();
		String bName = (String) b.getKey();
		int result = Integer.compare(aFre, bFre);
		
		if(result==0) result = aName.compareTo(bName);
		return result;
	}
}
