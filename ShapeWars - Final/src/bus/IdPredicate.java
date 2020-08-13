package bus;

import java.util.Comparator;

public class IdPredicate implements Comparator<String[]>
{
	@Override
	public int compare(String[] o1, String[] o2) 
	{
		int comparison = 0;
		//comparison = o1.GetId().compareTo(o2.GetId());
		Double a = (double)Integer.parseInt(o1[1]);
		Double b = (double)Integer.parseInt(o2[1]);
		
		comparison = a.compareTo(b);
		
	//	for a second comparison TODO
//		if (comparison == 0)
//		{
//			comparison = 
//		}
		return comparison;
	}
}
