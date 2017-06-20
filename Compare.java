package FileManagement;

import java.util.Comparator;

public class Compare implements Comparator<Record>{
	
	boolean  IsAsc = true;
	
	public Compare(boolean b)
	{
		this.IsAsc = b;
	}

	@Override
    public int compare(Record r1, Record r2)
    {
		if(IsAsc == true)
		{
			
			return  r1.id - r2.id;
		}
		else
		{
			
			return  r2.id - r1.id;
		}
        
    }
}
