package org.rivu.image.tools.db;

import com.sleepycat.je.DatabaseException;
import com.sleepycat.persist.EntityStore;
import com.sleepycat.persist.PrimaryIndex;
import com.sleepycat.persist.SecondaryIndex;

public class DataAccessor {
	PrimaryIndex<String, RivuData> id;
	SecondaryIndex<String, String, RivuData> type;
	SecondaryIndex<String, String, RivuData> docid;

	public DataAccessor(EntityStore store) throws DatabaseException {
		id = store.getPrimaryIndex(String.class, RivuData.class);
		type = store.getSecondaryIndex(id, String.class, "type");
		docid = store.getSecondaryIndex(id, String.class, "docid");
	}
}
